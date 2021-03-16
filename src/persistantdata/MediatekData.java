package persistantdata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mediatek2021.*;
import persistantdata.document.CD;
import persistantdata.document.DVD;
import persistantdata.document.Livre;
import persistantdata.utilisateur.Bibliothécaire;

/**
 * @version 1.2 - 15/03/2021
 * @author Jean-François Brette / Manil RICHARD / Sébastien CUVELLIER
 *         MediatekData récupère les information de la base de donnée. Classe
 *         mono-instance : l'unique instance est connue de la bibliotheque via
 *         une injection de dépendance dans son bloc static
 */
public class MediatekData implements PersistentMediatek {

	// Sauvegarde de nos chemins:

	// Sébastien:
	// jdbc:sqlite:/home/sebastien/Documents/Java/Mediatek-CUVELLIER-RICHARD/Database/db.db
	// Manil:
	// jdbc:sqlite:C:\Users\manil\eclipseEE-workshop\ProjetMediatek\Database\db.db
	private static String DATABASE_CHEMIN = "jdbc:sqlite:/home/sebastien/Documents/Java/Mediatek-CUVELLIER-RICHARD/Database/db.db";

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
	 * 
	 * @param type - Le type de document que l'on veux.
	 * @return retourne la liste de tous les documents de la bibliothèque
	 */
	@Override
	public List<Document> catalogue(int type) {

		// Connexion à la base de données
		Connection conn = getConnection();
		// On déclare une liste de documents.
		List<Document> documents = new ArrayList<Document>();

		String sql = "";

		switch (type) {
		case 1:
			sql = "SELECT * FROM Livre";
			break;
		case 2:
			sql = "SELECT * FROM CD";
			break;
		case 3:
			sql = "SELECT * FROM DVD";
			break;
		default:
			return null;
		}

		try {
			// On prépare la requête
			PreparedStatement query = conn.prepareStatement(sql);
			ResultSet rs = query.executeQuery();
			// On récupère toute les lignes de la base de données.
			while (rs.next()) {
				switch (type) {
				case 1:
					documents.add(
							new Livre(rs.getString("titre"), rs.getString("auteur"), rs.getString("codeBarre"), 0));
					break;
				case 2:
					documents.add(new CD(rs.getString("titre"), rs.getString("auteur"), rs.getString("codeBarre"), 0));
					break;
				case 3:
					documents.add(new DVD(rs.getString("titre"), rs.getString("auteur"), rs.getString("codeBarre"),
							rs.getInt("adulte"), 0));
					break;
				default:
					return null;
				}

			}
			// On renvoie la liste des documents.
			return documents;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Permet de récuper un utilisateur en provenance de la base de donnée.
	 * 
	 * @param login    - Login de l'utilisateur
	 * @param password - Mot de passe de l'utilisateur
	 * @return retourne l'utilisateur, si pas trouvé, renvoie null
	 */
	@Override
	public Utilisateur getUser(String login, String password) {
		// Connexion à la base de données
		Connection conn = getConnection();
		// On prépare la requête
		String sql = "SELECT * FROM Utilisateur WHERE Login=? AND Password=?";

		try {
			// On prépare la requête
			PreparedStatement query = conn.prepareStatement(sql);
			// On remplit la requête préparé
			query.setString(1, login);
			query.setString(2, password);
			// On exécute la requête préparé
			ResultSet rs = query.executeQuery();

			// Si on a un résutat
			if (rs.next()) {
				// On récupére le prénom
				String prénom = rs.getString("prénom");
				// On créer et retourn un Utilisateur Bibliothécaire
				Utilisateur Bibliothécaire = new Bibliothécaire(login, password, prénom);
				return Bibliothécaire;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Si on a pas trouvé on retourn null
		return null;
	}

	/**
	 * Permet de récuperer un document en particulier en provenance de la base de
	 * donnée.
	 * 
	 * @param numDocument - Numéro du document que nous cherchons.
	 * @return retourne le document, si pas trouvé, renvoie null.
	 */
	@Override
	public Document getDocument(int numDocument) {

		// On initialise un document
		Document document;

		document = getDocument(numDocument, 1);
		if (document != null) {
			return document;
		}

		document = getDocument(numDocument, 2);
		if (document != null) {
			return document;
		}

		document = getDocument(numDocument, 3);
		if (document != null) {
			return document;
		}

		return null;

	}

	/**
	 * Permet de récuperer un document en particulier en provenance de la base de
	 * donnée.
	 * 
	 * @param numDocument - Numéro du document que nous cherchons.
	 * @param type        - Type du document que nous cherchons.
	 * @return retourne le document, si pas trouvé, renvoie null.
	 */
	private Document getDocument(int numDocument, int type) {
		// On transforme notre int en string
		String codeBarre = String.valueOf(numDocument);
		// Connexion à la base de données
		Connection conn = getConnection();

		String sql = null;

		switch (type) {
		case 1:
			sql = "SELECT * FROM Livre WHERE codeBarre=?";
			break;
		case 2:
			sql = "SELECT * FROM CD WHERE codeBarre=?";
			break;
		case 3:
			sql = "SELECT * FROM DVD WHERE codeBarre=?";
			break;
		default:
			return null;
		}

		// On initialise un document
		Document document;

		try {
			// On prépare la requête
			PreparedStatement query = conn.prepareStatement(sql);
			query.setString(1, codeBarre);

			ResultSet rs = query.executeQuery();
			if (rs.next()) {

				switch (type) {
				case 1:
					document = new Livre(rs.getString("titre"), rs.getString("auteur"), rs.getString("codeBarre"), 0);
					return document;
				case 2:
					document = new CD(rs.getString("titre"), rs.getString("auteur"), rs.getString("codeBarre"), 0);
					return document;
				case 3:
					document = new DVD(rs.getString("titre"), rs.getString("auteur"), rs.getString("codeBarre"),
							rs.getInt("adulte"), 0);
					return document;
				default:
					return null;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	// ajoute un nouveau document - exception à définir

	/**
	 * Permet de rajouter un document à la base de donnée.
	 * 
	 * @param type - Permet de savoir le type du document.
	 * @param args - Contient les informations sur le document
	 * @throws NewDocException - Si les données ne permettent pas de créer un
	 *                         document
	 */
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {

		// On vérifie qu'on est en présence d'un type valable
		if (1 > type && type > 3) {
			throw new NewDocException("Merci de donner un type de document !");
		}

		// on défini nos variables
		String titre = (String) args[0];
		String auteur = (String) args[1];
		String codeBarre = (String) args[2];
		boolean adulte = (boolean) args[3];

		// On vérifie d'avoir de quoi ajouter le document
		if (titre == "" || auteur == "" || codeBarre == "") {
			throw new NewDocException("Le code barre, le titre et l'auteur sont des champs obligatoires !");
		}

		// On vérifie si le code barre est déjà utilisé (C'est nôtre clée primaire)

		if (getDocument(Integer.parseInt(codeBarre)) != null) {
			throw new NewDocException("Le code barre est déjà utilisé, le document est déjà rentré !");
		}

		// Connexion à la base de données
		Connection conn = getConnection();
		// On prépare la requête
		String sql = null;
		// On choisis l'instruction en fonction du type
		switch (type) {
		case 1:
			sql = "INSERT INTO Livre (codeBarre, auteur, titre, emprunt) VALUES (?,?,?,?)";
			break;
		case 2:
			sql = "INSERT INTO DVD (codeBarre, auteur, titre, emprunt, adulte) VALUES (?,?,?,?,?)";
			break;
		case 3:
			sql = "INSERT INTO CD (codeBarre, auteur, titre, emprunt) VALUES (?,?,?,?)";
			break;
		default:
			return;
		}

		try {
			// On remplis la requêtes avec les informations pour chaque type
			PreparedStatement pstmt = conn.prepareStatement(sql);
			switch (type) {
			case 1:
				pstmt.setString(1, codeBarre);
				pstmt.setString(2, auteur);
				pstmt.setString(3, titre);
				pstmt.setInt(4, 0);
				break;
			case 2:
				pstmt.setString(1, codeBarre);
				pstmt.setString(2, auteur);
				pstmt.setString(3, titre);
				pstmt.setInt(4, 0);
				break;
			case 3:
				pstmt.setString(1, codeBarre);
				pstmt.setString(2, auteur);
				pstmt.setString(3, titre);
				pstmt.setInt(4, 0);
				if (adulte) {
					pstmt.setInt(5, 1);
				} else {
					pstmt.setInt(5, 0);
				}
				break;
			default:
				break;
			}
			// On exectute la requête
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permet de supprimer un document de la base de donnée.
	 * 
	 * @param numDoc - Numéro du document que nous cherchons à supprimer.
	 * @throws SuppressException - Si le document n'existe pas
	 */
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		// On initialise un document
		Document document;

		document = getDocument(numDoc, 1);
		if (document != null) {
			suppressDoc(numDoc, 1);
			return;
		}

		document = getDocument(numDoc, 2);
		if (document != null) {
			suppressDoc(numDoc, 1);
			return;
		}

		document = getDocument(numDoc, 3);
		if (document != null) {
			suppressDoc(numDoc, 1);
			return;
		}
		
		throw new SuppressException("Ce code barre ne correspond à aucun document !");
	}

	/**
	 * Permet de supprimer un document de la base de donnée.
	 * 
	 * @param numDoc - Numéro du document que nous cherchons à supprimer.
	 * @throws SuppressException - Si le document n'existe pas
	 */
	public void suppressDoc(int numDoc, int type) throws SuppressException {

		// Connexion à la base de données
		Connection conn = getConnection();
		// On prépare la requête
		String sql = null;
		// On choisis l'instruction en fonction du type
		switch (type) {
		case 1:
			sql = "DELETE FROM Livre WHERE codeBarre=?";
			break;
		case 2:
			sql = "DELETE FROM DVD WHERE codeBarre=?";
			break;
		case 3:
			sql = "DELETE FROM CD WHERE codeBarre=?";
			break;
		default:
			return;
		}
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, String.valueOf(numDoc));
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permet d'obtenir une connection avec la base de donnée
	 * 
	 * @param numDoc - Numéro du document que nous cherchons à supprimer.
	 * @throws SuppressException - à définir <------
	 */
	private Connection getConnection() {

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		Connection conn = null;

		try {
			// Créaction de la connexion avec la base de données
			conn = DriverManager.getConnection(DATABASE_CHEMIN);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// On renvoie la connexion
		return conn;
	}

}
