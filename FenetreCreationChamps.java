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
import javax.swing.JComboBox;
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
	private JPanel bas3;
	private JPanel bas4;


	private JLabel nom;
	private JTextField name;

	private JLabel adresseCo;
	private JTextField addressCo;
	private JLabel port;
	private JTextField textPort;

	private JLabel login;
	private JTextField textLogin;
	private JLabel motDePasse;
	private JTextField password;

	private JLabel typeBdd;
	private JComboBox choix;
	private JButton valider;

	private JLabel bdd;
	private JTextField textBdd;
	private JLabel table;
	private JTextField textTable;


	private ArrayList<Connexion> connexions;
	private ArrayList<Liaison> connexionsLiaison = new ArrayList<Liaison>();
	private ArrayList<ConnexionMongoDB> connexionMongoDB;
	private JTable listeCo;



	public FenetreCreationChamps(ArrayList<Connexion> connexions, JTable listeCo, ArrayList<ConnexionMongoDB> connexionMongoDB) {

		super("Nom temporaire"); 							// Nom cont a changer

		this.connexionMongoDB = connexionMongoDB;
		this.connexions = connexions;
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

		bas3 = new JPanel();
		bas3.setLayout(new BoxLayout(bas3, BoxLayout.X_AXIS));

		bas4 = new JPanel();
		bas4.setLayout(new BoxLayout(bas4, BoxLayout.X_AXIS));

		nom = new JLabel("Nom : ");
		name = new JTextField();
		name.setPreferredSize(new Dimension(40,25));
		//name.setMaximumSize(new Dimension(40,25));



		adresseCo = new JLabel("Adresse de connexion : ");
		addressCo = new JTextField();
		addressCo.setPreferredSize(new Dimension(100,25));
		//addressCo.setMaximumSize(new Dimension(100,25));

		port = new JLabel("Port : ");
		textPort = new JTextField();
		//textPort.setPreferredSize(new Dimension(10,10));
		//textPort.setMaximumSize(new Dimension(10,10));

		haut.add(adresseCo);
		haut.add(addressCo);
		haut.add(port);
		haut.add(textPort);

		login = new JLabel("Login : ");
		textLogin = new JTextField();
		textLogin.setPreferredSize(new Dimension(40,25));

		motDePasse = new JLabel("Mot de Passe : ");
		password = new JTextField();
		password.setPreferredSize(new Dimension(100,25));

		typeBdd = new JLabel("Type base de données : ");
		choix = new JComboBox(TypeBdd.values());
		choix.addItem("");
		choix.setSelectedItem("");
		choix.addActionListener(new ChoixBdd());

		bdd = new JLabel("Nom base de données : ");
		textBdd = new JTextField();
		table = new JLabel("Nom table : ");
		textTable = new JTextField();


		valider = new JButton("Valider");
		valider.addActionListener(new BoutonValider());
		valider.setPreferredSize(new Dimension(100,40));
		valider.setMaximumSize(new Dimension(100,40));

		milieu.add(typeBdd);
		milieu.add(choix);
		bas.add(nom);
		bas.add(name);
		bas2.add(login);
		bas2.add(textLogin);
		bas2.add(motDePasse);
		bas2.add(password);
		bas3.add(bdd);
		bas3.add(textBdd);
		bas3.add(table);
		bas3.add(textTable);
		bas4.add(valider);

		nom.setEnabled(false);
		name.setEnabled(false);
		login.setEnabled(false);
		textLogin.setEnabled(false);
		motDePasse.setEnabled(false);
		password.setEnabled(false);
		bdd.setEnabled(false);
		textBdd.setEnabled(false);
		table.setEnabled(false);
		textTable.setEnabled(false);


		panneauPrincipal.add(haut);
		panneauPrincipal.add(milieu);
		panneauPrincipal.add(bas);
		panneauPrincipal.add(bas2);
		panneauPrincipal.add(bas3);
		panneauPrincipal.add(bas4);


		this.add(panneauPrincipal);




		this.setVisible(true);

		pack();
	}

	public class BoutonValider implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if(!addressCo.getText().equals(""))
			{
				if(!textPort.getText().equals(""))
				{
					if(!choix.getSelectedItem().toString().equals(""))
					{
						if(choix.getSelectedItem().equals(TypeBdd.MongoDB))
						{
							if(!name.getText().equals(""))
							{
								connexions.add(new Connexion(addressCo.getText(), textPort.getText(), (TypeBdd) choix.getSelectedItem(), textBdd.getText()));
								String url = "mongodb://"+addressCo.getText();
								String nom = name.getText();

								System.out.println(url);
								System.out.println(nom);

								connexionMongoDB.add(new ConnexionMongoDB(url, nom));

								if(connexionMongoDB == null)
								{
									System.out.println("lol");
								}

								for(Liaison l : connexionsLiaison)
								{
									if(l.getConnexionMongoDB() == null)
									{
										l.setConnexionMongoDB(connexionMongoDB.get(0));
									}
								}
								listeCo.updateUI();
								dispose();
							}
						}
						else
						{
							if(!textLogin.getText().equals(""))
							{
								if(!password.getText().equals(""))
								{
									if(!textBdd.getText().equals(""))
									{
										if(!textTable.getText().equals(""))
										{
											connexions.add(new Connexion(addressCo.getText(), textPort.getText(), (TypeBdd) choix.getSelectedItem(), name.getText(), textLogin.getText(), password.getText(), textBdd.getText(), textTable.getText()));

											String url = "jdbc:mysql://"+addressCo.getText()+"/"+textBdd.getText()+"";

											System.out.println(url);

											//Liaison l = new Liaison(url, textLogin.getText(), password.getText(), textTable.getText(), connexionMongoDB);

											if(connexionMongoDB == null)
											{
												System.out.println("test");
											}
											
											String tmp = ""+textBdd.getText()+""+textTable.getText();

											connexionsLiaison.add(new Liaison(url, textLogin.getText(), password.getText(), textTable.getText(), connexionMongoDB.get(0), tmp));
											listeCo.updateUI();
											dispose();
										}
									}
								}//password
							}//textLogin
						}

					}//bdd

				}//textPort
			}//addressCo
			//valider.setEnabled(false);

		}

	}

	public class ChoixBdd implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(choix.getSelectedItem().equals(TypeBdd.MySQL)){

				nom.setEnabled(false);
				name.setEnabled(false);
				login.setEnabled(true);
				textLogin.setEnabled(true);
				motDePasse.setEnabled(true);
				password.setEnabled(true);
				bdd.setEnabled(true);
				textBdd.setEnabled(true);
				table.setEnabled(true);
				textTable.setEnabled(true);
			}
			else
				if(choix.getSelectedItem().equals(TypeBdd.MongoDB))
				{
					nom.setEnabled(true);
					name.setEnabled(true);
					login.setEnabled(false);
					textLogin.setEnabled(false);
					motDePasse.setEnabled(false);
					password.setEnabled(false);
					bdd.setEnabled(false);
					textBdd.setEnabled(false);
					table.setEnabled(false);
					textTable.setEnabled(false);
				}
				else{
					nom.setEnabled(false);
					name.setEnabled(false);
					login.setEnabled(false);
					textLogin.setEnabled(false);
					motDePasse.setEnabled(false);
					password.setEnabled(false);
					bdd.setEnabled(false);
					textBdd.setEnabled(false);
					table.setEnabled(false);
					textTable.setEnabled(false);
				}
		}

	}

	/*public static void main(String[] args) {
		JFrame fenetre = new FenetreCreationChamps();
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/


}