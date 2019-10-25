import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener {

    private GameService gs;
    private String gameName;

    public MyListener(String input, GameService service){
        this.gameName = input;
        this.gs = service;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.gs.getLowValue(this.gameName);
    }
}
