import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class connectListener implements ActionListener {

    private JTextField T1,T2;
    private GameService GS;

    public connectListener(JTextField t1, JTextField t2, GameService gs){
        this.T1 = t1;
        this.T2 = t2;
        this.GS = gs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String in1 = this.T1.getText();
        String in2 = this.T2.getText();
        this.GS.connectAccount(in1,in2);
    }
}
