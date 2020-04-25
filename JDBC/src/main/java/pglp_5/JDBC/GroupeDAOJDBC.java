package pglp_5.JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GroupeDAOJDBC extends DAO<Groupe>{
	
	private DAO<Personne1> persoJDBC;
	
	public GroupeDAOJDBC() throws SQLException, IOException {
		super();
		 persoJDBC = new DAOFactoryJDBC().getPersonne1DAO();
	}

	public void createtable() throws SQLException {
		 DatabaseMetaData dbmd = getConnect().getMetaData();
	        ResultSet rs = dbmd.getTables(null, null,
	                "Groupe".toUpperCase(), null);

	        Statement stmt = getConnect().createStatement();
	            if (!rs.next()) {
	            	stmt.executeUpdate("Create Table Groupe"
	                        + " (id_grp int primary key, nom varchar(30))");
	            }	
	            System.out.println("Table Groupe crée");
	            rs.close();
	            stmt.close();
	}
	
	@Override
	public Groupe create(Groupe obj) throws IOException, SQLException {
		 String insertgrp = ("insert into Groupe values ("
                 + "?, ? )");
         PreparedStatement update =getConnect().prepareStatement(insertgrp);
         update.setInt(1, obj.getId());
         update.setString(2, obj.getNom());
         update.executeUpdate();
         update.close();
 	     return obj;
	}

	@Override
	public Groupe find(int id) throws IOException, ClassNotFoundException {
		
	        return null;
		
           
	}

	@Override
	public Groupe update(Groupe obj) throws IOException, SQLException {
		 Statement stmt = getConnect().createStatement();
	     ResultSet result = stmt.executeQuery("select *"
	                    + "from Groupe where id_grp="
	                    + obj.getId());
	                if (result.next()) {
	                	this.delete(obj);
	                    this.create(obj);
	                    System.out.println("La mise à jour du Groupe effectuée");
	                }
	                stmt.close();
	               
        return obj;
	}

	@Override
	public void delete(Groupe obj) throws SQLException {
		Statement stmt = getConnect().createStatement();
		
        ResultSet rs = stmt.executeQuery("select * from Groupe where id_grp=" + obj.getId());
            if(rs.next()) {
           	  stmt.executeUpdate("delete from Groupe where id_grp="+obj.getId());
            	 System.out.printf("Ligne supprimée \n");
           
            }
            rs.close();
            stmt.close();
	}
	public void droptable() throws SQLException {
		Statement stmt = getConnect().createStatement();
        stmt.execute("DROP TABLE Groupe");
    	stmt.close();
    	System.out.println("Tables supprimées \n");
		
	}
	public void affichetable() throws SQLException {
		 DatabaseMetaData dbmd = getConnect().getMetaData();

	        ResultSet rs = dbmd.getTables(null, null,"Groupe".toUpperCase(), null);
	        Statement stmt = getConnect().createStatement();
	        
	    	rs = stmt.executeQuery("SELECT * FROM Groupe");

	         System.out.println("Table Groupe: \n");
	         System.out.println("id_grp\t nom");
	         while (rs.next()) {
	             System.out.printf("%d\t%s%n", rs.getInt("id_grp"),
	                     rs.getString("nom"));
	         }
	         rs.close();
	         stmt.close();
	}
	
	
}
