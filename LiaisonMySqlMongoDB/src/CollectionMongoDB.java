import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * La classe CollectionMongoDB gère une collection de MongoDB
 * avec tous les changements possibles
 * 
 * @author XABA
 * @version 16/02/2017
 */
public class CollectionMongoDB
{
	/**
	 * Le nom de la collection de la base de données MongoDB
	 */
	private String nomCollection;
	
	/**
	 * La collection de la base de données MongoDB
	 */
	private MongoCollection<Document> collection;
	
	/**
	 * On met en place la transmission vers MongoDB
	 * à partir des deux paramètres suivants
	 * @param db la base de données MongoDB
	 * @param nomCollection le nom de la collection
	 */
	public CollectionMongoDB(MongoDatabase db, String nomCollection)
	{
		this.nomCollection = nomCollection;
		// Si la collection n'existe pas, MongoDB la crée automatiquement
		this.collection = db.getCollection(nomCollection);
	}
	
	/**
	 * Retourne le nom de la collection MongoDB
	 * @return nomCollection le nom de la collection MongoDB
	 */
	public String getNomCollection()
	{
		return this.nomCollection;
	}
	
	/**
	 * Retourne la collection MongoDB
	 * @return collection la collection MongoDB
	 */
	public MongoCollection<Document> getCollection()
	{
		return collection;
	}
	
	/**
	 * On affiche dans la console le nom de la collection,
	 * son nombre de documents et ses documents au format json
	 */
	public void printCollection()
	{
		System.out.println("Dans la collection "+nomCollection+",");
		System.out.println("Il y a "+collection.count()+" documents.\n");
		for (Document cur : collection.find())
		{
		    System.out.println(cur.toJson());
		}
	}
	
	/**
	 * Retourne un document créé à partir d'une liste de clés et de valeurs
	 * @param keys la liste des clés
	 * @param values la liste des valeurs
	 * @return doc un document créé à partir d'une liste de clés et de valeurs
	 */
	public Document listToDoc(List<String> keys, List<String> values)
	{
		Document doc = new Document(keys.get(0), values.get(0));
		for(int i=1;i<keys.size();i++)
		{
			doc.append(keys.get(i), values.get(i));
		}
		return doc;
	}
	
	/**
	 * Ajoute un document à la collection à partir d'une liste de clés et de valeurs
	 * @param keys la liste des clés
	 * @param values la liste des valeurs
	 */
	public void ajouter(List<String> keys, List<String> values)
	{
		Document doc = listToDoc(keys, values);
		collection.insertOne(doc);
	}
	
	/**
	 * Modifie un document à la collection à partir d'une liste de clés et de valeurs
	 * @param keys la liste des clés
	 * @param values la liste des valeurs
	 */
	public void modifier(List<String> keys, List<String> values)
	{
		Document doc = listToDoc(keys, values);
		collection.findOneAndReplace(new Document("id",values.get(0)), doc);
	}
	
	/**
	 * Un changement est un ajout ou une modification d'un document
	 * à la collection à partir d'une liste de clés et de valeurs
	 * @param keys la liste des clés
	 * @param values la liste des valeurs
	 */
	private void unChangement(List<String> keys, List<String> values)
	{
		Document whereQuery = new Document(keys.get(0), values.get(0));
		Document doc = collection.find(whereQuery).first();
		if(doc == null)
		{
			ajouter(keys, values);
		}
		else
		{
			modifier(keys, values);
		}
	}
	
	/**
	 * À partir d'une liste de changements,
	 * on applique chaque changement un par un
	 * @param changements liste des changements
	 */
	public void appliquerChangements(ArrayList<List<String>> changements)
	{
		System.out.println("Changements : "+changements.size());
		List<String> keys = changements.get(0);
		
		for(String s : changements.get(0))
		{
			System.out.println(s);
		}
		
		for(int i=1;i<changements.size();i++)
		{
			unChangement(keys, changements.get(i));
		}
	}
}

