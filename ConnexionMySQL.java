import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ConnexionMySQL
{
	ArrayList<List<String>> donneesPourMongoDB;
	ArrayList<String> nomColonnes;
	
	public ConnexionMySQL()
	{
		/*String url = "jdbc:mysql://localhost:3306/Transporteur1";
		String login = "root";
		String passwd = "3306";*/
		
		/**
		 * url contenant l'ip et le nom de la database, login et password de connexion
		 */
		
		String url = "jdbc:mysql://82.236.66.233/Transporteur1";
		String login = "xaba";
		String passwd = "xaba";
		
		Statement stmt = null;
		Connection c = null;
		
		ResultSet rs = null;
		
		
		
		//ResultSetMetaData rsmd = null;
		
		//donneesPourMongoDB = new ArrayList<List<String>>();
		donneesPourMongoDB.clear();
		
		
		nomColonnes = new ArrayList<String>();
		
		try
		{
			// MySQL driver
			Class.forName("com.mysql.jdbc.Driver");
			// Connexion
			c = DriverManager.getConnection(url, login, passwd);
			stmt = c.createStatement();
			
			//String sql = "INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (1,\"P.O. Box 885, 1348 Dui Road\",\"Ap #839-6507 Donec Rd.\",6017,35,\"-9.82013, 164.80831\");";
			//stmt.executeUpdate(sql);
			
			
			String sql = "SELECT * FROM Trajet";
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				System.out.println(rs.getString("UPDATE_DATE"));
				
			}
			/**
			 * Affichage de tous les id de la database
			 */
			/*String sql = "SELECT * FROM Trajet";
			rs = stmt.executeQuery(sql);*/
			
			/*rsmd = rs.getMetaData();
			String name = rsmd.getColumnName(1);
			
		    for(int i = 1; i < rsmd.getColumnCount(); i++)
		    {
		    	System.out.println(rsmd.getColumnName(i));
		    }*/
			
		    
		    
		    new MySQLThread(stmt);
		    
			
			
			
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}/* finally
		{
			try
			{
				c.close();
				stmt.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/
	}
	
	private class MySQLThread extends Thread
	{
		private java.sql.Statement statement;
		
		
		MySQLThread(java.sql.Statement stmt)
		{
			statement = stmt;
			start();
		}
		
		public void run()
		{
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				dt = sdf.parse("2017-01-01 00:00:00");
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			String lastUpdate = sdf.format(dt);
			
			String sql = "SELECT * FROM TRAJET WHERE update_date > '"+lastUpdate+"'";
			
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			List<String> donnees = new ArrayList<String>();
			
			while(true)
			{
				donneesPourMongoDB = new ArrayList<List<String>>();
				try {
					rs = statement.executeQuery(sql);
					rsmd = rs.getMetaData();
					
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
					System.out.println(donneesPourMongoDB.size());
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				dt = new Date();
				lastUpdate = sdf.format(dt);
				sql = "SELECT * FROM TRAJET WHERE update_date > '"+lastUpdate+"'";
			}
		}
	}
}
