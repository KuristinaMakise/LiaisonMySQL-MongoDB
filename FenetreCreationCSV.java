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

	private Connexion connexion;

	private String fichierAUtiliser;

	public FenetreCreationCSV(ArrayList<Connexion> connections, JTable listeCo) {

		JPanel panneau = new JPanel();
		panneau.setLayout(new BoxLayout(panneau, BoxLayout.X_AXIS));
		
		this.connexions = connexions;
		this.listeCo = listeCo;
		
		texte = new JLabel("Choisir le fichier à traiter : ");
		fichier = new JLabel("");
		choisir = new JButton("...");
		choisir.addActionListener(new choisirFichierATravailler());

		panneau.add(fichier);
		panneau.add(Box.createHorizontalGlue());
		panneau.add(choisir);

		this.add(panneau);
		this.setVisible(true);
		pack();
	}

	public void lireCSV() throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(fichier.getText()));
		String ligne = null;
		while ((ligne = br.readLine()) != null)
		{
			// Retourner la ligne dans un tableau
			String[] data = ligne.split(",");

			connexion.setNom(data[0]);
			connexion.setAdresseCo(data[1]);
			connexion.setPort(data[2]);
			connexion.setMdp(data[3]);
		}
		br.close();
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
			
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(fichier.getText()));
				String ligne = null;
				try {
					while ((ligne = br.readLine()) != null)
					{
						// Retourner la ligne dans un tableau
						String[] data = ligne.split(",");

						connexion.setNom(data[0]);
						connexion.setAdresseCo(data[1]);
						connexion.setPort(data[2]);
						connexion.setMdp(data[3]);
						
						connexions.add(connexion);
						br.close();
						
						listeCo.updateUI();
						dispose();
					}
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
