
public class Connexion {
	
	private String nom;
	private String adresseCo;
	private String port;
	private String mdp;

	public Connexion(String nom, String adresseCo, String port, String mdp) {
		
		this.nom = nom;
		this.adresseCo = adresseCo;
		this.port = port;
		this.mdp = mdp;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresseCo() {
		return adresseCo;
	}

	public void setAdresseCo(String adresseCo) {
		this.adresseCo = adresseCo;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
