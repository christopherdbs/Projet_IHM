import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BaseDonnee{
    private int chambreOccupe;
    private int chambreTotal;
    private Date date;
    private int tauxOccupation;
    
    BaseDonnee(Date date){
	this.date = date;
	retourneReservation(date);
	this.tauxOccupation = (chambreOccupe*100)/chambreTotal;
    }
    
    void retourneReservation(Date date){
	try{
            Class.forName("org.mariadb.jdbc.Driver");
            try{
                Connection connexion = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/vazelle","vazelle","6kzgd4rY5atKawnT");
		PreparedStatement requeteCount = connexion.prepareStatement("SELECT COUNT(*) FROM Chambre");
		PreparedStatement requeteCountRes = connexion.prepareStatement("SELECT debut, nuits FROM Historique");
		ResultSet resultatCount = requeteCount.executeQuery();
		ResultSet resultatCountDate = requeteCountRes.executeQuery();
		Date dateFin;
		this.chambreOccupe=0;
		while(resultatCount.next()){
		    this.chambreTotal = resultatCount.getInt(1);
		}
		
		while(resultatCountDate.next()){
		    Date debut = resultatCountDate.getDate(1);
		    int nuits = resultatCountDate.getInt(2); 
		    
		    dateFin = new Date(debut.getYear(),debut.getMonth(),debut.getDate()+nuits-1);
		    if(date.after(debut) && date.before(dateFin) || date.equals(debut) && date.before(dateFin) || date.equals(debut) && date.equals(dateFin) || date.after(debut) && date.equals(dateFin)){
			this.chambreOccupe++;

		    }
		}
		
		resultatCount.close();
		requeteCount.close();
		resultatCountDate.close();
		requeteCountRes.close();
		connexion.close();
	    }catch(SQLException e){
                System.err.println("Erreur connexion: "+e.getMessage());
	    }	       
        }catch(ClassNotFoundException e){
            System.err.println("Pilote indisponible !");
        }
    }

    int getChambreOccupe(){
	return this.chambreOccupe;
    }

    int getChambreTotal(){
	return this.chambreTotal;
    }
    
    Date getDate(){
	return this.date;
    }
    
    int getTauxOcupation(){
	return this.tauxOccupation;
    }
}

