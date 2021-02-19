package persistantdata;

import java.util.List;

import mediatek2021.*;


/**
 * @version 1.1 - 19/02/2021
 * @author Jean-François Brette / Manil RICHARD / Sébastien CUVELLIER
 * MediatekData récupère les information de la base de donnée.
 * Classe mono-instance : l'unique instance est connue de la bibliotheque via une injection de dépendance dans son bloc static
 */
public class MediatekData implements PersistentMediatek {
	
	/**
	 * Injection dynamique de la dépendance dans le package stable mediatek2021.
	 */
	static {
		Mediatek.getInstance().setData(new MediatekData());
	}

	/**
	 * On bloque l'instanciation.
	 */
	private MediatekData() {
	}

	
	/**
	 * Permet de récuperer une liste de document en provenance de la base de donnée
	 * @param type - Le type de document que l'on veux.
	 * @return retourne la liste de tous les documents de la bibliothèque
	 */
	@Override
	public List<Document> catalogue(int type) {
		return null;
	}


	/**
	 * Permet de récuper un utilisateur en provenance de la base de donnée
	 * @param login - Login de l'utilisateur
	 * @param password - Mot de passe de l'utilisateur
	 * @return retourne l'utilisateur, si pas trouvé, renvoie null
	 */
	@Override
	public Utilisateur getUser(String login, String password) {
		return null;
	}

	/**
	 * Permet de récuperer un document en particulier en provenance de la base de donnée
	 * @param numDocument - Numéro du document que nous cherchons.
	 * @return retourne le document, si pas trouvé, renvoie null
	 */
	@Override
	public Document getDocument(int numDocument) {
		return null;
	}

	// ajoute un nouveau document - exception à définir
	
	/**
	 * Permet de rajouter un document à la base de donnée.
	 * @param type - Permet de savoir le type du document.
	 * @param args - Contient les informations sur le document
	 * @throws NewDocException - À définir <------
	 */
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc en fonction du type et des infos optionnelles
	}

	/**
	 * Permet de supprimer un document de la base de donnée.
	 * @param numDoc - Numéro du document que nous cherchons à supprimer.
	 * @throws SuppressException - À définir <------
	 */
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		
	}

}
