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
    private static final Random RANDOM = new SecureRandom();
    private static final Base64.Encoder enc = Base64.getEncoder();
    private static final Base64.Decoder dec = Base64.getDecoder();

    public logInListener(JTextField user, JTextField pass, DatabaseConnectionService dbservice){
        this.usename = user;
        this.password = pass;
        this.dbservice = dbservice;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = this.usename.getText();
        String pass = this.password.getText();
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
            CallableStatement cs = this.dbservice.getConnection().prepareCall("? = call registerNewUser(?, ?, ?)");
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
