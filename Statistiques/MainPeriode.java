import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Date;
import java.util.Calendar;

public class MainPeriode{
    
    JTextField debutP = new JTextField();
    JTextField finP = new JTextField();
    JButton valide = new JButton("Validez");
    Date debut;
    Date fin;
    
    MainPeriode(FrameMenu fenetre){
	JPanel textField = new JPanel(new GridLayout(1,3));

	textField.add(debutP);
	textField.add(finP);
	textField.add(valide);
	
	fenetre.getContentPane().add(textField,BorderLayout.SOUTH);
	fenetre.panel_act_south= textField;
	fenetre.revalidate();
	
	valide.addActionListener(new ActionValide(fenetre,debutP,finP,valide));
	//RecupererPeriode(new Date(2018,01,05),new Date(2018,01,07));
    }
}
