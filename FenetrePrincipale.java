import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;


public class FenetrePrincipale extends JFrame{

	//private Container cont;
	private Container panneauPrincipal;
	private JTable listeCo;

	private JButton creationChamps;
	private JButton creationFichier;
	private JButton suppression;
	//private JButton update;


	protected ArrayList<Connexion> connexions = new ArrayList<Connexion>();
	private ArrayList<Liaison> connexionsLiaison = new ArrayList<Liaison>();
	private ArrayList<ConnexionMongoDB> connexionMongoDB = new ArrayList<ConnexionMongoDB>();
	
	private String[] entetes = {"Adresse de Connexion", "Port",  "Type Bdd", "Nom", "Login", "Mot de Passe", "Nom Bdd", "Nom Table"};

	private ModeleDynamiqueObjet mDO = new ModeleDynamiqueObjet();

	public FenetrePrincipale() {

		super("Fenetre Principale"); 							// Nom cont a changer

		panneauPrincipal = getContentPane();
		panneauPrincipal.setPreferredSize(new Dimension(800, 400));

		panneauPrincipal = new JPanel();
		panneauPrincipal.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		listeCo = new JTable(mDO);
		listeCo.setDefaultEditor(TypeBdd.class, new BddCellEditor());
		ListSelectionModel selectionModel = listeCo.getSelectionModel();
		listeCo.setSelectionMode(selectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged( ListSelectionEvent event )
			{
				// See if this is a valid table selection
				if( event.getSource() == listeCo.getSelectionModel()
						&& event.getFirstIndex() >= 0 )
				{
					// Get the data model for this table
					TableModel model = (TableModel)listeCo.getModel();

					suppression.setEnabled(true);

				}
			}
		});
		
		listeCo.setVisible(true);

		gc.weightx = 7;
		gc.weighty = 5;

		gc.gridwidth = 7;
		gc.gridheight = 5;

		gc.gridx = 1;
		gc.gridy = 1;

		gc.fill = GridBagConstraints.BOTH;

		gc.insets = new Insets(20, 20, 5, 20);

		panneauPrincipal.add(new JScrollPane(listeCo), gc);

		creationChamps = new JButton("Creation par champs");
		creationChamps.addActionListener(new AjoutChamps());

		creationFichier = new JButton("Importation CSV");
		creationFichier.addActionListener(new AjoutCSV());

		suppression = new JButton("Suppression");
		suppression.addActionListener(new SupprimerLigne());
		suppression.setEnabled(false);
		/*
		update = new JButton("Mise à jour");
		update.addActionListener(new Update());*/

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;

		gc.gridx = 1;
		gc.gridy = 7;

		//gc.insets = new Insets(20, 20, 20, 20);

		panneauPrincipal.add(creationChamps, gc);

		gc.gridx = 3;

		panneauPrincipal.add(creationFichier, gc);

		gc.gridx = 5;

		panneauPrincipal.add(suppression, gc);

		///gc.gridx = 7;

		//panneauPrincipal.add(update, gc);

		this.add(panneauPrincipal);
		this.setVisible(true);
		pack();
	}

	public class ModeleDynamiqueObjet extends AbstractTableModel {

		public ModeleDynamiqueObjet() {
			super();
		}

		public int getRowCount() {
			return connexions.size();
		}

		public int getColumnCount() {
			return entetes.length;
		}

		public String getColumnName(int columnIndex) {
			return entetes[columnIndex];
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0:
				return connexions.get(rowIndex).getAdresseCo();
			case 1:
				return connexions.get(rowIndex).getPort();
			case 2:
				return connexions.get(rowIndex).getBdd();
			case 3:
				return connexions.get(rowIndex).getNom();
			case 4:
				return connexions.get(rowIndex).getLogin();
			case 5:
				return connexions.get(rowIndex).getMdp();
			case 6:
				return connexions.get(rowIndex).getNomBdd();
			case 7:
				return connexions.get(rowIndex).getNomTable();
			default:
				return null; //Ne devrait jamais arriver
			}
		}

	/*	public void addConnexion(Connexion co) 
		{
			if(co.getBdd() == TypeBdd.MySQL)
			{
				connexions.add(co);
				
				/*String url = "jdbc:mysql://"+co.getAdresseCo()+"/"+co.getNomBdd()+"";
				
				System.out.println(url);
				
				Liaison l = new Liaison(url, co.getLogin(), co.getMdp(), co.getNomTable(), connexionMongoDB.get(0));
				
				
				connexionsLiaison.add(new Liaison(url, co.getLogin(), co.getMdp(), co.getNomTable(), connexionMongoDB.get(0)));
			}
			else
			{
				String url = "mongodb://"+co.getAdresseCo();
				String nom = co.getNom();
				connexionMongoDB = new ConnexionMongoDB(url, nom);
				
				for(Liaison l : connexionsLiaison)
				{
					if(l.getConnexionMongoDB() == null)
					{
						l.setConnexionMongoDB(connexionMongoDB);
					}
				}
			}
				

			fireTableRowsInserted(connexions.size() -1, connexions.size() -1);
		}*/

		public void removeCollection(int rowIndex) {

			connexions.remove(rowIndex);

			fireTableRowsDeleted(rowIndex, rowIndex);
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false; //Toutes les cellules éditables
		}

		public Class getColumnClass(int columnIndex){
			if(columnIndex == 5){
				return TypeBdd.class;
			}
			return Object.class;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if(aValue != null){
				Connexion connexion = connexions.get(rowIndex);

				switch(columnIndex){
				case 0:
					connexion.setAdresseCo((String)aValue);
					break;
				case 1:
					connexion.setPort((String)aValue);
					break;
				case 2:
					connexion.setBdd((TypeBdd)aValue);
					break;
				case 3:
					connexion.setNom((String)aValue);
					break;
				case 4:
					connexion.setLogin((String)aValue);
					break;
				case 5:
					connexion.setMdp((String)aValue);
					break;
				case 6:
					connexion.setNomBdd((String)aValue);
					break;
				case 7: 
					connexion.setNomTable((String)aValue);
					break;
				}
			}
		}
	}


	public class BddCellEditor extends DefaultCellEditor {
		public BddCellEditor() {
			super(new JComboBox(TypeBdd.values()));
		}
	}


	public class AjoutChamps implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFrame fenetre = new FenetreCreationChamps(connexions, listeCo, connexionMongoDB);
			//addConnexion(connexions.get(connexions.size() - 1));
			fenetre.setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);										//Ne fonctionne plus si on met le 'this' avant 
		}
		
		public void addConnexion(Connexion co) 
		{
			/*if(co.getBdd() == TypeBdd.MySQL)
			{
				connexions.add(co);
				
				String url = "jdbc:mysql://"+co.getAdresseCo()+"/"+co.getNomBdd()+"";
				
				System.out.println(url);
				
				Liaison l = new Liaison(url, co.getLogin(), co.getMdp(), co.getNomTable(), connexionMongoDB);
				
				
				connexionsLiaison.add(new Liaison(url, co.getLogin(), co.getMdp(), co.getNomTable(), connexionMongoDB));
			}
			else
			{
				String url = "mongodb://"+co.getAdresseCo();
				String nom = co.getNom();
				connexionMongoDB = new ConnexionMongoDB(url, nom);
				
				for(Liaison l : connexionsLiaison)
				{
					if(l.getConnexionMongoDB() == null)
					{
						l.setConnexionMongoDB(connexionMongoDB);
					}
				}
			}*/
				

			//fireTableRowsInserted(connexions.size() -1, connexions.size() -1);
		}

	}

	public class AjoutCSV implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFrame fenetre = new FenetreCreationCSV(connexions, listeCo, connexionMongoDB);
			fenetre.setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);									
		}



	}

	public class SupprimerLigne implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			mDO.removeCollection(listeCo.getSelectedRow());
			listeCo.updateUI();
			listeCo.getSelectionModel().clearSelection();
			suppression.setEnabled(false);
		}

	}

	/*public static void main(String[] args) {
		JFrame fenetre = new FenetrePrincipale();
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/

}
