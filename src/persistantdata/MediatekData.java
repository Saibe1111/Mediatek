package persistantdata;

import java.sql.*;
import java.util.List;

import mediatek2021.*;
import persistantdata.document.CD;
import persistantdata.document.DVD;
import persistantdata.document.Livre;
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
		//Connexion Ã  la base de données
		Connection conn = getConnection();
		//On prépare la requÃªte
        String sql = "SELECT * FROM Utilisateur WHERE Login=? AND Password=?";
        
        try {
        	//On prépare la requÃªte
        	PreparedStatement query = conn.prepareStatement(sql);
        	//On remplit la requÃªte préparé
        	query.setString(1, login);
        	query.setString(2, password);
        	//On exécute la requÃªte préparé
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
		
		//Connexion Ã  la base de données
		Connection conn = getConnection();
		//On prépare la requÃªte
		String sql = "SELECT * FROM Document WHERE code_barre=?";
		
		try {
			//On prépare la requÃªte
		    PreparedStatement query = conn.prepareStatement(sql);
		    //On remplit la requÃªte préparé
		    query.setInt(1, numDocument);
		    //On exécute la requÃªte préparé
		    ResultSet rs = query.executeQuery();
		    
		    //Si on a un résutat
		    if(rs.next()) {
			    //On récupère le prénom
			    String titre = rs.getString("titre");
			    String auteur = rs.getString("auteur");
			    String type_document = rs.getString("type_document");
			    String code_barre = rs.getString("code_barre");
			    
			    switch (Integer.parseInt(type_document)) {
				case 1:
					Document livre = new Livre(titre, auteur, code_barre);
					return livre;
				case 2:
					Document cd = new CD(titre, auteur, code_barre);
					return cd;
				case 3:
					Document dvd = new DVD(titre, auteur, code_barre);
					return dvd;
				default:
					return null;
				}
			    
		    }
		    
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		//Si on a pas trouvé on retourn null
		return null;
		
	}

	// ajoute un nouveau document - exception à définir
	
	/**
	 * Permet de rajouter un document Ã  la base de donnée.
	 * @param type - Permet de savoir le type du document. 1-> Livre 2-> DVD 3-> CD
	 * @param args - Contient les informations sur le document
	 * @throws NewDocException - à définir <------
	 */
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		
	    String codeBarre = (String) args[2];
	    String titre = (String) args[2];
	    String auteur = (String) args[2];
		
		//Connexion Ã  la base de données
		Connection conn = getConnection();
		//On prépare la requÃªte
		String sql = "INSERT INTO Document (type_document, auteur, titre, code_barre) VALUES (?,?,?,?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, type);
            pstmt.setString(2, auteur);
            pstmt.setString(3, titre);
            pstmt.setString(4, codeBarre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		//throw new NewDocException("Type de document inconnu !");
		
		
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc en fonction du type et des infos optionnelles
	}

	/**
	 * Permet de supprimer un document de la base de donnée.
	 * @param numDoc - Numéro du document que nous cherchons à  supprimer.
	 * @throws SuppressException - à définir <------
	 */
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		
	}
	
	/**
	 * Permet d'obtenir une connection avec la base de donnée
	 * @param numDoc - Numéro du document que nous cherchons Ã  supprimer.
	 * @throws SuppressException - à définir <------
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
<<<<<<< Updated upstream
			//Sébastien: jdbc:sqlite:/home/sebastien/Documents/Git/Mediatek/Database/db.db
			//Manil: 
			
            String url = "jdbc:sqlite:/home/sebastien/Documents/Java/Mediatek-CUVELLIER-RICHARD/Database/db.db";
            // Créaction de la connexion avec la base de données
=======
			//SÃ©bastien: jdbc:sqlite:/home/sebastien/Documents/Git/Mediatek/Database/db.db
			//Manil: jdbc:sqlite:C:\Users\manil\Documents\GitHub\Mediatek\Database\db.db
			
            String url = "jdbc:sqlite:C:\\Users\\manil\\Documents\\GitHub\\Mediatek\\Database\\db.db";
            // CrÃ©action de la connexion avec la base de donnÃ©es
>>>>>>> Stashed changes
            conn = DriverManager.getConnection(url);

            System.out.println("La connexion avec la base de données est un succès");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

		//On renvoie la connexion
		return conn;
	}

}
