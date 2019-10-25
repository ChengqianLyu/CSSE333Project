import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseConnectionService dcs = new DatabaseConnectionService("golem.csse.rose-hulman.edu","SteamManager19");
        dcs.connect("huangz2","LHQ909202033lhq");
        GameService gs = new GameService(dcs);

//        JFrame window = new JFrame("Steam Manager");




//        JFrame f;
//        JMenuBar mb;
//        JMenu file,edit,help;
//        JMenuItem cut,copy,paste,selectAll;
//        cut=new JMenuItem("Russia");
//        copy=new JMenuItem("U.S");
//        paste=new JMenuItem("China");
//        selectAll=new JMenuItem("Europe");
//        mb=new JMenuBar();
//        file=new JMenu("Game");
//        edit=new JMenu("Region");
//        help=new JMenu("GameStat");
//        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);
//        mb.add(file);mb.add(edit);mb.add(help);
//        window.setJMenuBar(mb);
//
//
//        String gameName = JOptionPane.showInputDialog(window,"Enter the game name");
//        gs.getLowValue(gameName);
//        ArrayList<Float> put = gs.getValue();
//        ArrayList<Float> highest = gs.getHighest_value();
//
//
//        JPanel p2 = new JPanel();
//        String row[][] = { {"Game","LowestPrice","HighestPrice"},
//                {gameName,put.toString(),highest.toString()}};
//        String column[]={"","",""};
//        JTable table = new JTable(row,column);
//        p2.add(table);
//
//
//        String cateinput = JOptionPane.showInputDialog(window,"Enter the category");
//        gs.searchGameByCategory(cateinput);
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
//        window.setSize(500,200);
//        window.setVisible(true);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFrame window = new JFrame("Steam Manager");

        JMenuBar mb;
        JMenu edit;
        JMenuItem cut,copy,paste,selectAll;
        cut=new JMenuItem("SearchByCategory");
        copy=new JMenuItem("SearchByTitle");
        paste=new JMenuItem("SearchLowestAndHighestPrice");
        selectAll=new JMenuItem("SearchByUsetag");
        cut.addActionListener(new MenuBarListener("SearchByCategory",gs));
        copy.addActionListener(new MenuBarListener("SearchByTitle",gs));
        paste.addActionListener(new MenuBarListener("SearchHighestAndLowestValue",gs));
        selectAll.addActionListener(new MenuBarListener("SearchByUsetag",gs));
        mb=new JMenuBar();
        edit=new JMenu("Tool");
        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);
        mb.add(edit);
        window.setJMenuBar(mb);


//        JTextField textField = new JTextField();
//        textField.setBounds(50,50,150,50);
//        JButton searchByCategory = new JButton("SearchLowestValue");
//        searchByCategory.setBounds(250,50,150,50);
//        window.add(textField);
//        window.add(searchByCategory);
//        searchByCategory.addActionListener(new MyListener(textField,gs));
        window.setSize(600,300);
        window.setLayout(null);
        window.setVisible(true);
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
