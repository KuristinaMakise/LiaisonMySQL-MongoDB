import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class FenetreCreationCSV extends JFrame{

	private  JFileChooser jfc;

	private ArrayList<Connexion> connexions;
	private JTable listeCo;

	private JLabel texte;
	private JLabel fichier;
	private JButton choisir;
	private JButton valider;
	private ArrayList<ConnexionMongoDB> connexionMongoDB;
	private ArrayList<Liaison> connexionsLiaison = new ArrayList<Liaison>();



	private String fichierAUtiliser;

	public FenetreCreationCSV(ArrayList<Connexion> connexions, JTable listeCo, ArrayList<ConnexionMongoDB> connexionMongoDB) {

		this.connexionMongoDB = connexionMongoDB;
		
		JPanel panneau = new JPanel();
		panneau.setLayout(new BoxLayout(panneau, BoxLayout.X_AXIS));
		panneau.setPreferredSize(new Dimension(400, 125));
		this.connexions = connexions;
		this.listeCo = listeCo;

		texte = new JLabel("Choisir le fichier à traiter : ");
		fichier = new JLabel("");
		choisir = new JButton("...");
		choisir.addActionListener(new choisirFichierATravailler());
		valider = new JButton("Valider");
		valider.addActionListener(new BoutonValider());

		panneau.add(fichier);
		panneau.add(Box.createHorizontalGlue());
		panneau.add(choisir);
		panneau.add(Box.createHorizontalGlue());
		panneau.add(valider);


		this.add(panneau);
		this.setVisible(true);
		pack();
	}

	/*public void lireCSV() throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(fichier.getText()));
		String ligne = null;
		while ((ligne = br.readLine()) != null)
		{
			// Retourner la ligne dans un tableau
			String[] data = ligne.split(",");


		}
		br.close();
	}
	 */
	public TypeBdd choisirType(String type)
	{
		TypeBdd tb;
		if(type.equals("MongoDB"))
			tb = TypeBdd.MongoDB;
		else
			if(type.equals("MySQL"))
				tb = TypeBdd.MySQL;
			else
				tb = TypeBdd.valueOf(type);

		return tb;
	}

	class choisirFichierATravailler extends AbstractAction
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			File repertoireCourant = null;
			try {
				repertoireCourant = new File(".").getCanonicalFile();
			} catch(IOException e) {}


			JFileChooser dialogue = new JFileChooser(repertoireCourant+"/data");

			dialogue.showOpenDialog(null);
			fichierAUtiliser = ""+dialogue.getSelectedFile();      
			fichier.setText(fichierAUtiliser);

		}

	}

	class BoutonValider implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Connexion connexion;
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(fichier.getText()));
				String ligne = null;
				try {
					while ((ligne = br.readLine()) != null)
					{
						connexion = new Connexion();
						// Retourner la ligne dans un tableau
						String[] data = ligne.split(";");
						System.out.println(ligne);
						System.out.println(data[0]);
						connexion.setAdresseCo(data[0]);
						connexion.setPort(data[1]);
						connexion.setBdd(choisirType(data[2]));
						connexion.setNom(data[3]);
						connexion.setLogin(data[4]);
						connexion.setMdp(data[5]);
						connexion.setNomBdd(data[6]);
						connexion.setNomTable(data[7]);

						connexions.add(connexion);
						if(choisirType(data[2]) == TypeBdd.MongoDB && !data[0].equals("") && !data[3].equals(""))
						{
							
							String url = "mongodb://"+data[0];
							String nom = data[3];
							
							System.out.println(url);
							System.out.println(nom);

							connexionMongoDB.add(new ConnexionMongoDB(url, nom));

							for(Liaison l : connexionsLiaison)
							{
								if(l.getConnexionMongoDB() == null)
								{
									l.setConnexionMongoDB(connexionMongoDB.get(0));
								}
							}

						}
						else 
							if(choisirType(data[2]) == TypeBdd.MySQL && !data[0].equals("") && !data[4].equals("") && !data[5].equals("") && !data[6].equals("") && !data[7].equals(""))
							{
								String url = "jdbc:mysql://"+data[0]+"/"+data[6]+"";
								String tmp = ""+data[6]+""+data[7];
								connexionsLiaison.add(new Liaison(url, data[4], data[5], data[7], connexionMongoDB.get(0), tmp));
							}

					}
					br.close();
					listeCo.updateUI();
					dispose();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}

}