import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.logging.Level;

public class ConnexionMongoDB
{
	private MongoCollection<Document> collection;
	private MongoClient mongoClient;
	
	public ConnexionMongoDB()
	{
		java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		
		// Création de la connexion à MongoDB
		//MongoClient mongoClient = new MongoClient("localhost", 27017);
		
		//MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		
		// Connexion avec l'ip et le port
		MongoClientURI connectionString = new MongoClientURI("mongodb://82.236.66.233");
		mongoClient = new MongoClient(connectionString);

		// Notre base de données
		MongoDatabase db = mongoClient.getDatabase("Transporteur1");
		collection = db.getCollection("Trajet");
		
		/*Document doc = new Document("testName", "Azerty")
                .append("testAge", "123");
		collection.insertOne(doc);*///
		
		for (Document cur : collection.find()) {
		    System.out.println(cur.toJson());
		}
		
		System.out.println(collection.count());
		
		//mongoClient.close();
	}
	
	public void printDB()
	{
		for (Document cur : collection.find())
		{
		    System.out.println(cur.toJson());
		}
		
		System.out.println(collection.count());
	}
	
	public MongoCollection<Document> getCollection()
	{
		return collection;
	}
	
	public void arret()
	{
		mongoClient.close();
	}



}


