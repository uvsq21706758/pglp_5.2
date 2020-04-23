package pglp_5.JDBC;

import java.io.IOException;
import java.sql.SQLException;

public class DAOFactoryJDBC {
	
	 public static DAO<Numero_telephone> getNumero_telephoneDAO() throws IOException, SQLException{
		return new Numero_telephoneDAOJDBC();
		}
	 
	public static DAO<Personne1> getPersonne1DAO() throws IOException, SQLException{ 
			return new Personne1DAOJDBC();
		}
	
    public static DAO<Groupe> getGroupe() throws IOException, SQLException{
		        return new GroupeDAOJDBC();
		        
		 }
    
}
