import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;
import java.util.Random;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class logInListener implements ActionListener {

    private JTextField usename;
    private  JTextField password;
    private DatabaseConnectionService dbservice;
    private GameService gs;
    private String func;
    private static final Random RANDOM = new SecureRandom();
    private static final Base64.Encoder enc = Base64.getEncoder();
    private static final Base64.Decoder dec = Base64.getDecoder();

    public logInListener(JTextField user, JTextField pass, DatabaseConnectionService dbservice,GameService gs, String function){
        this.usename = user;
        this.password = pass;
        this.dbservice = dbservice;
        this.gs = gs;
        this.func = function;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = this.usename.getText();
        String pass = this.password.getText();
        boolean result = false;
        if (this.func.equals("login")){
            result = login(name,pass);
            if(!result){
                JOptionPane.showMessageDialog(null,"Invalid username or password");
                return;
            }
        }else{
            result = register(name,pass);
            if(!result){
                JOptionPane.showMessageDialog(null, "Failed to create account");
                return;
            }
        }
        System.out.println("loginSuccess");
        JFrame window = new JFrame("Steam Manager User Window");

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
        JMenuItem getLib = new JMenuItem("GetGameLibrary");
        JMenuItem getAcInfo = new JMenuItem("GetAccountInfo");
        JMenuItem getGameStat = new JMenuItem("GetGameStatus");
        getLib.addActionListener(new MenuBarListener("Lib",gs));
        getAcInfo.addActionListener(new MenuBarListener("Info",gs));
        getGameStat.addActionListener(new MenuBarListener("Stat",gs));
        mb=new JMenuBar();
        edit=new JMenu("Tool");
        JMenu uMenu = new JMenu("User");
        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);
        uMenu.add(getLib);uMenu.add(getAcInfo);uMenu.add(getGameStat);
        mb.add(edit);
        mb.add(uMenu);
        window.setJMenuBar(mb);

        window.setSize(600,300);
        window.setLayout(null);
        window.setVisible(true);
    }
    public boolean login(String Username, String password) {
        Connection con = this.dbservice.getConnection();
        String query = "select Name, PasswordSalt, PasswordHash from Users";
        try {
            PreparedStatement pre = con.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            byte[] salt = null;
            String hash = null;
            while (rs.next()) {
                if(rs.getString("Name").equals(Username)) {
                    salt = rs.getBytes("PasswordSalt");
                    hash = rs.getString("PasswordHash");
                    break;
                }
            }
            String attemp = this.hashPassword(salt, password);
            if(attemp.equals(hash)) {
                return true;
            }
            else {
                //TODO: show some error message
                return false;
            }




        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean register(String Username, String Password) {
        byte[] salt = getNewSalt();
        String hash = hashPassword(salt, Password);
        try {
            CallableStatement cs = this.dbservice.getConnection().prepareCall("{? = call registerNewUser(?, ?, ?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, Username);
            cs.setString(3, hash);
            cs.setBytes(4, salt);
            cs.execute();
            int rv = cs.getInt(1);
            if (rv != 0) {
                //TODO: show some fail message
                return false;
            }
            else {
                //TODO: registration successful
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public byte[] getNewSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public String getStringFromBytes(byte[] data) {
        return enc.encodeToString(data);
    }
    public String hashPassword(byte[] salt, String password) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f;
        byte[] hash = null;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = f.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return getStringFromBytes(hash);
    }
}
