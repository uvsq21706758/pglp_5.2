package pglp_5.JDBC;

import java.io.IOException;
import java.sql.SQLException;

public class DAOFactory {
	
	 public static DAO<Numero_telephone> getNumero_telephoneDAO() throws IOException, SQLException{
		return new Numero_telephoneDAO();
		}
	 
	public static DAO<Personne1> getPersonne1DAO() throws IOException, SQLException{ 
			return new Personne1DAO();
		}
	
    public static DAO<Groupe> getGroupe() throws IOException, SQLException{
		        return new GroupeDAO();
		        
		 }
    
}
