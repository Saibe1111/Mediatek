package persistantdata;

import java.sql.*;
import java.util.List;

import mediatek2021.*;
import persistantdata.utilisateur.Bibliothécaire;


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
		//Connexion à la base de données
		Connection conn = getConnection();
		//On prépare la requête
        String sql = "SELECT * FROM Utilisateur WHERE Login=? AND Password=?";
        
        try {
        	//On prépare la requête
        	PreparedStatement query = conn.prepareStatement(sql);
        	//On remplit la requête préparé
        	query.setString(1, login);
        	query.setString(2, password);
        	//On exécute la requête préparé
            ResultSet rs    = query.executeQuery();
            
            //Si on a un résutat
            if(rs.next()) {
            	//On récupère le prénom
            	String prénom = rs.getString("Prénom");
            	//On créer et retourn un Utilisateur bibliothécaire
            	Utilisateur bibliothécaire = new Bibliothécaire(login,password,prénom);
        		return bibliothécaire;
            }
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
		}
		//Si on a pas trouvé on retourn null
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
	
	/**
	 * Permet d'obtenir une connection avec la base de donnée
	 * @param numDoc - Numéro du document que nous cherchons à supprimer.
	 * @throws SuppressException - À définir <------
	 */
	private Connection getConnection() {
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		Connection conn = null;
		
		try {
            //Liens vers la base de donnée
			
			//Sauvegarde de nos chemins:
			//Sébastien: jdbc:sqlite:/home/sebastien/Documents/Git/Mediatek/Database/db.db
			//Manil: 
			
            String url = "jdbc:sqlite:/home/sebastien/Documents/Java/Mediatek-CUVELLIER-RICHARD/Database/db.db";
            // Créaction de la connexion avec la base de données
            conn = DriverManager.getConnection(url);

            System.out.println("La connexion avec la base de données est un succès");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

		//On renvoie la connexion
		return conn;
	}

}
