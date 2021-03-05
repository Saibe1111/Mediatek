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
 * @author Jean-Fran�ois Brette / Manil RICHARD / S�bastien CUVELLIER
 * MediatekData r�cup�re les information de la base de donn�e.
 * Classe mono-instance : l'unique instance est connue de la bibliotheque via une injection de d�pendance dans son bloc static
 */
public class MediatekData implements PersistentMediatek {
	
	/**
	 * Injection dynamique de la d�pendance dans le package stable mediatek2021.
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
	 * Permet de r�cuperer une liste de document en provenance de la base de donn�e
	 * @param type - Le type de document que l'on veux.
	 * @return retourne la liste de tous les documents de la biblioth�que
	 */
	@Override
	public List<Document> catalogue(int type) {
		
		
		
		
		return null;
	}


	/**
	 * Permet de r�cuper un utilisateur en provenance de la base de donn�e
	 * @param login - Login de l'utilisateur
	 * @param password - Mot de passe de l'utilisateur
	 * @return retourne l'utilisateur, si pas trouv�, renvoie null
	 */
	@Override
	public Utilisateur getUser(String login, String password) {
		//Connexion à la base de donn�es
		Connection conn = getConnection();
		//On pr�pare la requête
        String sql = "SELECT * FROM Utilisateur WHERE Login=? AND Password=?";
        
        try {
        	//On pr�pare la requête
        	PreparedStatement query = conn.prepareStatement(sql);
        	//On remplit la requête pr�par�
        	query.setString(1, login);
        	query.setString(2, password);
        	//On ex�cute la requête pr�par�
            ResultSet rs    = query.executeQuery();
            
            //Si on a un r�sutat
            if(rs.next()) {
            	//On r�cup�re le prénom
            	String prénom = rs.getString("prénom");
            	//On cr�er et retourn un Utilisateur Bibliothécaire
            	Utilisateur Bibliothécaire = new Bibliothécaire(login,password,prénom);
        		return Bibliothécaire;
            }
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
		}
		//Si on a pas trouv� on retourn null
		return null;
	}

	/**
	 * Permet de r�cuperer un document en particulier en provenance de la base de donn�e
	 * @param numDocument - Num�ro du document que nous cherchons.
	 * @return retourne le document, si pas trouv�, renvoie null
	 */
	@Override
	public Document getDocument(int numDocument) {
		
		//Connexion à la base de donn�es
		Connection conn = getConnection();
		//On pr�pare la requête
		String sql = "SELECT * FROM Document WHERE code_barre=?";
		
		try {
			//On pr�pare la requête
		    PreparedStatement query = conn.prepareStatement(sql);
		    //On remplit la requête pr�par�
		    query.setInt(1, numDocument);
		    //On ex�cute la requête pr�par�
		    ResultSet rs = query.executeQuery();
		    
		    //Si on a un r�sutat
		    if(rs.next()) {
			    //On r�cup�re le prénom
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
		//Si on a pas trouv� on retourn null
		return null;
		
	}

	// ajoute un nouveau document - exception � d�finir
	
	/**
	 * Permet de rajouter un document à la base de donn�e.
	 * @param type - Permet de savoir le type du document. 1-> Livre 2-> DVD 3-> CD
	 * @param args - Contient les informations sur le document
	 * @throws NewDocException - � d�finir <------
	 */
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		
	    String codeBarre = (String) args[2];
	    String titre = (String) args[2];
	    String auteur = (String) args[2];
		
		//Connexion à la base de donn�es
		Connection conn = getConnection();
		//On pr�pare la requête
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
	 * Permet de supprimer un document de la base de donn�e.
	 * @param numDoc - Num�ro du document que nous cherchons � supprimer.
	 * @throws SuppressException - � d�finir <------
	 */
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		
	}
	
	/**
	 * Permet d'obtenir une connection avec la base de donn�e
	 * @param numDoc - Num�ro du document que nous cherchons à supprimer.
	 * @throws SuppressException - � d�finir <------
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
            //Liens vers la base de donn�e
			
			//Sauvegarde de nos chemins:

			//Sébastien: jdbc:sqlite:/home/sebastien/Documents/Git/Mediatek/Database/db.db
			//Manil: jdbc:sqlite:C:\Users\manil\Documents\GitHub\Mediatek\Database\db.db
			
            String url = "jdbc:sqlite:C:\\Users\\manil\\Documents\\GitHub\\Mediatek\\Database\\db.db";
            // Créaction de la connexion avec la base de données
            conn = DriverManager.getConnection(url);

            System.out.println("La connexion avec la base de donn�es est un succ�s");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

		//On renvoie la connexion
		return conn;
	}

}
