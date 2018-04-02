import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Date;

public class ActionBouttonNonPres implements ActionListener{
    Camembert camembertPanel;
    View vue;
    
    ActionBouttonNonPres(Camembert camembertPanel, View vue){
        this.camembertPanel=camembertPanel;
        this.vue=vue;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object o = e.getSource();
	
        if(o==vue.bouttonSuivant ){

            vue.setDateVue(new Date(vue.getDateVue().getYear(),vue.getDateVue().getMonth(),vue.getDateVue().getDate()+1));
            BaseDonneeNonPres BD = new BaseDonneeNonPres(vue.getDateVue());
            vue.setTauxOccupation(BD.tauxNonPres);
        }
        if(o==vue.bouttonPrecedent ){
            vue.setDateVue(new Date(vue.getDateVue().getYear(),vue.getDateVue().getMonth(),vue.getDateVue().getDate()-1));
            BaseDonneeNonPres BD = new BaseDonneeNonPres(vue.getDateVue());
            vue.setTauxOccupation(BD.tauxNonPres);

        }
        camembertPanel.repaint();
    }
}
