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


	private JLabel adresseCo;
	private JTextField addressCo;
	private JLabel port;
	private JTextField textPort;

	private JLabel login;
	private JTextField textLogin;
	private JLabel motDePasse;
	private JTextField password;

	private JLabel typeBdd;
	private JLabel choix;
	private JButton valider;

	private JLabel bdd;
	private JTextField textBdd;
	private JLabel table;
	private JTextField textTable;


	private ArrayList<Connexion> connexions;
	private ArrayList<Liaison> connexionsLiaison = new ArrayList<Liaison>();
	private ArrayList<ConnexionMongoDB> connexionMongoDB;
	private JTable listeCo;
	private JFrame fenetre;



	public FenetreCreationChamps(ArrayList<Connexion> connexions, JTable listeCo, ArrayList<ConnexionMongoDB> connexionMongoDB, String choixBdd, JFrame fenetre) {

		super("Ajout base par champs"); 							// Nom cont a changer

		this.fenetre = fenetre;
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


		choix = new JLabel(choixBdd.toString());
		choix.setPreferredSize(new Dimension(40,25));
		
		bdd = new JLabel("Nom base de données : ");
		textBdd = new JTextField();
		textBdd.setPreferredSize(new Dimension(40,25));
		table = new JLabel("Nom table : ");
		textTable = new JTextField();


		valider = new JButton("Valider");
		valider.addActionListener(new BoutonValider());
		valider.setPreferredSize(new Dimension(100,40));
		valider.setMaximumSize(new Dimension(100,40));

		milieu.add(typeBdd);
		milieu.add(choix);
		bas.add(bdd);
		bas.add(textBdd);
		bas.add(table);
		bas.add(textTable);
		bas2.add(login);
		bas2.add(textLogin);
		bas2.add(motDePasse);
		bas2.add(password);

		bas4.add(valider);

		if(choix.getText().equals("MongoDB"))
		{
			login.setEnabled(false);
			textLogin.setEnabled(false);
			motDePasse.setEnabled(false);
			password.setEnabled(false);
			bdd.setEnabled(true);
			textBdd.setEnabled(true);
			table.setEnabled(false);
			textTable.setEnabled(false);
		}

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
					if(!textBdd.getText().equals(""))
					{
						if(choix.getText().equals("MongoDB"))
						{

							String url = "mongodb://"+addressCo.getText();
							String nom = textBdd.getText();

							System.out.println(url);
							System.out.println(nom);

							connexionMongoDB.add(new ConnexionMongoDB(url, nom));

							if(connexionMongoDB == null)
							{
								System.out.println("lol");
							}


							if(connexionMongoDB.get(0).isConnected())
							{
								connexions.add(new Connexion(addressCo.getText(), textPort.getText(), choix.getText(), textBdd.getText(), "", "", "", true));
								fenetre.setVisible(true);
								listeCo.updateUI();
								dispose();
							}
							else
							{
								connexionMongoDB.remove(0);
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

											String url = "jdbc:mysql://"+addressCo.getText()+"/"+textBdd.getText()+"";

											System.out.println(url);

											//Liaison l = new Liaison(url, textLogin.getText(), password.getText(), textTable.getText(), connexionMongoDB);

											if(connexionMongoDB == null)
											{
												System.out.println("test");
											}
											String tmp = ""+textBdd.getText()+""+textTable.getText();
											connexionsLiaison.add(new Liaison(url, textLogin.getText(), password.getText(), textTable.getText(), connexionMongoDB.get(0), tmp));

											if(connexionsLiaison.get(connexionsLiaison.size()-1).getTableMySQL().isConnected())
											{
												connexions.add(new Connexion(addressCo.getText(), textPort.getText(), choix.getText(), textLogin.getText(), password.getText(), textBdd.getText(), textTable.getText(), true));
												listeCo.updateUI();
												dispose();
											}
											else
											{
												connexionsLiaison.remove(connexionsLiaison.size()-1);
											}
										}
									}
								}//password
							}//textLogin
						}
					}

				}//bdd

			}//textPort
		}//addressCo
		//valider.setEnabled(false);

	}

}
