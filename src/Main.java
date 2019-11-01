import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseConnectionService dcs = new DatabaseConnectionService("golem.csse.rose-hulman.edu","SteamManager19");
        StandardPBEStringEncryptor dec = new StandardPBEStringEncryptor();
        dec.setPassword("A#ja4CYuqh=/QG`");
        dcs.connect("SteamManager19",dec.decrypt("SM0Phc5N6AwPGj5oWEDoWkUzkjlBNkpq"));
        GameService gs = new GameService(dcs);

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
        JTextField userField = new JTextField();
        userField.setBounds(70,50,200,50);
        JTextField passwordField = new JTextField();
        passwordField.setBounds(70,110,200,50);
        window.add(userField);
        window.add(passwordField);
        JButton logIn = new JButton("Log In");
        logIn.setBounds(130,175,80,50);
        logIn.addActionListener(new logInListener(userField,passwordField,dcs,gs,"login"));
        window.add(logIn);
        JButton signIn = new JButton("Sign In");
        signIn.setBounds(400,80,80,50);
        signIn.addActionListener(new logInListener(userField,passwordField,dcs,gs,"signin"));
        window.add(signIn);


        window.setSize(600,300);
        window.setLayout(null);
        window.setVisible(true);
    }

}
