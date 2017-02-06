import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FenetreCreationChamps extends JFrame {
	
	private Container panneauPrincipal;
	
	private JPanel haut;
	private JPanel milieu;
	private JPanel bas;
	private JPanel bas2;
	

	private JLabel nom;
	private JTextField name;
	
	private JLabel adresseCo;
	private JTextField addressCo;
	private JLabel port;
	private JTextField textPort;
	
	private JLabel motDePasse;
	private JTextField password;
	
	private JButton valider;
	
	private ArrayList<Connexion> connections;
	private JTable listeCo;
	
	

	public FenetreCreationChamps(ArrayList<Connexion> connections, JTable listeCo) {
		
		super("Nom temporaire"); 							// Nom cont a changer
		
		this.connections = connections;
		this.listeCo = listeCo;
		
		panneauPrincipal = getContentPane();
		panneauPrincipal.setPreferredSize(new Dimension(400, 125));
		
		panneauPrincipal = new JPanel();
		panneauPrincipal.setLayout(new BoxLayout(panneauPrincipal, BoxLayout.Y_AXIS));
		
		haut = new JPanel();
		haut.setLayout(new BoxLayout(haut, BoxLayout.X_AXIS));
		
		milieu = new JPanel();
		milieu.setLayout(new BoxLayout(milieu, BoxLayout.X_AXIS));
		
		bas = new JPanel();
		bas.setLayout(new BoxLayout(bas, BoxLayout.X_AXIS));
		
		bas2 = new JPanel();
		bas2.setLayout(new BoxLayout(bas2, BoxLayout.X_AXIS));
	
		nom = new JLabel("Nom : ");
		name = new JTextField();
		name.setPreferredSize(new Dimension(40,25));
		//name.setMaximumSize(new Dimension(40,25));
		
		
		
		
		haut.add(nom);
		haut.add(name);
		
		
		adresseCo = new JLabel("Adresse de connection : ");
		addressCo = new JTextField();
		addressCo.setPreferredSize(new Dimension(100,25));
		//addressCo.setMaximumSize(new Dimension(100,25));
		
		port = new JLabel("Port : ");
		textPort = new JTextField();
		//textPort.setPreferredSize(new Dimension(10,10));
		//textPort.setMaximumSize(new Dimension(10,10));
	
		milieu.add(adresseCo);
		milieu.add(addressCo);
		milieu.add(port);
		milieu.add(textPort);
		
		motDePasse = new JLabel("Mot de Passe : ");
		password = new JTextField();
		password.setPreferredSize(new Dimension(100,25));
		
		bas.add(motDePasse);
		bas.add(password);
		
		valider = new JButton("Valider");
		valider.addActionListener(new BoutonValider());
		valider.setPreferredSize(new Dimension(100,40));
		valider.setMaximumSize(new Dimension(100,40));
		
		bas2.add(valider);
		
		panneauPrincipal.add(haut);
		panneauPrincipal.add(milieu);
		panneauPrincipal.add(bas);
		panneauPrincipal.add(bas2);
		
		this.add(panneauPrincipal);
		
		
		
		
		this.setVisible(true);
		
		pack();
	}
	
	class BoutonValider implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(!name.getText().equals("")){
				if(!addressCo.getText().equals("")){
					if(!textPort.getText().equals("")){
						if(!password.getText().equals("")){
							connections.add(new Connexion(name.getText(), addressCo.getText(), textPort.getText(), password.getText()));
							listeCo.updateUI();
							dispose();
						}//password
					}//textPort
				}//addressCo
			}//name	
			//valider.setEnabled(false);
			
		}
		
	}
	
	/*public static void main(String[] args) {
		
		JFrame fenetre = new FenetreCreationChamps();
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/


}
