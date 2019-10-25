import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarListener implements ActionListener {

    private String function;
    private GameService gs;

    public MenuBarListener(String title, GameService service){
        this.function = title;
        this.gs = service;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame window = new JFrame(this.function);
        JTextField textField = new JTextField();
        textField.setBounds(50,50,150,50);
        JButton searchByCategory = new JButton(this.function);
        searchByCategory.setBounds(250,50,150,50);
        window.add(textField);
        window.add(searchByCategory);
        searchByCategory.addActionListener(new MyListener(textField,gs,this.function));
        window.setSize(600,300);
        window.setLayout(null);
        window.setVisible(true);
    }
}
