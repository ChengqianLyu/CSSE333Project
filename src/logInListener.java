import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logInListener implements ActionListener {

    private JTextField usename;
    private  JTextField password;

    public logInListener(JTextField user, JTextField pass){
        this.usename = user;
        this.password = pass;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = this.usename.getText();
        String pass = this.password.getText();
    }
}
