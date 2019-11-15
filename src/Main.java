import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.apache.commons.csv.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseConnectionService dcs = new DatabaseConnectionService("golem.csse.rose-hulman.edu","TestSteamManager219");
        StandardPBEStringEncryptor dec = new StandardPBEStringEncryptor();
        dec.setPassword("A#ja4CYuqh=/QG`");
//        dcs.connect("huangz2",dec.decrypt("SM0Phc5N6AwPGj5oWEDoWkUzkjlBNkpq"));
        dcs.connect("huangz2", "LHQ909202033lhq");
        GameService gs = new GameService(dcs);

        JFrame window = new JFrame("Steam Manager");

        JMenuBar mb;
        JMenu edit;
        JMenuItem cut,copy,paste,selectAll,addUser;
        cut=new JMenuItem("SearchByCategory");
        copy=new JMenuItem("SearchByTitle");
        paste=new JMenuItem("SearchLowestAndHighestPrice");
        selectAll=new JMenuItem("SearchByUsetag");
        addUser = new JMenuItem("ConnectSteamAccount");
        cut.addActionListener(new MenuBarListener("SearchByCategory",gs));
        copy.addActionListener(new MenuBarListener("SearchByTitle",gs));
        paste.addActionListener(new MenuBarListener("SearchHighestAndLowestValue",gs));
        selectAll.addActionListener(new MenuBarListener("SearchByUsetag",gs));
        addUser.addActionListener(new LiveFetchListener(gs));
        mb=new JMenuBar();
        edit=new JMenu("Tool");
        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);edit.add(addUser);
        mb.add(edit);
        window.setJMenuBar(mb);
        JTextField userField = new JTextField();
        userField.setBounds(70,50,200,50);
        JTextField passwordField = new JPasswordField();
        ((JPasswordField) passwordField).setEchoChar('*');
        passwordField.setBounds(70,110,200,50);
        JButton btnOneclick = new JButton("One-Click");
        btnOneclick.setBounds(400, 180, 120, 50);
        btnOneclick.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\AccountInfo.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(1);
                        String att2 = cr.get(2);
                        float iat1 = Float.parseFloat(att1);
                        int iat2 = Integer.parseInt(att2);
//                        System.out.println(att1);
                        CallableStatement cs = dcs.getConnection().prepareCall("{call insert_accountInfo (?, ?)}");
                        cs.setFloat(1, iat1);
                        cs.setInt(2, iat2);
                        cs.execute();
                    }
                    System.out.println("passAccountInfo");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\Users.csv"));
                    CSVParser cp2 = new CSVParser(br2, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp2) {
                        String att1 = cr.get(1);
//                        System.out.println(att1);
                        CallableStatement cs = dcs.getConnection().prepareCall("{call AddNewUser (?)}");
                        cs.setString(1, att1);
                        cs.execute();
                    }
                    System.out.println("passUser");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br3 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\Games.csv"));
                    CSVParser cp3 = new CSVParser(br3, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp3) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        int iat2 = Integer.parseInt(att2);
                        String att3 = cr.get(2);
                        String att4 = cr.get(3);
//                        System.out.println(att1);
                        CallableStatement cs = dcs.getConnection().prepareCall("{call insert_Game (?, ?, ?, ?)}");
                        cs.setInt(1, iat1);
                        cs.setInt(2, iat2);
                        cs.setString(3, att3);
                        cs.setString(4, att4);
                        cs.execute();
                    }
                    System.out.println("passGame");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br4 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\Category.csv"));
                    CSVParser cp4 = new CSVParser(br4, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp4) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        String att3 = cr.get(2);
//                        System.out.println(att1);
                        CallableStatement cs = dcs.getConnection().prepareCall("{call insert_category (?, ?, ?)}");
                        cs.setInt(1, iat1);
                        cs.setString(2, att2);
                        cs.setString(3, att3);
                        cs.execute();
                    }
                    System.out.println("passCategory");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\RegionTable.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
//                        System.out.println(att1);
                        CallableStatement cs = dcs.getConnection().prepareCall("{call insert_region (?, ?)}");
                        cs.setInt(1, iat1);
                        cs.setString(2, att2);
                        cs.execute();
                    }
                    System.out.println("passregion");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\GameStat.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(1);
                        String att2 = cr.get(2);
                        float iat2 = Float.parseFloat(att2);
                        String att3 = cr.get(3);
                        float iat3 = Float.parseFloat(att3);
//                        System.out.println(att1);
                        CallableStatement cs = dcs.getConnection().prepareCall("{call insert_GameStat (?, ?, ?)}");
                        cs.setString(1, att1);
                        cs.setFloat(2, iat2);
                        cs.setFloat(3, iat3);
                        cs.execute();
                    }
                    System.out.println("passGameStat");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\PriceTable.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        float iat2 = Float.parseFloat(att2);
                        String att3 = cr.get(2);
                        float iat3 = Float.parseFloat(att3);
                        String att4 = cr.get(3);
                        float iat4 = Float.parseFloat(att4);
//                        System.out.println(att1);
                        CallableStatement cs = dcs.getConnection().prepareCall("{call insert_price (?, ?, ?, ?)}");
                        cs.setInt(1, iat1);
                        cs.setFloat(2, iat2);
                        cs.setFloat(3, iat3);
                        cs.setFloat(4, iat4);
                        cs.execute();
                    }
                    System.out.println("passPrice");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\HasTable.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        int iat2 = Integer.parseInt(att2);
//                        System.out.println(att1);
                        String query = "insert into has (GameID, PriceID) values (?, ?)";
                        PreparedStatement ps = dcs.getConnection().prepareStatement(query);
                        ps.setInt(1, iat1);
                        ps.setInt(2, iat2);
                        ps.execute();
                    }
                    System.out.println("passHas");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\Links.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        int iat2 = Integer.parseInt(att2);
                        String att3 = cr.get(2);
                        int iat3 = Integer.parseInt(att3);
//                        System.out.println(att1);
                        String query = "insert into Links ([Steam Username], UserID, AccountInfoID) values (?, ?, ?)";
                        PreparedStatement ps = dcs.getConnection().prepareStatement(query);
                        ps.setInt(1, iat1);
                        ps.setInt(2, iat2);
                        ps.setInt(3, iat3);
                        ps.execute();
                    }
                    System.out.println("passLink");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\ContainsGame.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        int iat2 = Integer.parseInt(att2);
//                        System.out.println(att1);
                        String query = "insert into ContainsGame (GameID, AccountInfoID) values (?, ?)";
                        PreparedStatement ps = dcs.getConnection().prepareCall(query);
                        ps.setInt(1, iat1);
                        ps.setInt(2, iat2);
                        ps.execute();
                    }
                    System.out.println("passContainsGame");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\PublishInTable.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        int iat2 = Integer.parseInt(att2);
//                        System.out.println(att1);
                        String query = "insert into PublishedIn(GameID, RegionID) values (?, ?)";
                        PreparedStatement ps = dcs.getConnection().prepareStatement(query);
                        ps.setInt(1, iat1);
                        ps.setInt(2, iat2);
                        ps.execute();
                    }
                    System.out.println("passPublished");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\BelongToTable.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        int iat2 = Integer.parseInt(att2);
//                        System.out.println(att1);
                        String query = "insert into BelongTo (GameID, CategoryID) values (?, ?)";
                        PreparedStatement cs = dcs.getConnection().prepareCall(query);
                        cs.setInt(1, iat1);
                        cs.setInt(2, iat2);
                        cs.execute();
                    }
                    System.out.println("passBelong");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\ContainsStat.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        int iat2 = Integer.parseInt(att2);
//                        System.out.println(att1);
                        String query = "insert into ContainsGameStat(AccountInfoID, GameStatID) values (?, ?)";
                        PreparedStatement cs = dcs.getConnection().prepareStatement(query);
                        cs.setInt(1, iat1);
                        cs.setInt(2, iat2);
                        cs.execute();
                    }
                    System.out.println("passContainsStat");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\SteamServer.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        float iat2 = Float.parseFloat(att2);
                        String att3 = cr.get(2);
                        float iat3 = Float.parseFloat(att3);
                        String att4 = cr.get(3);
                        int iat4 = Integer.parseInt(att4);
//                        System.out.println(att1);
                        String query = "insert into SteamServer(SteamUsername, total_value, total_playtime, ContainsGameID) values (?, ?, ?, ?)";
                        PreparedStatement cs = dcs.getConnection().prepareStatement(query);
                        cs.setInt(1, iat1);
                        cs.setFloat(2, iat2);
                        cs.setFloat(3, iat3);
                        cs.setInt(4, iat4);
                        cs.execute();
                    }
                    System.out.println("passSteamServer");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }

                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\csse333project\\Data\\GameStatData.csv"));
                    CSVParser cp1 = new CSVParser(br1, CSVFormat.DEFAULT);
                    for (CSVRecord cr : cp1) {
                        String att1 = cr.get(0);
                        int iat1 = Integer.parseInt(att1);
                        String att2 = cr.get(1);
                        String att3 = cr.get(2);
                        float iat3 = Float.parseFloat(att3);
                        String att4 = cr.get(3);
                        float iat4 = Float.parseFloat(att4);
//                        System.out.println(att1);
                        String query = "insert into GameStatData (SteamUsername, GameStatTitle, [kill/death ratio], [overall performance]) values (?, ?, ?, ?)";
                        PreparedStatement cs = dcs.getConnection().prepareStatement(query);
                        cs.setInt(1, iat1);
                        cs.setString(2, att2);
                        cs.setFloat(3, iat3);
                        cs.setFloat(4, iat4);
                        cs.execute();
                    }
                    System.out.println("passStatData");
                }
                catch (FileNotFoundException n) {
                    n.printStackTrace();
                }
                catch (IOException m) {
                    m.printStackTrace();
                }
                catch (SQLException S) {
                    S.printStackTrace();
                }


            }
        });
        window.add(btnOneclick);
        window.add(userField);
        window.add(passwordField);
        JButton logIn = new JButton("Sign In");
        logIn.setBounds(130,175,80,50);
        logIn.addActionListener(new logInListener(userField,passwordField,dcs,gs,"login"));
        window.add(logIn);
        JButton signIn = new JButton("Sign Up");
        signIn.setBounds(400,80,80,50);
        signIn.addActionListener(new logInListener(userField,passwordField,dcs,gs,"signin"));
        window.add(signIn);


        window.setSize(600,300);
        window.setLayout(null);
        window.setVisible(true);
    }

}
