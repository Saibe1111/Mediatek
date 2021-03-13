package persistantdata.document;

import mediatek2021.Document;

/**
 * @version 1.0 - 02/03/2021
 * @author Manil RICHARD / Sébastien CUVELLIER
 * Class qui définit un livre.
 */
public class Livre implements Document {

	private String titre;
	private String auteur;
	private String codebarre;
	private Boolean emprunt;
	
	/*
	 * Constructeur du Livre pour emprunt Boolean
	 * 
	 * @param titre, titre du livre.
	 * @param auteur, auteur du livre.
	 * @param emprunt, le livre est-il emprunté 
	 * @param code_barre, code barre du livre, c'est une clé unique.
	 */


	public Livre(String titre, String auteur, String code_barre, Boolean emprunt) {
		this.titre = titre;
		this.auteur = auteur;
		this.codebarre = code_barre;
		this.emprunt = emprunt;
	}
	
	/*
	 * Constructeur du Livre pour emprunt Integer
	 * 
	 * @param titre, titre du livre.
	 * @param auteur, auteur du livre.
	 * @param code_barre, code barre du livre, c'est une clé unique.
	 * @param emprunt, le livre est-il emprunté. Integer traduit en boolean
	 */
	public Livre(String titre, String auteur, String code_barre, Integer emprunt) {
		this.titre = titre;
		this.auteur = auteur;
		this.codebarre = code_barre;
		if (emprunt == 1) {
			this.emprunt = true;
		}
		else{
			this.emprunt = false;
		}

	}


	/*
	 * Guetteur du livre.
	 * 
	 * @return, un objet qui définit un livre.
	 */
	@Override
	public Object[] data() {
		Object[] o = new Object[5];
		o[0] = "Livre";
		o[1] = titre;
		o[2] = auteur;
		o[3] = codebarre;
		o[4] = emprunt;
		return o;
	}
	
}