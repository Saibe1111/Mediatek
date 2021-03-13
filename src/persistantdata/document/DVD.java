package persistantdata.document;

import mediatek2021.Document;

/**
 * @version 1.0 - 28/02/2021
 * @author Manil RICHARD / Sébastien CUVELLIER
 * Class qui définit un DVD.
 */
public class DVD implements Document {

	private String titre;
	private String auteur;
	private String codebarre;
	private Boolean adulte;
	private Boolean emprunt;
	
	/*
	 * Constructeur du DVD.
	 * 
	 * @param titre, titre du DVD.
	 * @param auteur, auteur du DVD.
	 * @param code_barre, code barre du DVD, c'est une clé unique.
	 * @param adulte, si le DVD est réservé au adulte.
	 * @param emprunt, le DVD est-il emprunté.
	 */

	public DVD(String titre, String auteur, String code_barre, Boolean adulte, Boolean emprunt) {
		this.titre = titre;
		this.auteur = auteur;
		this.codebarre = code_barre;
		this.emprunt = emprunt;
		
	}
	/*
	 * Constructeur du DVD.
	 * 
	 * @param titre, titre du DVD.
	 * @param auteur, auteur du DVD.
	 * @param code_barre, code barre du DVD, c'est une clé unique.
	 * @param adulte, si le DVD est réservé au adulte.
	 * @param emprunt, le DVD est-il emprunté. Integer traduit en boolean
	 */
	public DVD(String titre, String auteur, String code_barre, Integer adulte, Integer emprunt) {
		this.titre = titre;
		this.auteur = auteur;
		this.codebarre = code_barre;
		
		if (adulte == 1) {
			this.adulte = true;
		}
		else{
			this.adulte = false;
		}
		
		if (emprunt == 1) {
			this.emprunt = true;
		}
		else{
			this.emprunt = false;
		}
		
	}


	/*
	 * Guetteur du DVD.
	 * 
	 * @return, un objet qui définit un DVD.
	 */
	@Override
	public Object[] data() {
		Object[] o = new Object[6];
		o[0] = "DVD";
		o[1] = titre;
		o[2] = auteur;
		o[3] = codebarre;
		o[4] = emprunt;
		o[5] = adulte;
		return o;
	}
	
}