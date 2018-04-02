import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Date;

public class View {
    
    private int tauxOccupation;
    JButton bouttonPrecedent = new JButton("<--");
    JButton bouttonSuivant = new JButton("-->");
    JLabel text;
        
    private Date date;
    
    public View(Date date){	
	this.date=date;
	this.text = new JLabel(""+date);
    }
    
    int getTauxOccupation(){
	return this.tauxOccupation;
    }
    
    void setTauxOccupation(int tauxOccupation){
	this.tauxOccupation = tauxOccupation;
    }
    
    Date getDateVue(){
	return this.date;
    }
    
    void setDateVue(Date date){
	this.date=date;
    }
}

class Camembert extends JPanel{
    View vue;
    JLabel text;
    JLabel pourcentage;

    Camembert(View vue, JLabel text){
	this.vue=vue;
	this.text=text;
	this.pourcentage= new JLabel(""+vue.getTauxOccupation()+"%");
	this.add(pourcentage);
    }

    
    @Override
    public void paintComponent(Graphics g){
	super.paintComponent(g);


	text.setText(""+vue.getDateVue());
	pourcentage.setText(""+vue.getTauxOccupation()+"%");


	g.setColor(Color.GREEN);

	g.fillArc(300-100,80,200,200,0,(vue.getTauxOccupation()*360)/100);
	g.setColor(Color.RED);
	g.fillArc(300-100,80,200,200,(vue.getTauxOccupation()*360)/100,360-((360*vue.getTauxOccupation())/100));
    }
}
