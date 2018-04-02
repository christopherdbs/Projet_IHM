import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

class Admin{
    
    public ArrayList<Chambre> listeChambre = new ArrayList<Chambre>();
    
    void recupereChambreBD(int categorie){
	try{
            Class.forName("org.mariadb.jdbc.Driver");
            try{
                Connection connexion = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/vazelle","vazelle","6kzgd4rY5atKawnT");
		PreparedStatement requete = connexion.prepareStatement("SELECT categorie, disponible, propre, numero FROM Chambre WHERE categorie='"+categorie+"'");
		ResultSet resultat = requete.executeQuery();
		this.listeChambre.clear();
		while(resultat.next()){
		    this.listeChambre.add(new Chambre(resultat.getBoolean(2),resultat.getBoolean(3),resultat.getInt(4),resultat.getInt(1)));
		}
		resultat.close();
		requete.close();
		connexion.close();
	    }catch(SQLException e){
		System.err.println("Erreur connexion: "+e.getMessage());
	    }
	}catch(ClassNotFoundException e){
	    System.err.println("Pilote indisponible !");
	}
    }
    
    Chambre retourneChambreDisponible(){
	boolean chambreTrouver = false;
	int j = 0;
	for(int i = 0 ; i != this.listeChambre.size() && !chambreTrouver ; i++){
	    if(this.listeChambre.get(i).getPropre() && this.listeChambre.get(i).getDisponible()){
		j = i;
		chambreTrouver=true;
	    }
	}
	if(chambreTrouver)
	    return this.listeChambre.get(j);
	else
	    return null;
    }

    void changerDisponibiliteChambre(Chambre chambre, Client client, Reservation res){
	try{
            Class.forName("org.mariadb.jdbc.Driver");
            try{
                Connection connexion = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/vazelle","vazelle","6kzgd4rY5atKawnT");
		int newDispo;
		if(chambre.getDisponible() == true){
		    newDispo = 0;
		    chambre.setDisponible(false);
		}
		else{
		    newDispo = 1;
		    chambre.setDisponible(true);
		}
		//System.out.println(select);		
		PreparedStatement requete = connexion.prepareStatement("UPDATE Chambre SET disponible='"+newDispo+"' WHERE numero='"+chambre.getNumeroChambre()+"'");
		PreparedStatement requeteID = connexion.prepareStatement("UPDATE Chambre SET id_client="+client.getID()+" WHERE numero='"+chambre.getNumeroChambre()+"'");
		PreparedStatement requeteRef = connexion.prepareStatement("UPDATE Chambre SET reference='"+/*client.getListeReservation().get(select)*/res.getReference()+"' WHERE numero='"+chambre.getNumeroChambre()+"'");
		
		requete.executeQuery();
		requeteID.executeQuery();
		requeteRef.executeQuery();
		requete.close();
		requeteRef.close();
		requeteID.close();
		connexion.close();
            }catch(SQLException e){
                System.err.println("Erreur connexion: "+e.getMessage());
            }
        }catch(ClassNotFoundException e){
            System.err.println("Pilote indisponible !");
        }
    }
}

class Chambre{
    
    private boolean disponible;
    private boolean propre;
    private int numeroChambre;
    private int categorie;
    
    Chambre(boolean disponible, boolean propre, int numeroChambre, int categorie){
	this.disponible = disponible;
	this.propre = propre;
	this.numeroChambre = numeroChambre;
	this.categorie = categorie;
    }
    
    boolean getDisponible(){
	return this.disponible;
    }
    
    boolean getPropre(){
	return this.propre;
    }
    
    int getNumeroChambre(){
	return this.numeroChambre;
    }
    
    int getCategorie(){
	return this.categorie;
    }

    void setDisponible(boolean disponible){
	this.disponible=disponible;
    }
}

public class ChambreBD{
   /* static public void main(String args[]){
	Admin a = new Admin();
	a.recupereChambreBD(1);
	
    }*/
}
