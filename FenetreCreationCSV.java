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
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FenetreCreationCSV extends JFrame{

	private ArrayList<Connexion> connexions;
	private JPanel panneau;
	private JTable listeCo;
	private JScrollPane js;
	private JLabel texte;
	private JLabel fichier;
	private JButton choisir;
	private JButton valider;
	private ArrayList<ConnexionMongoDB> connexionMongoDB;
	private ArrayList<Liaison> connexionsLiaison = new ArrayList<Liaison>();
	



	private String fichierAUtiliser;

	public FenetreCreationCSV(ArrayList<Connexion> connexions, JTable listeCo, ArrayList<ConnexionMongoDB> connexionMongoDB) {
		
		super("Ajout base par CSV");
		
		this.connexionMongoDB = connexionMongoDB;
		
		panneau = new JPanel();
		panneau.setLayout(new BoxLayout(panneau, BoxLayout.X_AXIS));
		panneau.setPreferredSize(new Dimension(600, 125));
		this.connexions = connexions;
		this.listeCo = listeCo;

		texte = new JLabel("Choisir le fichier à traiter : ");
		fichier = new JLabel("");
		//fichier.setPreferredSize(new Dimension(325, 25));
		js = new JScrollPane();
		js.add(fichier);
		js.setPreferredSize(new Dimension(330, 50));
		js.setMaximumSize(new Dimension(330, 50));
		js.setViewportView(fichier);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		choisir = new JButton("...");
		choisir.addActionListener(new choisirFichierATravailler());
		valider = new JButton("Valider");
		valider.addActionListener(new BoutonValider());
		panneau.add(texte);
		panneau.add(js);
		panneau.add(Box.createHorizontalGlue());
		panneau.add(choisir);
		panneau.add(valider);


		this.add(panneau);
		this.setVisible(true);
		pack();
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
			fichier.repaint();

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
						connexion.setBdd("MySQL");
						connexion.setNomBdd(data[2]);
						connexion.setNomTable(data[3]);
						connexion.setLogin(data[4]);
						connexion.setMdp(data[5]);
						

						connexions.add(connexion);
						if(!data[0].equals("") && !data[2].equals("") && !data[3].equals("") && !data[4].equals("") && !data[5].equals(""))
						{
								String url = "jdbc:mysql://"+data[0]+"/"+data[2]+"";
								String tmp = ""+data[2]+""+data[3];
								
								connexionsLiaison.add(new Liaison(url, data[4], data[5], data[3], connexionMongoDB.get(0), tmp));
								if(connexionsLiaison.get(connexionsLiaison.size()-1).getTableMySQL().isConnected())
								{
									connexion.setEtatCo(true);
								}
								else
								{
									connexion.setEtatCo(false);
								}
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