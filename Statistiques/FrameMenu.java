import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Date;

public class FrameMenu extends JFrame{
    
    public JPanel panel_act_center = new JPanel();
    public JPanel panel_act_south = new JPanel();
    public int first=1;

    FrameMenu(){
	JPanel menuBoutton = new JPanel();
	this.getContentPane().add(menuBoutton, BorderLayout.NORTH);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(600,400);
	this.setLocation(100,100);
	
	
	JButton occupation = new JButton("Taux occupation");
	JButton presence = new JButton("Taux Non Presentation");
	JButton periode = new JButton("Moyenne taux occupation");

	menuBoutton.add(occupation);
	menuBoutton.add(presence);
	menuBoutton.add(periode);

	occupation.addActionListener(new BouttonMenu(occupation,presence,menuBoutton,this,periode));
	presence.addActionListener(new BouttonMenu(occupation,presence,menuBoutton,this,periode));
	periode.addActionListener(new BouttonMenu(occupation,presence,menuBoutton,this,periode));
	this.setVisible(true);
    }
}

class BouttonMenu implements ActionListener {
    JButton occupation;
    JButton presence;
    JPanel menuBoutton;
    FrameMenu fenetre;
    JButton periode;
	
    BouttonMenu(JButton occupation,JButton presence, JPanel menuBoutton, FrameMenu fenetre, JButton periode ){
	this.occupation=occupation;
	this.presence=presence;
	this.menuBoutton=menuBoutton;
	this.fenetre=fenetre;
	this.periode=periode;
    }

    @Override
    public void actionPerformed(ActionEvent e){
	Object o = e.getSource();

	if(o==occupation){

	    
	    if(fenetre.first == 0){
		fenetre.remove(fenetre.panel_act_center);
		fenetre.remove(fenetre.panel_act_south);
	   }
		fenetre.first=0;
	    new MainOccupation(fenetre);

	}
	if(o==presence){

           if(fenetre.first == 0){
		fenetre.remove(fenetre.panel_act_center);
		fenetre.remove(fenetre.panel_act_south);
	   }
		fenetre.first=0;
	    new MainNonPres(fenetre);
	}
	if(o==periode){

          if(fenetre.first == 0){
		
		fenetre.remove(fenetre.panel_act_center);
		fenetre.remove(fenetre.panel_act_south);
		JPanel jp = new JPanel();
		fenetre.getContentPane().add(jp, BorderLayout.CENTER );
		jp.revalidate();
		fenetre.panel_act_center=jp;

	   }
		
		fenetre.first=0;
	    new MainPeriode(fenetre);
	}
    }
}
