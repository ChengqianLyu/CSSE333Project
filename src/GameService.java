import javax.swing.*;
import javax.xml.transform.Result;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

public class GameService {
    private DatabaseConnectionService dbService = null;
    private ArrayList<Float> lowest_value;
    private ArrayList<String> title;
    private ArrayList<Integer> year;
    private ArrayList<Float> price;
    private ArrayList<String> usertag;
    private ArrayList<Float> highest_value;
    private ArrayList<Float> total_value;
    private ArrayList<Float> total_playetime;
    private ArrayList<Float> KDA;
    private ArrayList<Float> performance;
    public GameService(DatabaseConnectionService dbService){
        this.dbService  = dbService;
    }

    public ArrayList<Float> getValue(){
        return this.lowest_value;
    }
    public ArrayList<Float> getHighest_value(){
        return this.highest_value;
    }
    public ArrayList<String> getTitle(){
        return this.title;
    }
    public ArrayList<Float> getPrice(){
        return this.price;
    }
    public ArrayList<String> getUsetag(){
        return this.usertag;
    }
    public ArrayList<Integer> getYear(){
        return this.year;
    }
    public ArrayList<Float> getTotal_value(){
        return this.total_value;
    }
    public ArrayList<Float> getTotal_playetime(){
        return  this.total_playetime;
    }
    public ArrayList<Float> getKDA(){
        return this.KDA;
    }
    public ArrayList<Float> getPerformance(){
        return this.performance;
    }

    public boolean getLowValue(String input){
        this.lowest_value = new ArrayList<Float>();
        this.highest_value = new ArrayList<>();
        CallableStatement cs = null;
        try {
            cs = this.dbService.getConnection().prepareCall("{call get_lowest_highest_price(?)}");
            cs.setString(1, input);
            ResultSet rs = cs.executeQuery();
            while(rs.next()) {
                this.highest_value.add(rs.getFloat(1));
                this.lowest_value.add(rs.getFloat(2));
            }
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"We don't have this game.");
           return false;
        }
        return true;
    }

    public boolean searchGameByTitle(String input){
        this.title = new ArrayList<>();
        this.year = new ArrayList<>();
        this.price = new ArrayList<>();
        this.usertag = new ArrayList<>();
        CallableStatement cs = null;
        try {
            cs = this.dbService.getConnection().prepareCall("{call Search_Title(?)}");
            cs.setString(1, input);
            ResultSet rs = cs.executeQuery();
            while(rs.next()) {
                this.title.add(rs.getString(1));
                this.year.add(rs.getInt(2));
                this.price.add(rs.getFloat(4));
                this.usertag.add(rs.getString(3));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"We don't have this game.");
            return false;
        }
        return true;
    }

    public boolean searchGameByUsertag(String input){
        this.title = new ArrayList<>();
        this.year = new ArrayList<>();
        this.price = new ArrayList<>();
        this.usertag = new ArrayList<>();
        CallableStatement cs = null;
        try {
            cs = this.dbService.getConnection().prepareCall("{call search_usertag(?)}");
            cs.setString(1, input);
            ResultSet rs = cs.executeQuery();
            while(rs.next()) {
                this.usertag.add(rs.getString(1));
                this.title.add(rs.getString(2));
                this.year.add(rs.getInt(3));
                this.price.add(rs.getFloat(4));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"We don't have this usertag.");
            return false;
        }
        return true;
    }


    public boolean searchGameByCategory(String input){
        this.title = new ArrayList<>();
        this.year = new ArrayList<>();
        this.price = new ArrayList<>();
        this.usertag = new ArrayList<>();
        CallableStatement cs = null;
        try {
            cs = this.dbService.getConnection().prepareCall("{call Search_category(?)}");
            cs.setString(1, input);
            ResultSet rs = cs.executeQuery();
            while(rs.next()) {
                this.title.add(rs.getString(1));
                this.year.add(rs.getInt(2));
                this.price.add(rs.getFloat(3));
                this.usertag.add(rs.getString(4));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"We don't have this category.");
            return false;
        }
        return true;
    }

    public boolean getInfo(String input){
        this.total_value = new ArrayList<>();
        this.total_playetime = new ArrayList<>();
        CallableStatement cs = null;
        try {
            cs = this.dbService.getConnection().prepareCall("{call Retrieve_account_info(?)}");
            cs.setString(1, input);
            ResultSet rs = cs.executeQuery();
            while(rs.next()) {
                this.total_value.add(rs.getFloat(1));
                this.total_playetime.add(rs.getFloat(2));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"We don't have this account.");
            return false;
        }
        return true;
    }

    public boolean getLib(String input){
        this.title = new ArrayList<>();
        CallableStatement cs = null;
        try {
            cs = this.dbService.getConnection().prepareCall("{call retrieve_gamelibrary(?)}");
            cs.setString(1, input);
            ResultSet rs = cs.executeQuery();
            while(rs.next()) {
                this.title.add(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"We don't have this account.");
            return false;
        }
        return true;
    }

    public boolean getStatus(String input){
        this.title = new ArrayList<>();
        this.KDA = new ArrayList<>();
        this.performance = new ArrayList<>();
        CallableStatement cs = null;
        try {
            cs = this.dbService.getConnection().prepareCall("{call retrieve_gamestatus(?)}");
            cs.setString(1, input);
            ResultSet rs = cs.executeQuery();
            while(rs.next()) {
                this.title.add(rs.getString(1));
                this.KDA.add(rs.getFloat(2));
                this.performance.add(rs.getFloat(3));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"We don't have this account.");
            return false;
        }
        return true;
    }
}
