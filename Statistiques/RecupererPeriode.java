import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Date;
import java.lang.*;

public class RecupererPeriode {
    Date debut;
    Date fin;
    int coor[] = new int[100];
    int moyenne;
    int taille;
    
    RecupererPeriode(Date debut, Date fin){
	this.debut = debut;
	this.fin = fin;

	constructionTableau(this.debut);
	calculerMoyenne(this.coor);
    }

    void constructionTableau(Date date){
	
	int i=0;
	while(!(date.equals(this.fin))){
	    BaseDonnee BD = new BaseDonnee(date);

	    coor[i]=300-BD.getTauxOcupation();
	    date = new Date(date.getYear(),date.getMonth(),date.getDate()+1);
	    i++;

	}
	this.taille=i;
    }

    void calculerMoyenne(int[] tab){
	int i;
	int tmp=0;

	for(i=0;this.taille!=i;i++){
	    tmp=tab[i]+tmp;
	}
	tmp=tmp/i;
	this.moyenne=tmp;
    }
}
