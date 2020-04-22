package pglp_5.JDBC;

import java.io.IOException;
import java.sql.SQLException;

public class App {

	  public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException  {
		   DAO<Numero_telephone> numjdbc;
		   Numero_telephone portable =new Numero_telephone(1,"062345667", "portable");
		   Numero_telephone portable1 =new Numero_telephone(2,"056778889", "fix");
		   numjdbc = DAOFactory.getNumero_telephoneDAO();
		  // numjdbc.create(portable);
		  // numjdbc.create(portable1);
		   numjdbc.find(1);
		  // numjdbc.delete(portable);
	  }
}