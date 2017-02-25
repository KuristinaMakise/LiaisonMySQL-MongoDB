import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;

/**
 * La classe ConnexionMongoDB permet la connexion à la base MongoDB
 * à partir d'une adresse IP et du nom de la base de données
 * 
 * @author XABA
 * @version 16/02/2017
 */
public class ConnexionMongoDB
{
	/**
	 * Les objets Mongo
	 */
	private MongoClient mongoClient;
	private MongoDatabase database;
	
	/**
	 * L'adresse IP et le nom de la base de données
	 */
	private String connexionMongoDB;
	private String databaseName;
	
	/**
	 * Connexion à la base de données MongoDB à partir des deux paramètres suivants
	 * @param connexionMongoDB l'adresse IP
	 * @param databaseName le nom de la base de données
	 */
	public ConnexionMongoDB(String connexionMongoDB, String databaseName)
	{
		setConnexion(connexionMongoDB, databaseName);
		// setConnexion("mongodb://82.236.66.233", "Transporteur1");
		
		// Création de la connexion à MongoDB
		// MongoClient mongoClient = new MongoClient("localhost", 27017);
		//MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		/*Document doc = new Document("testName", "Azerty")
                .append("testAge", "123");
		collection.insertOne(doc);*/
	}
	
	/**
	 * Modifie la connexion à la base de donnée MongoDB
	 * à partir des deux paramètres suivants
	 * @param connexionMongoDB l'adresse IP
	 * @param databaseName le nom de la base de donnée
	 */
	public void setConnexion(String connexionMongoDB, String dbName)
	{
		java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		
		this.connexionMongoDB = connexionMongoDB;
		MongoClientURI connectionString = new MongoClientURI(connexionMongoDB);
		this.mongoClient = new MongoClient(connectionString);
				
		this.databaseName = dbName;
		database = mongoClient.getDatabase(dbName);
	}
	
	/**
	 * Retourne le client Mongo
	 * @return mongoClient
	 */
	public MongoClient getMongoClient()
	{
		return this.mongoClient;
	}
	
	/**
	 * Retourne la base de donnée MongoDB
	 * @return database la base de donnée
	 */
	public MongoDatabase getDatabase()
	{
		return this.database;
	}
	
	/**
	 * Retourne l'adresse IP
	 * @return connexionMongoDB l'adresse IP
	 */
	public String getConnexionMongoDB()
	{
		return this.connexionMongoDB;
	}
	
	/**
	 * Retourne le nom de la base de donnée
	 * @return databaseName
	 */
	public String getDatabaseName()
	{
		return this.databaseName;
	}
	
	/**
	 * Ferme la connexion à MongoDB
	 */
	public void arret()
	{
		mongoClient.close();
	}

}


