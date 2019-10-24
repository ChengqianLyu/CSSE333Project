import javax.swing.*;
import javax.xml.transform.Result;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

public class GameService {
    private DatabaseConnectionService dbService = null;
    public GameService(DatabaseConnectionService dbService){
        this.dbService  = dbService;
    }

    public boolean getLowValue(String input) throws SQLException {
        CallableStatement cs =this.dbService.getConnection().prepareCall("{?=call get_lowest_highest_price(?)}");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, input);
        cs.execute();
        int rv = cs.getInt(1);
        if(rv != 0){
            System.out.println("We don't have this game.");
            return false;
        }
        cs = this.dbService.getConnection().prepareCall("call get_lowest_highest_price (?)");
        cs.setString(1,input);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
//        try {
//            rs = cs.executeQuery();
//
//            while(rs.next()){
//                System.out.println(rs.getString(1));
//            }
//        } catch (SQLException e) {
//            System.out.println("We don't have this game!");
//        }
//        int rv = cs.getInt(1);
//        if(!rs.next()){
//            System.out.println("We don't have this game.");
//            return false;
//        }
//        rs.previous();
//        if(rv == 1) {
//            JOptionPane.showMessageDialog(null, "ERROR: We don't have this game in library.");
//            return false;
//        }


        return true;
    }

    public ArrayList<String> getGameName() throws SQLException {
        Statement stmt = null;
        String query = "select title from Game";
        ArrayList<String> games = new ArrayList<String>();
        try {
            stmt = this.dbService.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                games.add(rs.getString("title")) ;
            }
        } catch (SQLException e ) {

        } finally {
            if (stmt != null) { stmt.close(); }
        }
        return games;
    }
}
