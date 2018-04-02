import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BaseDonneeNonPres{
    int nbPres = 0;
    int reservationTotal = 1;
    int reservationPres = 0;
    int tauxNonPres;
    
    BaseDonneeNonPres(Date date){
	retourneReservation(date);
	if(this.reservationTotal!=0)
	    this.tauxNonPres=100-((this.reservationPres*100)/this.reservationTotal);
	else
	    this.tauxNonPres=100;
    }
    
    void retourneReservation(Date date){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            try{
                Connection connexion = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/vazelle","vazelle","6kzgd4rY5atKawnT");
                Connection connexionihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm","projetihm","mhitejorp");                    
                PreparedStatement requeteCount = connexionihm.prepareStatement("SELECT COUNT(*) FROM Reservation WHERE debut='"+date+"'");
                PreparedStatement requeteCountDate = connexion.prepareStatement("SELECT COUNT(*) FROM Historique WHERE debut='"+date+"'");
		ResultSet resultatCount = requeteCount.executeQuery();
                ResultSet resultatCountDate = requeteCountDate.executeQuery();
		
		while(resultatCount.next() && resultatCountDate.next()){
		    this.reservationTotal = resultatCount.getInt(1);
		    this.reservationPres = resultatCountDate.getInt(1);
		}
		resultatCount.close();
                requeteCount.close();
                resultatCountDate.close();
                requeteCountDate.close();
                connexion.close();
		connexionihm.close();
            }catch(SQLException e){
                System.err.println("Erreur connexion: "+e.getMessage());
            }
        }catch(ClassNotFoundException e){
            System.err.println("Pilote indisponible !");
        }
    }
}
