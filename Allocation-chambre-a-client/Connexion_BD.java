import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Date; //Sinon reference to Date is ambiguous, both class java.util.Date in java.util and class java.sql.Date in java.sql match
import java.util.*;

class Client{

    private String nom;
    private String prenom;
    private int id;
    private ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
    
    Client(String nom, String prenom){
	this.nom=nom;
	this.prenom=prenom;
    }
    
    String getNom(){
	return this.nom;
    }
    String getPrenom(){
	return this.prenom;
    }
    int getID(){
	return this.id;
    }
    void setID(int id){
	this.id=id;
    }
    ArrayList<Reservation> getListeReservation(){
	return this.listeReservation;
    }
}

@SuppressWarnings("unchecked")
class Reservation{

    private Client client;
    private String reference;
    private Date debut;
    private int nuits;
    private int categorie;

    Reservation(Reservation r){
	this.client=r.getClient();
	this.reference=r.getReference();
	this.debut=r.getDebut();
	this.nuits=r.getNuits();
	this.categorie=r.getCategorie();
    }
    
    Reservation(Client client, Date date){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            try{
                Connection connexion = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm","projetihm","mhitejorp");
		Connection connexionBDFlorian = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/vazelle","vazelle","6kzgd4rY5atKawnT");
		PreparedStatement requete = connexion.prepareStatement("SELECT reference, debut, nuits, categorie, c.id FROM Client AS c, Reservation AS r WHERE c.nom='"+client.getNom()+"' AND c.prenom='"+client.getPrenom()+"' AND c.id=r.client");
		PreparedStatement requeteRef;
		ResultSet resultatRef;
		
		ResultSet resultat = requete.executeQuery();

		Date dateFin;
		
		this.client=client;
		while(resultat.next()){
		    this.reference=resultat.getString(1);
                    this.debut=resultat.getDate(2);
                    this.nuits=resultat.getInt(3);
		    this.categorie=resultat.getInt(4);
		    client.setID(resultat.getInt(5));

		    

		    requeteRef = connexionBDFlorian.prepareStatement("SELECT COUNT(*) FROM Chambre WHERE reference='"+this.reference+"'");
                    resultatRef = requeteRef.executeQuery();

		    resultatRef.next();
		
			
			if(resultatRef.getInt(1)==0){
		/*	    dateFin = new Date(this.debut.getYear(),this.debut.getMonth(),this.debut.getDate()+this.nuits-1);
			    if(date.after(this.debut) && date.before(dateFin) || date.equals(this.debut) && date.before(dateFin) || date.equals(this.debut) && date.equals(dateFin) || date.after(this.debut) && date.equals(dateFin)){*/
			    client.getListeReservation().add(new Reservation(this));
			    }
			// }
		
		    resultatRef.close();
		    requeteRef.close();
		    
		}
		connexionBDFlorian.close();
		resultat.close();
		requete.close();
		connexion.close();
	    }catch(SQLException e){
                System.err.println("Erreur connexion here 1: "+e.getMessage());
	    }	       
        }catch(ClassNotFoundException e){
            System.err.println("Pilote indisponible !");
        }
    }

    Reservation(String reference, Date date){
	try{
            Class.forName("org.mariadb.jdbc.Driver");
            try{
                Connection connexion = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm","projetihm","mhitejorp");
		PreparedStatement requete = connexion.prepareStatement("SELECT c.prenom, c.nom, r.reference, r.debut, r.nuits, r.categorie, c.id FROM Client AS c, Reservation AS r WHERE r.reference='"+reference+"' AND r.client=c.id");
		                                                                                                 
		Connection connexionBDFlorian = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/vazelle","vazelle","6kzgd4rY5atKawnT");
                PreparedStatement requeteRef = connexionBDFlorian.prepareStatement("SELECT COUNT(reference) FROM Chambre WHERE reference='"+reference+"'");
                ResultSet resultatRef = requeteRef.executeQuery();
                resultatRef.next();
                if(resultatRef.getInt(1)==0){
		    
		    Date dateFin;
		    ResultSet resultat = requete.executeQuery();
		    while(resultat.next()){
			this.client= new Client(resultat.getString(2),resultat.getString(1));
			this.reference=resultat.getString(3);
			this.debut=resultat.getDate(4);
			this.nuits=resultat.getInt(5);
			this.categorie=resultat.getInt(6);
			this.client.setID(resultat.getInt(7));
			/*
			dateFin = new Date(this.debut.getYear(),this.debut.getMonth(),this.debut.getDate()+this.nuits-1);
			if(date.after(this.debut) && date.before(dateFin) || date.equals(this.debut) && date.before(dateFin) || date.equals(this.debut) && date.equals(dateFin) || date.after(this.debut) && date.equals(dateFin)){*/
			    this.client.getListeReservation().add(this);
			    //}
		    }
		    resultat.close();
		}
		connexionBDFlorian.close();
		resultatRef.close();
		requeteRef.close();
		requete.close();
		connexion.close();
	    }catch(SQLException e){
		System.err.println("Erreur connexion: "+e.getMessage());
	    }
	}catch(ClassNotFoundException e){
	    System.err.println("Pilote indisponible !");
	}
    }
    
    Client getClient(){
	return this.client;
    }
    String getReference(){
        return this.reference;
    }
    Date getDebut(){
        return this.debut;
    }
    int getNuits(){
        return this.nuits;
    }
    int getCategorie(){
        return this.categorie;
    }
}

public class Connexion_BD {
    /*public static void main(String args[]){
	
    }*/
}

