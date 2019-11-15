import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LiveFetchListener implements ActionListener {

    private GameService GS;

    public LiveFetchListener(GameService gs){
        this.GS = gs;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Live Fetch Window");
        JTextField userField = new JTextField();
        userField.setBounds(70,50,200,50);
        JTextField passwordField = new JTextField();
        passwordField.setBounds(70,110,200,50);
        frame.add(userField);
        frame.add(passwordField);
        JButton logIn = new JButton("Connect");
        logIn.setBounds(130,175,80,50);
        logIn.addActionListener(new connectListener(userField,passwordField,this.GS));
        frame.add(logIn);
        frame.setSize(600,300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
