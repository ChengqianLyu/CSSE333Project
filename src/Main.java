import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseConnectionService dcs = new DatabaseConnectionService("golem.csse.rose-hulman.edu","SteamManager19");
        dcs.connect("huangz2","LHQ909202033lhq");
        GameService gs = new GameService(dcs);
        ArrayList<String> output = gs.getGameName();
        System.out.println(output.toString());
        gs.getLowValue("1");
    }

}
