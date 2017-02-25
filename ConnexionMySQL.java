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
	private String url;
	private String login;
	private String passwd;
	private String table;
	
	private Statement stmt;
	
	public ConnexionMySQL(String url, String login, String passwd, String table)
	{
		/*String url = "jdbc:mysql://localhost:3306/Transporteur1";
		String login = "root";
		String passwd = "3306";*/
		
		/**
		 * url contenant l'ip et le nom de la database, login et password de connexion
		 */
		this.url = url;
		this.login = login;
		this.passwd = passwd;
		// this.url = "jdbc:mysql://82.236.66.233/Transporteur1";
		// this.login = "xaba";
		// this.passwd = "xaba";
		
		this.table = table;
		
		stmt = null;
		Connection c = null;
		
		ResultSet rs = null;
		
		//ResultSetMetaData rsmd = null;
		
		try
		{
			// MySQL driver
			Class.forName("com.mysql.jdbc.Driver");
			// Connexion
			c = DriverManager.getConnection(url, login, passwd);
			stmt = c.createStatement();
			
			//String sql = "INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (1,\"P.O. Box 885, 1348 Dui Road\",\"Ap #839-6507 Donec Rd.\",6017,35,\"-9.82013, 164.80831\");";
			//stmt.executeUpdate(sql);
			
			String sql = "SELECT * FROM "+table;
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Statement getStmt()
	{
		return this.stmt;
	}
	
}
