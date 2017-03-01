public class Connexion {

	private String nom;
	private String adresseCo;
	private String port;
	private String login;
	private String mdp;
	private TypeBdd bdd;
	private String nomBdd;
	private String nomTable;


	public Connexion() {


	}
	public Connexion(String adresseCo, String port, TypeBdd bdd, String nom) {

		this.adresseCo = adresseCo;
		this.port = port;
		this.bdd = bdd;
	}

	public Connexion(String adresseCo, String port, TypeBdd bdd, String nom, String login, String mdp, String nomBdd, String nomTable) {

		this.adresseCo = adresseCo;
		this.port = port;
		this.login = login;
		this.mdp = mdp;
		this.bdd = bdd;
		this.nomBdd = nomBdd;
		this.nomTable = nomTable;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public TypeBdd getBdd() {
		return bdd;
	}

	public void setBdd(TypeBdd bdd) {
		this.bdd = bdd;
	}

	public String getNomBdd() {
		return nomBdd;
	}

	public void setNomBdd(String nomBdd) {
		this.nomBdd = nomBdd;
	}

	public String getNomTable() {
		return nomTable;
	}

	public void setNomTable(String nomTable) {
		this.nomTable = nomTable;
	}
}