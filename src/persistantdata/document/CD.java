package persistantdata.document;

import mediatek2021.Document;

/**
 * @version 1.0 - 28/02/2021
 * @author Manil RICHARD / Sébastien CUVELLIER
 * Class qui définit un CD.
 */
public class CD implements Document{
	
	private String titre;
	private String auteur;
	private String codebarre;
	
	
	/*
	 * Constructeur du CD.
	 * 
	 * @param titre, titre du CD.
	 * @param auteur, auteur du CD.
	 */
	public CD(String titre, String auteur, String code_barre) {
		this.titre = titre;
		this.auteur = auteur;
		this.codebarre = code_barre;
	}


	/*
	 * Guetteur du CD.
	 * 
	 * @return, un objet qui définit un CD.
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
