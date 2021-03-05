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
	private Boolean emprunt;
	
	
	/*
	 * Constructeur du CD pour emprunt Boolean
	 * 
	 * @param titre, titre du CD.
	 * @param auteur, auteur du CD.
	 * @param emprunt, le CD est-il emprunté 
	 */
<<<<<<< Updated upstream
	public CD(String titre, String auteur) {
		this.titre = titre;
		this.auteur = auteur;
=======
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
	 * @param emprunt, le CD est-il emprunté. Integer traduit en boolean
	 */
	public CD(String titre, String auteur, String code_barre, Integer emprunt) {
		this.titre = titre;
		this.auteur = auteur;
		this.codebarre = code_barre;
		if (emprunt == 1) {
			this.emprunt = true;
		}
		else{
			this.emprunt = false;
		}

		
>>>>>>> Stashed changes
	}


	/*
	 * Guetteur du CD.
	 * 
	 * @return, un objet qui définit un CD.
	 */
	@Override
	public Object[] data() {
		Object[] o = new Object[4];
		o[0] = titre;
		o[1] = auteur;
		o[2] = codebarre;
		o[3] = emprunt;
		return o;
	}
	
}
