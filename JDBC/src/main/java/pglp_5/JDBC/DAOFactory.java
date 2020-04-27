package pglp_5.JDBC;

import java.io.IOException;

public class DAOFactory extends AbstractDAOFactory{
	 
	 public static DAO<Numero_telephone> getNumero_telephoneDAO() throws IOException{
		return new Numero_telephoneDAO();
		}
	 
	public static DAO<Personne1> getPersonne1DAO() throws IOException{ 
			return new Personne1DAO();
		}
	
    public static DAO<Groupe> getGroupe() throws IOException{
		        return new GroupeDAO();
		        
		 }
    
}
