import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Date;

public class MainNonPres{
    MainNonPres(FrameMenu fenetre){
        Date date = new Date(2018-1900,01-1,07);
	
        View vue = new View(date);

	BaseDonneeNonPres BD = new BaseDonneeNonPres(date);
	vue.setTauxOccupation(BD.tauxNonPres);

	Camembert c = new Camembert(vue,vue.text);
        fenetre.getContentPane().add(c,BorderLayout.CENTER);
	
        JPanel bouttonPanel = new JPanel(new GridLayout(1,3));
        fenetre.getContentPane().add(bouttonPanel,BorderLayout.SOUTH);

	fenetre.panel_act_center= c;
	fenetre.panel_act_south= bouttonPanel;

	vue.text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        bouttonPanel.add(vue.bouttonPrecedent);
        bouttonPanel.add(vue.text);
        bouttonPanel.add(vue.bouttonSuivant);
	
	vue.bouttonPrecedent.addActionListener(new ActionBouttonNonPres(c,vue));
        vue.bouttonSuivant.addActionListener(new ActionBouttonNonPres(c,vue));
	
	c.revalidate();
    }
}


