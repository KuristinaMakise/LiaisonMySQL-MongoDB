import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * La classe Liaison permet de faire la liaison entre
 * une table MySQL et une collection MongoDB
 * La classe Liaison doit reporter les changements qui sont
 * faits sur la table MySQL sur la collection MongoDB
 * 
 * @author XABA
 * @version 16/02/2017
 */
public class Liaison
{
	private ConnexionMySQL tableMySQL;
	private ConnexionMongoDB connexionMongoDB;
	
	private CollectionMongoDB collectionMongoDB;
	
	private ArrayList<List<String>> donneesPourMongoDB;
	private ArrayList<String> nomColonnes;
	
	public Liaison(String url, String login, String passwd, String table, ConnexionMongoDB connexionMongoDB)
	{
		this.tableMySQL = new ConnexionMySQL(url, login, passwd, table);
		this.connexionMongoDB = connexionMongoDB;
		this.collectionMongoDB = new CollectionMongoDB(connexionMongoDB.getDatabase(), table);
		
		donneesPourMongoDB = new ArrayList<List<String>>();
		
		nomColonnes = new ArrayList<String>();
		
		
		
		new MySQLThread(tableMySQL.getStmt(), table);
	}

	public ConnexionMySQL getTableMySQL()
	{
		return tableMySQL;
	}

	public ConnexionMongoDB getConnexionMongoDB()
	{
		return connexionMongoDB;
	}
	
	public void setConnexionMongoDB(ConnexionMongoDB connexionMongoDB) 
	{
		this.connexionMongoDB = connexionMongoDB;
	}

	public CollectionMongoDB getCollectionMongoDB()
	{
		return collectionMongoDB;
	}
	
	private class MySQLThread extends Thread
	{
		private java.sql.Statement statement;
		private String table;
		
		MySQLThread(java.sql.Statement stmt, String table)
		{
			statement = stmt;
			this.table = table;
			
			start();
		}
		
		public void run()
		{
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try
			{
				dt = sdf.parse("2017-01-01 00:00:00");
			}
			catch(ParseException e2)
			{
				e2.printStackTrace();
			}
			String lastUpdate = sdf.format(dt);
			
			String sql = "SELECT * FROM "+table+" WHERE update_date > '"+lastUpdate+"'";
			
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			List<String> donnees = new ArrayList<String>();
			ArrayList<List<String>> changements;
			List<String> ls;
			
			while(true)
			{
				donneesPourMongoDB = new ArrayList<List<String>>();
				try
				{
					rs = statement.executeQuery(sql);
					rsmd = rs.getMetaData();
					nomColonnes = new ArrayList<String>();
					for(int i = 1; i <= rsmd.getColumnCount(); i++)
				    {
				    	if(!nomColonnes.contains(rsmd.getColumnName(i)))
				    	{
				    		nomColonnes.add(rsmd.getColumnName(i));
				    	}
				    }
					
					while(rs.next())
					{
						for(String nom : nomColonnes)
						{
							donnees.add(rs.getString(nom));
						}
						donneesPourMongoDB.add(donnees);
						donnees = new ArrayList<String>();
					}
					System.out.println("Donnees pour MongoDB : "+donneesPourMongoDB.size());
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
				/**
				 * On prépare les changements à appliquer sur MongoDB
				 */
				
				changements = new ArrayList<List<String>>();
				
				ls = new ArrayList<String>();
				for(String nom : nomColonnes)
				{
					ls.add(nom);
				}
				changements.add(ls);
				
				for(List<String> listeString : donneesPourMongoDB)
				{
					for(String value : listeString)
					{
						ls.add(value);
					}
					
					changements.add(ls);
					ls = new ArrayList<String>();
				}
				
				System.out.println("Il y a "+changements.get(0).size()+" colonnes dans la table.");
				
				new MongoDBThread(collectionMongoDB, changements);
				
				try
				{
					Thread.sleep(10000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				
				dt = new Date();
				lastUpdate = sdf.format(dt);
				sql = "SELECT * FROM "+table+" WHERE update_date > '"+lastUpdate+"'";
			}
		}
	}
	
	private class MongoDBThread extends Thread
	{
		CollectionMongoDB collectionMongoDB;
		ArrayList<List<String>> changements;
		
		MongoDBThread(CollectionMongoDB collectionMongoDB, ArrayList<List<String>> changements)
		{
			this.collectionMongoDB = collectionMongoDB;
			this.changements = changements;
			start();
		}
		
		public void run()
		{
			collectionMongoDB.appliquerChangements(changements);
			collectionMongoDB.printCollection();
		}
	}
	
}
