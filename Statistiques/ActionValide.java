import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Date;
import java.lang.*;

public class ActionValide implements ActionListener{
    FrameMenu fenetre;
    JButton valide;
    Courbe c;
    String[] debutS;
    String[] finS;
    JTextField debutP;
    JTextField finP;
    
    ActionValide(FrameMenu fenetre, JTextField debutP, JTextField finP, JButton valide){ 
	this.valide=valide;
	this.fenetre=fenetre;
	this.debutP=debutP;
	this.finP=finP;
	
    }

    public void notifierErreur(){
        JOptionPane jop1 = new JOptionPane();
	jop1.showMessageDialog(fenetre,"La Date se note sous la forme '<année>-<mois>-<jour>'","Erreur : Date incorrecte",JOptionPane.INFORMATION_MESSAGE);
    }

    public void notifierErreurAnnee(){
        JOptionPane jop1 = new JOptionPane();
	jop1.showMessageDialog(fenetre,"L'année de la case de gauche doit être infèrieur ou égale à celle de droite","Erreur : Année incorrecte",JOptionPane.INFORMATION_MESSAGE);
    }

    public void notifierErreurMois(){
        JOptionPane jop1 = new JOptionPane();
	jop1.showMessageDialog(fenetre,"Le mois de la case de gauche doit être infèrieur ou égale à celle de droite quand vous selectionnez la même année","Erreur : Mois incorrecte",JOptionPane.INFORMATION_MESSAGE);
    }

    public void notifierErreurJour(){
        JOptionPane jop1 = new JOptionPane();
	jop1.showMessageDialog(fenetre,"Le jour de la case de gauche doit être inferieur à celle de droite quand vous selectionnez le même mois et la même année","Erreur : Jour incorrecte",JOptionPane.INFORMATION_MESSAGE);
    }

    
    @Override
    public void actionPerformed(ActionEvent e){
	this.debutS= this.debutP.getText().split("-");
	this.finS=this.finP.getText().split("-");

	if(debutS.length != 3 || finS.length != 3){
	    notifierErreur();
	    return;
	}
	if(Integer.parseInt(debutS[0])>Integer.parseInt(finS[0])){
	    notifierErreurAnnee();
	    return;
	}
	else if(Integer.parseInt(debutS[0])==Integer.parseInt(finS[0]))
	    if(Integer.parseInt(debutS[1])>Integer.parseInt(finS[1])){
		notifierErreurMois();
		return;
	    }
	    else if(Integer.parseInt(debutS[1])==Integer.parseInt(finS[1]))
		if(Integer.parseInt(debutS[2])>Integer.parseInt(finS[2]) || Integer.parseInt(debutS[2])==Integer.parseInt(finS[2])){
		    notifierErreurJour();
		    return;
		}
	
	
	
	
	RecupererPeriode infoPeriode = new RecupererPeriode(new Date(Integer.parseInt(debutS[0])-1900,Integer.parseInt(debutS[1])-1,Integer.parseInt(debutS[2])),new Date(Integer.parseInt(finS[0])-1900,Integer.parseInt(finS[1])-1,Integer.parseInt(finS[2])));
	this.c = new Courbe(infoPeriode.moyenne,infoPeriode.coor,infoPeriode.taille);
	fenetre.getContentPane().remove(fenetre.panel_act_center);
	fenetre.getContentPane().add(c,BorderLayout.CENTER);
	fenetre.panel_act_center= this.c;

	fenetre.revalidate();
    }
}
