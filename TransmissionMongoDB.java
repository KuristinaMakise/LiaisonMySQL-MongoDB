import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public class TransmissionMongoDB {
	
	private MongoCollection<Document> collection;
	
	public TransmissionMongoDB(MongoCollection<Document> collection)
	{
		this.collection = collection;
	}
	
	public Document listToDoc(List<String> keys, List<String> values)
	{
		Document doc = new Document(keys.get(0), values.get(0));
		for(int i=1;i<keys.size();i++)
		{
			doc.append(keys.get(i), values.get(i));
		}
		return doc;
	}
	
	public void ajouter(List<String> keys, List<String> values)
	{
		Document doc = listToDoc(keys, values);
		collection.insertOne(doc);
	}
	
	public void modifier(List<String> keys, List<String> values)
	{
		Document doc = listToDoc(keys, values);
		collection.findOneAndReplace(new Document("id",values.get(0)), doc);
	}
	
	public void envoi(List<String> keys, List<String> values)
	{
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(keys.get(0), values.get(0));
		Document doc = collection.find(whereQuery).first();
		if(doc == null)
		{
			ajouter(keys, values);
		}else{
			modifier(keys, values);
		}
	}
	
	public void transmission(ArrayList<List<String>> changements)
	{
		System.out.println(changements.size());
		List<String> keys = changements.get(0);
		
		for(int i=1;i<changements.size();i++)
		{
			envoi(keys, changements.get(i));
		}
	}

}
