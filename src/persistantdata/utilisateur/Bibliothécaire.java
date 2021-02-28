package persistantdata.utilisateur;

import mediatek2021.Utilisateur;

/**
 * @version 1.0 - 28/02/2021
 * @author Manil RICHARD / Sébastien CUVELLIER
 * Class qui définit un bibliothécaire.
 */
public class Bibliothécaire implements Utilisateur {
	
	private String login;
	private String password;
	private String prénom;

	/*
	 * Constructeur de  bibliothécaire.
	 * 
	 * @param login, login du bibliothécaire.
	 * @param password, mot de passe du bibliothécaire.
	 * @param prénom, Prénom du bibliothécaire.
	 */
	public Bibliothécaire(String login, String password, String prénom) {
		this.login = login;
		this.password = password;
		this.prénom = prénom;
	}

	/*
	 * Guetteur du login.
	 * 
	 * @return, login du bibliothécaire.
	 */
	@Override
	public String login() {
		return this.login;
	}
	
	/*
	 * Guetteur du mot de passe.
	 * 
	 * @return, mot de passe du bibliothécaire.
	 */
	@Override
	public String password() {
		return this.password;
	}
	
	/*
	 * Guetteur du reste des éléments du bibliothécaire.
	 * 
	 * @return, un objet qui définit le bibliothécaire. Ici le prénom.
	 */
	@Override
	public Object[] data() {
		Object[] o = new Object[1];
		o[0] = prénom;
		return o;
	}

}
