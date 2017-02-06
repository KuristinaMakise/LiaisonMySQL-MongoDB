import java.util.ArrayList;
import java.util.List;

public class Main
{
	public static void main(String args[])
	{
		ConnexionMySQL cMySQL = new ConnexionMySQL();
		ConnexionMongoDB cMongoDB = new ConnexionMongoDB();
		
		
		TransmissionMongoDB t = new TransmissionMongoDB(cMongoDB.getCollection());
		ArrayList<List<String>> changements = new ArrayList<List<String>>();
		List<String> ls = new ArrayList<String>();
		
		for(String nom : cMySQL.nomColonnes)
		{
			ls.add(nom);
		}
		
		changements.add(ls);

		ls = new ArrayList<String>();
		
		for(List<String> listeString : cMySQL.donneesPourMongoDB)
		{
			for(String value : listeString)
			{
				ls.add(value);
			}
			
			changements.add(ls);
			ls = new ArrayList<String>();
		}
		
		//System.out.println(changements.get(0).size());
		
		/*changements.add(ls);
		ls = new ArrayList<String>();
		ls.add("3");
		ls.add("X");
		changements.add(ls);
		ls = new ArrayList<String>();
		ls.add("4");
		ls.add("A");
		changements.add(ls);
		ls = new ArrayList<String>();
		ls.add("3");
		ls.add("B");
		changements.add(ls);*/
		t.transmission(changements);
		cMongoDB.printDB();
		cMongoDB.arret();
	}
}