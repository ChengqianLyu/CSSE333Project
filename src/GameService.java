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

//    public ArrayList<String> getGameName() throws SQLException {
//        Statement stmt = null;
//        String query = "select title from Game";
//        ArrayList<String> games = new ArrayList<String>();
//        try {
//            stmt = this.dbService.getConnection().createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                games.add(rs.getString("title")) ;
//            }
//        } catch (SQLException e ) {
//
//        } finally {
//            if (stmt != null) { stmt.close(); }
//        }
//        return games;
//    }
}
