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
	private boolean isConnected;
	
	private Statement stmt;
	
	public ConnexionMySQL(String url, String login, String passwd, String table)
	{
		/**
		 * url contenant l'ip et le nom de la database, login et password de connexion
		 */
		this.url = url;
		this.login = login;
		this.passwd = passwd;
		
		stmt = null;
		Connection c = null;
		
		ResultSet rs = null;
		
		try
		{
			// MySQL driver
			Class.forName("com.mysql.jdbc.Driver");
			// Connexion
			c = DriverManager.getConnection(url, login, passwd);
			isConnected = true;
			stmt = c.createStatement();
			
			String sql = "SELECT * FROM "+table;
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				
				System.out.println(rs.getString("UPDATE_DATE"));
			}
			/**
			 * Affichage de tous les id de la database
			 */
		}
		catch(Exception e)
		{
			isConnected = false;
			e.printStackTrace();
		}
	}
	
	public Statement getStmt()
	{
		return this.stmt;
	}
	
	public boolean isConnected()
	{
		return this.isConnected;
	}
	
}
