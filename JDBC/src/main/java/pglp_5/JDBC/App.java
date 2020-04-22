package pglp_5.JDBC;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import pglp_5.JDBC.Personne1.Builder;

public class App {

	  public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException  {
		   DAO<Numero_telephone> numjdbc;
		   Numero_telephone portable =new Numero_telephone(1,"062345667", "portable");
		   Numero_telephone portable1 =new Numero_telephone(2,"056778889", "fix");
		   numjdbc = DAOFactory.getNumero_telephoneDAO();
		   ((Numero_telephoneDAO) numjdbc).createtable();
		   numjdbc.create(portable);
		   numjdbc.create(portable1);
		   ((Numero_telephoneDAO) numjdbc).affichetable();
		   numjdbc.find(2);
		   numjdbc.delete(portable);
		   
		   DAO<Personne1> persojdbc;
		   Builder b = new Builder(1,"El gaamouss", "manale", "employee",LocalDate.of(1996, 8, 27));
		   persojdbc=DAOFactory.getPersonne1DAO();
		   b.Num_telephone(portable);
		   ((Personne1DAO) persojdbc).createtable();
		   Personne1 p=b.build();
		   persojdbc.create(p);
		   ((Personne1DAO) persojdbc).affichetable();
		   
	  }
}