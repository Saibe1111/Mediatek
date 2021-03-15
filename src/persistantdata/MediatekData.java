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
 * MediatekData récupère les information de la base de donnée.
 * Classe mono-instance : l'unique instance est connue de la bibliotheque via une injection de dépendance dans son bloc static
 */
public class MediatekData implements PersistentMediatek {
	
	//Sauvegarde de nos chemins:

	//Sébastien: jdbc:sqlite:/home/sebastien/Documents/Java/Mediatek-CUVELLIER-RICHARD/Database/db.db
	//Manil: jdbc:sqlite:C:\Users\manil\eclipseEE-workshop\ProjetMediatek\Database\db.db
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
	 * @param type - Le type de document que l'on veux.
	 * @return retourne la liste de tous les documents de la bibliothèque
	 */
	@Override
	public List<Document> catalogue(int type) {
		
		//Connexion à la base de données
		Connection conn = getConnection();
		
		List<Document> documents = new ArrayList<Document>(); 
		
		String sql = "";
		
		switch (type) {
		case 1:
			
			//Pour les livres
			sql = "SELECT * FROM Livre";
			
			try {
	        	//On prépare la requête
	        	PreparedStatement query = conn.prepareStatement(sql);
	        	//On exécute la requête préparé
	            ResultSet rs = query.executeQuery();
	            
	            while(rs.next())
	            {
	            	documents.add(new Livre(rs.getString("titre"), rs.getString("auteur"), rs.getString("codeBarre"), rs.getInt("emprunt")));
	            }
	            
	            return documents;
				
			} catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return null;
			}
		case 2:
			sql = "SELECT * FROM CD";
			
			try {
	        	//On prépare la requête
	        	PreparedStatement query = conn.prepareStatement(sql);
	        	//On exécute la requête préparé
	            ResultSet rs = query.executeQuery();
	            
	            while(rs.next())
	            {
	            	documents.add(new CD(rs.getString("titre"), rs.getString("auteur"), rs.getString("codeBarre"), rs.getInt("emprunt")));
	            }
	            
	            return documents;
				
			} catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return null;
			}
			
		case 3:
			sql = "SELECT * FROM DVD";	

			try {
	        	//On prépare la requête
	        	PreparedStatement query = conn.prepareStatement(sql);
	        	//On exécute la requête préparé
	            ResultSet rs = query.executeQuery();
	            
	            while(rs.next())
	            {
	            	documents.add(new DVD(rs.getString("titre"), rs.getString("auteur"), rs.getString("codeBarre"), rs.getInt("adulte"),rs.getInt("emprunt")));
	            }
	            
	            return documents;
				
			} catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return null;
			}
			
		default:
			return null;
		}
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
            	//On récupére le prénom
            	String prénom = rs.getString("prénom");
            	//On créer et retourn un Utilisateur Bibliothécaire
            	Utilisateur Bibliothécaire = new Bibliothécaire(login,password,prénom);
        		return Bibliothécaire;
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
		String codeBarre = String.valueOf(numDocument);
		Connection conn = getConnection();
		String sql = "SELECT * FROM Livre WHERE codeBarre=?";
		Document doc;
		try (PreparedStatement query = conn.prepareStatement(sql)) {
			query.setString(1, codeBarre);
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				doc = new Livre(rs.getString("titre"),rs.getString("auteur"), rs.getString("codeBarre"), 0 );
				return doc;
			}
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		sql = "SELECT * FROM CD WHERE codeBarre=?";
		try (PreparedStatement query = conn.prepareStatement(sql)) {
			query.setString(1, codeBarre);
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				doc = new CD(rs.getString("titre"),rs.getString("auteur"), rs.getString("codeBarre"), 0 );
				return doc;
			}
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		sql = "SELECT * FROM DVD WHERE codeBarre=?";
		try (PreparedStatement query = conn.prepareStatement(sql)) {
			query.setString(1, codeBarre);
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				doc = new DVD(rs.getString("titre"),rs.getString("auteur"), rs.getString("codeBarre"), rs.getInt("adulte"), 0 );
				return doc;
			}
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		return null;
		
	}

	// ajoute un nouveau document - exception à définir
	
	/**
	 * Permet de rajouter un document à la base de donnée.
	 * @param type - Permet de savoir le type du document. 1-> Livre 2-> DVD 3-> CD
	 * @param args - Contient les informations sur le document
	 * @throws NewDocException - à définir <------
	 */
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		
		for (Object object : args) {
			System.out.println(object);
		}
		
	    String titre = (String) args[1];
	    String auteur = (String) args[2];
	    String codeBarre = (String) args[3];
	    boolean adulte = (boolean) args[4];
		
	    if(titre=="" || auteur =="" || codeBarre == "") {
	    	throw new NewDocException("Il manque des champs");
	    }
	    
		//Connexion à la base de données
		Connection conn = getConnection();
		//On prépare la requête		
		String sql;
		
		
		
		switch (type) {
			case 1:
				sql = "INSERT INTO Livre (codeBarre, auteur, titre, emprunt) VALUES (?,?,?,?)";
				try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, codeBarre);
	            pstmt.setString(2, auteur);
	            pstmt.setString(3, titre);	         
	            pstmt.setInt(4, 0);
	            pstmt.executeUpdate();
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
				break;
			
			case 2:
				sql = "INSERT INTO DVD (codeBarre, auteur, titre, emprunt, adulte) VALUES (?,?,?,?,?)";
				try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, codeBarre);
		            pstmt.setString(2, auteur);
		            pstmt.setString(3, titre);
		            pstmt.setInt(4, 0);
		            if (adulte){
		            	pstmt.setInt(5, 1);
		            }else {
		            	pstmt.setInt(5, 0);
		            }		          
		            pstmt.executeUpdate();
			        } catch (SQLException e) {
			            System.out.println(e.getMessage());
			        }
				break;
				
			case 3:
				sql = "INSERT INTO CD (codeBarre, auteur, titre, emprunt) VALUES (?,?,?,?)";
				try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, codeBarre);
		            pstmt.setString(2, auteur);
		            pstmt.setString(3, titre);
		            pstmt.setInt(4, 0);
		            pstmt.executeUpdate();
			        } catch (SQLException e) {
			            System.out.println(e.getMessage());
			        }
				break;
		
		}
		
	}

	/**
	 * Permet de supprimer un document de la base de donnée.
	 * @param numDoc - Numéro du document que nous cherchons à supprimer.
	 * @throws SuppressException - à définir <------
	 */
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		
	}
	
	/**
	 * Permet d'obtenir une connection avec la base de donnée
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

		//On renvoie la connexion
		return conn;
	}

}
