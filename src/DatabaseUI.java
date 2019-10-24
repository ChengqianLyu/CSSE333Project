import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;

public class DatabaseUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			JFrame window = new JFrame("Steam Manager");
			
			
			

			JFrame f;    
			JMenuBar mb;    
			JMenu file,edit,help;    
			JMenuItem cut,copy,paste,selectAll;        
			cut=new JMenuItem("Russia");    
			copy=new JMenuItem("U.S");    
			paste=new JMenuItem("China");    
			selectAll=new JMenuItem("Europe");      
			mb=new JMenuBar();    
			file=new JMenu("Game");    
			edit=new JMenu("Region");    
			help=new JMenu("GameStat");     
			edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);    
			mb.add(file);mb.add(edit);mb.add(help);    
			window.setJMenuBar(mb);
//			JButton a = new JButton("Search");
//			JPanel p1 = new JPanel();
//			
//			p1.add(a);
//			
			JPanel p2 = new JPanel();
			String row[][] = { {"Game","GameStat","Region","CurrentPrice","LowestPrice","HighestPrice"},    
                    {"","","","","",""},    
                    {"","","","","",""},
                    {"","","","","",""}};  
			String column[]={"","","","","",""};    
			JTable table = new JTable(row,column);
			p2.add(table);
//			window.getContentPane().add(p1);
			window.getContentPane().add(p2);
			
			window.setSize(500,200);
			window.setVisible(true);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
