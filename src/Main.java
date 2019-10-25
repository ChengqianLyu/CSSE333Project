import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseConnectionService dcs = new DatabaseConnectionService("golem.csse.rose-hulman.edu","SteamManager19");
        dcs.connect("huangz2","LHQ909202033lhq");
        GameService gs = new GameService(dcs);
//        ArrayList<String> output = gs.getGameName();
//        System.out.println(output.toString());
//        gs.getLowValue("Payday2");


        JFrame window = new JFrame("Steam Manager");




        JFrame f;
        JMenuBar mb;
        JMenu file,edit,help;
        JMenuItem cut,copy,paste,selectAll;
        cut=new JMenuItem("Russia");
        copy=new JMenuItem("U.S");
        paste=new JMenuItem("China");
        selectAll=new JMenuItem("Europe");
        mb=new JMenuBar();
        file=new JMenu("Game");
        edit=new JMenu("Region");
        help=new JMenu("GameStat");
        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);
        mb.add(file);mb.add(edit);mb.add(help);
        window.setJMenuBar(mb);
//			JButton a = new JButton("Search");
//			JPanel p1 = new JPanel();
//
//			p1.add(a);
//


        String gameName = JOptionPane.showInputDialog(window,"Enter the game name");
        gs.getLowValue(gameName);
        ArrayList<Float> put = gs.getValue();




        JPanel p2 = new JPanel();
        String row[][] = { {"Game","LowestPrice"},
                {gameName,put.toString()}};
        String column[]={"",""};
        JTable table = new JTable(row,column);
        p2.add(table);
        //window.getContentPane().add(p2);



//        String titleinput = JOptionPane.showInputDialog(window,"Enter the game name");
//        gs.searchGameByTitle(titleinput);
//        ArrayList<Float> price = gs.getPrice();
//        ArrayList<String> title = gs.getTitle();
//        ArrayList<Integer> year = gs.getYear();
//        ArrayList<String> usetag = gs.getUsetag();
//
//        String row1[][] = { {"Title","Year","Usertag", "CurrentPrice"},
//                {title.toString(),year.toString(),usetag.toString(),price.toString()}};
//        String column1[]={"","","",""};
//        JTable table1 = new JTable(row1,column1);
//        p2.add(table1);
//        window.getContentPane().add(p2);
//
//        window.repaint();
        String cateinput = JOptionPane.showInputDialog(window,"Enter the category");
        gs.searchGameByCategory(cateinput);
        ArrayList<Float> price = gs.getPrice();
        ArrayList<String> title = gs.getTitle();
        ArrayList<Integer> year = gs.getYear();
        ArrayList<String> usetag = gs.getUsetag();

        String row1[][] = { {"Title","Year","Usertag", "CurrentPrice"},
                {title.toString(),year.toString(),usetag.toString(),price.toString()}};
        String column1[]={"","","",""};
        JTable table1 = new JTable(row1,column1);
        p2.add(table1);
        window.getContentPane().add(p2);

        window.setSize(500,200);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
