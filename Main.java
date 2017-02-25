import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Main
{
	public static void main(String args[])
	{
		JFrame fenetre = new FenetrePrincipale();
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*ConnexionMongoDB cMongoDB = new ConnexionMongoDB("mongodb://82.236.66.233", // l'adresse IP pour MongoDB
														 "Transporteur1"); // le nom de la base de données MongoDB
		
		Liaison liaison001 = new Liaison("jdbc:mysql://82.236.66.233/Transporteur1", // l'IP et le nom de la base de données MySQL
										 "xaba", // le login de connexion à MySQL
										 "xaba", // le password de connexion à MySQL
										 "Trajet", // le nom de la table MySQL
										 cMongoDB // la connexion à la base de données MongoDB
										 );
			
		
		Liaison liaison002 = new Liaison("jdbc:mysql://82.236.66.233/Transporteur1", // l'IP et le nom de la base de données MySQL
				 "xaba", // le login de connexion à MySQL
				 "xaba", // le password de connexion à MySQL
				 "Trajet2", // le nom de la table MySQL
				 cMongoDB // la connexion à la base de données MongoDB
				 );
		
		//ConnexionMySQL cMySQL = new ConnexionMySQL("jdbc:mysql://82.236.66.233/Transporteur1","xaba","xaba","Trajet");
		
		// test Specvey
		
		/*ConnexionMongoDB cMongoDB = new ConnexionMongoDB("mongodb://localhost", // l'adresse IP pour MongoDB
				 										 "Transporteur1"); // le nom de la base de données MongoDB
		
		Liaison liaison001 = new Liaison("jdbc:mysql://localhost/test", // l'IP et le nom de la base de données MySQL
										 "xaba", // le login de connexion à MySQL
										 "xaba", // le password de connexion à MySQL
										 "TableTest", // le nom de la table MySQL
										 cMongoDB // la connexion à la base de données MongoDB
										 );*/
		
		
		
		
		
		/*ArrayList<List<String>> changements = new ArrayList<List<String>>();
		List<String> ls = new ArrayList<String>();
		for(String nom : cMySQL.nomColonnes)
		{
			ls.add(nom);
		}
		changements.add(ls);

		ls = new ArrayList<String>();
		
		for(List<String> listeString : cMySQL.donneesPourMongoDB)
		{
			for(String value : listeString)
			{
				ls.add(value);
			}
			
			changements.add(ls);
			ls = new ArrayList<String>();
		}*/
		
		//System.out.println(changements.get(0).size());
		
		/*changements.add(ls);
		ls = new ArrayList<String>();
		ls.add("3");
		ls.add("X");
		changements.add(ls);
		ls = new ArrayList<String>();
		ls.add("4");
		ls.add("A");
		changements.add(ls);
		ls = new ArrayList<String>();
		ls.add("3");
		ls.add("B");
		changements.add(ls);*/
		
		
		
		//cMongoDB.arret();*/
	}
}