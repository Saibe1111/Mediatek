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
	
	/*
	 * Constructeur du livre.
	 * 
	 * @param titre, titre du livre.
	 * @param auteur, auteur du livre.
	 */
	public Livre(String titre, String auteur, String code_barre) {
		this.titre = titre;
		this.auteur = auteur;
		this.codebarre = code_barre;
	}


	/*
	 * Guetteur du livre.
	 * 
	 * @return, un objet qui définit un livre.
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