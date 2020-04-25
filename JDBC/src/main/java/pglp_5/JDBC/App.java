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
		   numjdbc = DAOFactoryJDBC.getNumero_telephoneDAO();
		  ((Numero_telephoneDAOJDBC) numjdbc).createtable();
		  // numjdbc.create(portable);
		 // numjdbc.create(portable1);
		  //numjdbc.update(portable1);
		 
		  
		   //numjdbc.delete(portable);
		  
		   
		  DAO<Personne1> persojdbc;
		    Builder b = new Builder(1,"El gaamouss", "manale", "employee",LocalDate.of(1996, 8, 27));
		   persojdbc=DAOFactoryJDBC.getPersonne1DAO();
		   b.Num_telephone(portable);
		 // ((Personne1DAOJDBC) persojdbc).createtable();
		   Personne1 employee=b.build();
		 // persojdbc.create(employee);
		 // numjdbc.find(1);
		//   persojdbc.find(1);
		  // ((Personne1DAOJDBC) persojdbc).affichetable();
		   //((Numero_telephoneDAOJDBC) numjdbc).affichetable();
		   //((Personne1DAOJDBC) persojdbc).createassoc(p,portable);
		  // ((Personne1DAOJDBC) persojdbc).afficheassoc();
		   //persojdbc.delete(p);*/
		   
		   DAO<Groupe> grpJDBC =new DAOFactoryJDBC().getGroupe();
		
	       
	        Groupe departement =new Groupe(1,"Departement");
	        Groupe service =new Groupe(2, "Service");
	        departement.add(employee);
	        ((GroupeDAOJDBC) grpJDBC).createtable();
	        grpJDBC.create(departement);
	       // grpJDBC.create(service);	
	        ((GroupeDAOJDBC) grpJDBC).affichetable();
	        ((GroupeDAOJDBC) grpJDBC).affichepersopargrp();


		   
	  }
}