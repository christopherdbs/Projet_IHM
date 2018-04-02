import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Date;

class Courbe extends JPanel{
    int moyenne;
    int x[];
    int y[];
    int taille;
    JLabel j;
    Courbe(int moyenne, int[] tab, int taille){
	this.moyenne=300-moyenne;
	this.x=tab;
	this.y= new int[taille];
	this.taille=taille;
	this.j=new JLabel("Moyenne : "+moyenne);
	this.add(j);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
	y[0]=50;
	this.j.setText("Moyenne : "+moyenne);
	//this.add(j);

	for(int i=1;i!=this.taille;i++){
	    y[i]=y[i-1]+400/this.taille;
	}
	g.drawLine(50,300,50,50);
	g.drawLine(400,300,50,300);
	g.drawPolyline(this.y,this.x,this.taille);
    }
}
