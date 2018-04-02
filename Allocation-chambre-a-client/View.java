import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.util.*;
import java.io.*;
import java.awt.Color;

public class View extends JFrame{
    
    private JPanel p = new JPanel();    
    private JPanel c2 = new JPanel();
    private JPanel panel_choix = new JPanel(new GridLayout(1,4));
    private JPanel panel_date = new JPanel();
    private JPanel panel_south = new JPanel();
    private CardLayout cl = new CardLayout();
    private JPanel content = new JPanel();
    private int i=400;
    private String[] listContent = {"c1","c2","c3"};
    public ArrayList<JPanel> jp_reservation = new ArrayList<JPanel>();
    public Reservation res_act;
    public Chambre chambre_act;
    public Admin a;
    public JLabel choix_chambre = new JLabel();    
    public JButton consulter_liste = new JButton("Consulter liste");
    private JPanel liste_chambre;
    private JScrollPane scrollPane;
    private boolean dejaAfficher = false;
    public JButton precedent;
    public JButton valider;
    public int select=-1;
    
    public View(){
	
	this.setSize(1000,800);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(" Gestionnaire de réservation ");
	this.a = new Admin();
       
	JLabel l = new JLabel("Recherche (Ex: 'Nom Prenom' ou 'Reference')  ");
	JTextField champ = new JTextField(40);	
	JButton b = new JButton("  Rechercher");
	p.add(l);
	p.add(champ);
	p.add(b);

       	
	Controleur c = new Controleur(1);
	c.setChamp(champ);
	c.setView(this);
	b.addActionListener(c);
	

	precedent = new JButton("Précédent");
	Controleur c3 = new Controleur(4);
	precedent.addActionListener(c3);
	c3.setView(this);
	precedent.setVisible(false);
	precedent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	
	valider = new JButton("Valider");
	Controleur c4 = new Controleur(2);
	valider.addActionListener(c4);
	c4.setView(this);
	c4.setChamp(champ);
	valider.setVisible(false);
	valider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	
	Controleur c2 = new Controleur(3);
	consulter_liste.addActionListener(c2);
	c2.setView(this);
	consulter_liste.setVisible(false);
	consulter_liste.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	choix_chambre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

	JLabel date = new JLabel(" "+ c.getDate());
	//	panel_date.add(date);
	date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	panel_choix.add(choix_chambre);	
	panel_choix.add(valider);
	panel_choix.add(consulter_liste);
	panel_choix.add(precedent);
	panel_south.add(panel_date);
	this.getContentPane().add(panel_south, BorderLayout.SOUTH);
	
	content.setLayout(cl);
	
	this.getContentPane().add(p, BorderLayout.NORTH);
	this.getContentPane().add(content, BorderLayout.CENTER);
	JPanel vide =  new JPanel();
	content.add(vide, listContent[0]);
    }
    
   
    public void afficherReservationET(){
	
	GridLayout g2 = new GridLayout(1,7);	
     	
	
	JPanel res = new JPanel(g2);
	res.setBackground(Color.gray);
	res.setPreferredSize(new Dimension(1000,50));       
	JLabel anom = new JLabel("Nom");
	anom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel aprenom = new JLabel("Prenom");
	aprenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel areference  = new JLabel("Reference");
	areference.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel adebut = new JLabel("Debut");
	adebut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel anuits = new JLabel("Nuits");
	anuits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel acategorie = new JLabel("Categorie");
	acategorie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel aselection = new JLabel("Séléction");
	aselection.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	res.add(anom);
	res.add(aprenom);
	res.add(areference);	
	res.add(adebut);
	res.add(anuits);
	res.add(acategorie);
	res.add(aselection);
	
	c2.add(res,BorderLayout.NORTH);
	
	
    }
    
    public void afficherReservation(Reservation r, int id){
	
    	GridLayout g1 = new GridLayout(1,7);
	JPanel c1 = new JPanel(g1);
	c1.setBackground(Color.white);
	c1.setPreferredSize(new Dimension(1000,50));       
	JLabel nom = new JLabel(r.getClient().getNom());
	nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel prenom = new JLabel(r.getClient().getPrenom());
	prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel reference  = new JLabel(r.getReference());
	reference.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel debut = new JLabel(r.getDebut().toString());
	debut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel nuits = new JLabel(" "+r.getNuits());
	nuits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel categorie = new JLabel(" "+r.getCategorie());
	categorie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JButton select = new JButton("Selectionner");
	select.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	Controleur c = new Controleur(0);
	select.addActionListener(c);
	c.setSelection(id);
	c.setView(this);
	c1.add(nom);
	c1.add(prenom);
	c1.add(reference);
	c1.add(debut);
	c1.add(nuits);
	c1.add(categorie);
	c1.add(select);
	
	jp_reservation.add(c1);
	c2.add(c1,BorderLayout.CENTER);
	
	content.add(c2, listContent[1]);
	
	
    }
    
    public void switchNext(){
	cl.next(content);
    }
    
    public void switchPrevious(){
	
	cl.previous(content);
    }

    public void showNum(int i){
	cl.show(content, listContent[i]);
    }
    
    public void afficherSelection(int select){

	for(JPanel p : jp_reservation){
	    p.setBackground(Color.white);
	}
	Color g = new Color(131,155,83);
	jp_reservation.get(select).setBackground(g);
    }
    
    public void afficherBoutonValider(){
	valider.setVisible(true);
    }
    public void afficherBoutonConsulterListe(){
	consulter_liste.setVisible(true);
    }
    
    public void afficherChoixChambre(int categorie){
	
	
	a.recupereChambreBD(categorie);
	Chambre chambre = a.retourneChambreDisponible();
	choix_chambre.setText("Chambre n: "+chambre.getNumeroChambre());
	choix_chambre.setVisible(true);
        this.chambre_act=chambre;
	panel_south.add(panel_choix);

	this.revalidate();
    }
    
    public void afficherListeChambre(){

	
	if(dejaAfficher == true){
	    content.remove(scrollPane); 
	}
	dejaAfficher = true;	
	liste_chambre = new JPanel();
	
	int j=0,compteur=0;
	while( j < a.listeChambre.size()){
	    if(a.listeChambre.get(j).getDisponible() == true){
		compteur++;

	    }
	    j++;
	}

	GridLayout g;
	if(compteur+1 < 15){
	    g = new GridLayout(14,1);
	}else{
	    g = new GridLayout(compteur+1,1);
	}
	
	
	
	liste_chambre.setLayout(g);
	scrollPane = new JScrollPane(liste_chambre,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
	
	GridLayout g1 = new GridLayout(1,2);
	JPanel en_tete = new JPanel(g1);
	en_tete.setBackground(Color.gray);
	en_tete.setPreferredSize(new Dimension(1000,50));
	JLabel num_chambre = new JLabel("Chambre");
	num_chambre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	JLabel echanger = new JLabel("Echanger");
	echanger.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	en_tete.add(num_chambre);
	en_tete.add(echanger);
	liste_chambre.add(en_tete, BorderLayout.NORTH);
	
	for (int x  = 0;x < a.listeChambre.size();x++){
	    
	    Chambre chambre = a.listeChambre.get(x);
	    if((chambre.getDisponible() == true) && (chambre.getPropre() == true)){
		JLabel id = new JLabel("Chambre n°"+chambre.getNumeroChambre());
		id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		JButton b = new JButton("Echanger");
		b.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		Controleur c = new Controleur(0);
		b.addActionListener(c);
		
		JPanel p = new JPanel(g1);
		
		c.setEchange(x);
		c.setView(this);
		p.add(id);
		p.add(b);
		liste_chambre.add(p, BorderLayout.CENTER);
	    }
	}
	
		

	precedent.setVisible(true);
	liste_chambre.revalidate();
	content.add(scrollPane, listContent[2]);
	cl.next(content);
	this.revalidate();
	
    }
    
    public void notifierValider(){
	
        JOptionPane jop1 = new JOptionPane();
	jop1.showMessageDialog(this,"Chambre "+ chambre_act.getNumeroChambre()+ " attribuée","Validation",JOptionPane.INFORMATION_MESSAGE);
        
	
    }

        public void notifierErreur(boolean b){
	
        JOptionPane jop1 = new JOptionPane();
	if(b == true)
	jop1.showMessageDialog(this,"Aucune réservation trouvée ","Erreur",JOptionPane.INFORMATION_MESSAGE);
        else
	    jop1.showMessageDialog(this,"La référence n'existe pas","Erreur",JOptionPane.INFORMATION_MESSAGE);
	
    }

    
    public JPanel getPanelCard(){
	return this.c2;
    }
    
    public void setReservationAct(Reservation act){
	this.res_act=act;
    }
}




 
