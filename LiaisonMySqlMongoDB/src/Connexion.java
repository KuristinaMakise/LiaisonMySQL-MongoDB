public class Connexion {

	private String adresseCo;
	private String port;
	private String login;
	private String mdp;
	private String bdd;
	private String nomBdd;
	private String nomTable;
	private Boolean etatCo;


	public Connexion() {


	}
	

	public Connexion(String adresseCo, String port, String bdd, String login, String mdp, String nomBdd, String nomTable, Boolean etatCo) {

		this.adresseCo = adresseCo;
		this.port = port;
		this.login = login;
		this.mdp = mdp;
		this.bdd = bdd;
		this.nomBdd = nomBdd;
		this.nomTable = nomTable;
		this.etatCo = etatCo;
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

	public String getBdd() {
		return bdd;
	}

	public void setBdd(String bdd) {
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
	
	public boolean getEtatCo()
	{
		return etatCo;
	}
	
	public void setEtatCo(boolean etatCo)
	{
		this.etatCo = etatCo;
	}
	
}