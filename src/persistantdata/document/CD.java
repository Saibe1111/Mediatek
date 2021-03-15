package persistantdata.document;

import mediatek2021.Document;

/**
 * @version 1.1 - 15/03/2021
 * @author Manil RICHARD / Sébastien CUVELLIER
 * Class qui définit un CD.
 */
public class CD implements Document{
	
	private String titre;
	private String auteur;
	private String codebarre;
	private Boolean emprunt;
	
	
	/*
	 * Constructeur du CD pour emprunt Boolean
	 * 
	 * @param titre, titre du CD.
	 * @param auteur, auteur du CD.
	 * @param code_barre, code barre du CD, c'est une clé unique.
	 * @param emprunt, le CD est-il emprunté.
	 */


	public CD(String titre, String auteur, String code_barre, Boolean emprunt) {
		this.titre = titre;
		this.auteur = auteur;
		this.codebarre = code_barre;
		this.emprunt = emprunt;
	}
	
	/*
	 * Constructeur du CD pour emprunt Integer
	 * 
	 * @param titre, titre du CD.
	 * @param auteur, auteur du CD.
	 * @param code_barre, code barre du CD, c'est une clé unique.
	 * @param emprunt, le CD est-il emprunté. Integer traduit en boolean
	 */
	public CD(String titre, String auteur, String code_barre, Integer emprunt) {
		this.titre = titre;
		this.auteur = auteur;
		this.codebarre = code_barre;
		
		this.emprunt = (emprunt == 1);
	}


	/*
	 * Guetteur du CD.
	 * 
	 * @return, un objet qui définit un CD.
	 */
	@Override
	public Object[] data() {
		Object[] o = new Object[5];
		o[0] = "CD";
		o[1] = titre;
		o[2] = auteur;
		o[3] = codebarre;
		o[4] = emprunt;
		return o;
	}
	
}
