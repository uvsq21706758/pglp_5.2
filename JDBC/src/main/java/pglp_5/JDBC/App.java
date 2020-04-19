package pglp_5.JDBC;

import java.io.IOException;
import java.sql.SQLException;

public class App {

	  public static void main(String[] args) throws IOException, SQLException  {
		   DAO<Numero_telephone> numjdbc;
		   Numero_telephone portable =new Numero_telephone(1, "0687447778", "portable");
		   numjdbc = DAOFactory.getNumero_telephoneDAO();
		   numjdbc.create(portable);
	  }
}