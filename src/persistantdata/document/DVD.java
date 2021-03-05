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
	 */

	public DVD(String titre, String auteur, String code_barre, Boolean adulte, Boolean emprunt) {
		this.titre = titre;
		this.auteur = auteur;
		this.codebarre = code_barre;
		
		this.emprunt = emprunt;
	}


	/*
	 * Guetteur du DVD.
	 * 
	 * @return, un objet qui définit un DVD.
	 */
	@Override
	public Object[] data() {
		Object[] o = new Object[3];
		o[0] = titre;
		o[1] = auteur;
		o[2] = codebarre;
		return o;
	}
	
}