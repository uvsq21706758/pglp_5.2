package pglp_5.JDBC;

import java.io.IOException;
import java.sql.SQLException;

public class DAOFactoryJDBC extends AbstractDAOFactory{
	
	 public static DAOJDBC<Numero_telephone> getNumero_telephoneDAO() throws IOException, SQLException{
		return new Numero_telephoneDAOJDBC();
		}
	 
	public static DAOJDBC<Personne1> getPersonne1DAO() throws IOException, SQLException{ 
			return new Personne1DAOJDBC();
		}
	
    public static DAOJDBC<Groupe> getGroupe() throws IOException, SQLException{
		        return new GroupeDAOJDBC();
		        
		 }
   
    
}
