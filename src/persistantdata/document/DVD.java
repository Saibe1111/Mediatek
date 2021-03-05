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
	
	
	/*
	 * Constructeur du DVD.
	 * 
	 * @param titre, titre du DVD.
	 * @param auteur, auteur du DVD.
	 */
	public DVD(String titre, String auteur) {
		this.titre = titre;
		this.auteur = auteur;
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