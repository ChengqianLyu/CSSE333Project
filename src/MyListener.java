import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyListener implements ActionListener {

    private GameService gs;
    private String gameName;
    private JTextField myField;
    private String func;

    public MyListener(JTextField field, GameService service, String function){
        this.myField = field;
        this.gs = service;
        this.func = function;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.func){
            case "SearchByCategory":
                this.caty();
                break;
            case "SearchByTitle":
                this.title();
                break;
            case "SearchHighestAndLowestValue":
                this.low_high();
                break;
            case "SearchByUsetag":
                this.usertag();
                break;
            case "Info":
                this.info();
                break;
            case "Lib":
                this.lib();
                break;
            case "Stat":
                this.stat();
                break;
        }
    }

    public void low_high(){
        this.gameName = this.myField.getText();
        boolean result = this.gs.getLowValue(this.gameName);
        if(!result){
            return;
        }
        ArrayList<Float> put = gs.getValue();
        ArrayList<Float> highest = gs.getHighest_value();
        JFrame frame = new JFrame("Search Result");
        JPanel p2 = new JPanel();
        String row[][] = { {"Game","LowestPrice","HighestPrice"},
                {gameName,put.toString(),highest.toString()}};
        String column[]={"","",""};
        JTable table = new JTable(row,column);
        p2.add(table);
        frame.getContentPane().add(p2);
        frame.setSize(300,100);
        frame.setVisible(true);
    }

    public void caty(){
        this.gameName = this.myField.getText();
        boolean result = gs.searchGameByCategory(this.gameName);
        if(!result){
            return;
        }
        ArrayList<Float> price = gs.getPrice();
        ArrayList<String> title = gs.getTitle();
        ArrayList<Integer> year = gs.getYear();
        ArrayList<String> usetag = gs.getUsetag();
        JFrame window = new JFrame("Search Result");
        JPanel p2 = new JPanel();
        String row1[][] = { {"Title","Year","Usertag", "CurrentPrice"},
                {title.toString(),year.toString(),usetag.toString(),price.toString()}};
        String column1[]={"","","",""};
        JTable table1 = new JTable(row1,column1);
        p2.add(table1);
        window.getContentPane().add(p2);
        window.getContentPane().add(p2);
        window.setSize(300,100);
        window.setVisible(true);
    }

    public void title(){
        this.gameName = this.myField.getText();
        boolean result = gs.searchGameByTitle(this.gameName);
        if(!result){
            return;
        }
        ArrayList<Float> price = gs.getPrice();
        ArrayList<String> title = gs.getTitle();
        ArrayList<Integer> year = gs.getYear();
        ArrayList<String> usetag = gs.getUsetag();
        JFrame window = new JFrame("Search Result");
        JPanel p2 = new JPanel();
        String row1[][] = { {"Title","Year","Usertag", "CurrentPrice"},
                {title.toString(),year.toString(),usetag.toString(),price.toString()}};
        String column1[]={"","","",""};
        JTable table1 = new JTable(row1,column1);
        p2.add(table1);
        window.getContentPane().add(p2);
        window.getContentPane().add(p2);
        window.setSize(300,100);
        window.setVisible(true);
    }

    public void usertag(){
        this.gameName = this.myField.getText();
        boolean result = gs.searchGameByUsertag(this.gameName);
        if(!result){
            return;
        }
        ArrayList<Float> price = gs.getPrice();
        ArrayList<String> title = gs.getTitle();
        ArrayList<Integer> year = gs.getYear();
        ArrayList<String> usetag = gs.getUsetag();
        JFrame window = new JFrame("Search Result");
        JPanel p2 = new JPanel();
        String row1[][] = { {"Title","Year","Usertag", "CurrentPrice"},
                {title.toString(),year.toString(),usetag.toString(),price.toString()}};
        String column1[]={"","","",""};
        JTable table1 = new JTable(row1,column1);
        p2.add(table1);
        window.getContentPane().add(p2);
        window.getContentPane().add(p2);
        window.setSize(300,100);
        window.setVisible(true);
    }

    public void info(){
        String username = this.myField.getText();
        boolean result = this.gs.getInfo(username);
        if(!result){
            return;
        }
        ArrayList<Float> value = this.gs.getTotal_value();
        ArrayList<Float> playTime = this.gs.getTotal_playetime();
        JFrame window = new JFrame("Result");
        JPanel p2 = new JPanel();
        String row1[][] = { {"User","Total Value","Total Play Time"},
                {username,value.toString(),playTime.toString()}};
        String column1[]={"","   ","     "};
        JTable table1 = new JTable(row1,column1);
        p2.add(table1);
        window.getContentPane().add(p2);
        window.getContentPane().add(p2);
        window.setSize(400,100);
        window.setVisible(true);
    }

    public void lib(){
        String username = this.myField.getText();
        boolean result = this.gs.getLib(username);
        if(!result){
            return;
        }
        ArrayList<String> title = this.gs.getTitle();
        JFrame window = new JFrame("Result");
        JPanel p2 = new JPanel();
        String row1[][] = { {"User","Title"},
                {username,title.toString()}};
        String column1[]={"",""};
        JTable table1 = new JTable(row1,column1);
        p2.add(table1);
        window.getContentPane().add(p2);
        window.getContentPane().add(p2);
        window.setSize(300,100);
        window.setVisible(true);
    }

    public void stat(){
        String username = this.myField.getText();
        boolean result = this.gs.getStatus(username);
        if(!result){
            return;
        }
        ArrayList<String> title = this.gs.getTitle();
        ArrayList<Float> kda = this.gs.getKDA();
        ArrayList<Float> performance = this.gs.getPerformance();
        JFrame window = new JFrame("Result");
        JPanel p2 = new JPanel();
        String row1[][] = { {"User","Title","KDA","Performance"},
                {username,title.toString(),kda.toString(),performance.toString()}};
        String column1[]={"","","",""};
        JTable table1 = new JTable(row1,column1);
        p2.add(table1);
        window.getContentPane().add(p2);
        window.getContentPane().add(p2);
        window.setSize(500,100);
        window.setVisible(true);
    }
}
