import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.sql.Date;

@SuppressWarnings("unchecked")
public class Controleur implements ActionListener{
    
    private int id;
    private JTextField champ;
    private View view;
    private int select =-1;
    private int echange=-1;  
    //DATE FIXE
    Date date= new Date(2018-1900,1-1,7);
  
    public Controleur(int id){
	this.id=id;
    }
    
    //@Override
    public void actionPerformed(ActionEvent e){
	
	if (id==1){
	    String s = champ.getText();
	    String[] res= s.split(" ");
	    Reservation r;


	    
	    if(res.length > 1){
		r = new Reservation(new Client(res[0],res[1]),date); 
		int size = r.getClient().getListeReservation().size();
		if(size == 0){
		     view.notifierErreur(true);
		     
		}
	    }else{
		r = new Reservation(res[0],date); 
		
		if(r.getClient() == null){
		    view.notifierErreur(false);
		}
	    }
	    
	    view.jp_reservation.clear();
	    view.getPanelCard().removeAll();
	    view.getPanelCard().updateUI();
	    view.afficherReservationET();
	    if(r.getClient()!=null)
		for(int l=0;l< r.getClient().getListeReservation().size();l++){ 
		    view.afficherReservation(r.getClient().getListeReservation().get(l),l);
		}
	    view.setReservationAct(r);
	    view.precedent.setVisible(false);
	    view.valider.setVisible(false);
	    view.consulter_liste.setVisible(false);
	    view.choix_chambre.setVisible(false);
	    view.showNum(1);
	    
	}else if(select>=0){
	    view.select = select;
	    view.afficherSelection(select);
	        
	    view.afficherBoutonValider();
	    view.afficherBoutonConsulterListe();
	    
	    view.afficherChoixChambre(view.res_act.getClient().getListeReservation().get(select).getCategorie());
	

	}else if(id == 2){
	    
	    view.a.changerDisponibiliteChambre(view.chambre_act, view.res_act.getClient(),view.res_act.getClient().getListeReservation().get(view.select));
	    
	    view.notifierValider();
	    view.precedent.setVisible(false);
	    view.valider.setVisible(false);
	    view.consulter_liste.setVisible(false);
	    view.choix_chambre.setVisible(false);
	    champ.setText("");
	    view.showNum(0);
	    
	}else if(id == 3){
	    
      	    view.afficherListeChambre();
	    view.consulter_liste.setVisible(false);
	    view.precedent.setVisible(true);
			    
	}else if(id ==4){
	    
	    view.showNum(1);
	    view.precedent.setVisible(false);
	    view.consulter_liste.setVisible(true);

	}else if(echange>=0){
	    
	    
	    view.chambre_act = view.a.listeChambre.get(echange);
	    view.choix_chambre.setText("Chambre n: "+ view.a.listeChambre.get(echange).getNumeroChambre());
	    view.revalidate();
	}
    }
    
    public Date getDate(){
	return this.date;
    }
    
    public void setView(View view){
	this.view=view;
    }
    
    public void setChamp(JTextField champ){
	this.champ=champ;
    }
    
    public void setSelection(int select){
	this.select=select;
	
    }

    
    public void setEchange(int echange){
	this.echange=echange;
    }
}
