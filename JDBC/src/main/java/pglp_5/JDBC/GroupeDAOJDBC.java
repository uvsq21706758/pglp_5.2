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
	
	
	public void createpersoingroup(int idgrp,int idperso) throws SQLException {
		DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,
                "persoingroupe".toUpperCase(), null);

        Statement stmt = getConnect().createStatement();
            if (!rs.next()) {
                stmt.executeUpdate("Create table"
                        + " persoingroupe"
                        + " (id_grp int,"
                        + "id_perso int, "
                        + "primary key (id_grp, id_perso),"
                        + "foreign key (id_grp) references"
                        + " Groupe(id_grp), "
                        + "foreign key (id_perso)"
                        + "references Personnes(id_perso))");
            }
                stmt.executeUpdate("insert into"
                        + " persoingroupe values ("
                        + idgrp + "," + idperso + ")");
                    rs.close();
                    stmt.close();
	}


	public void creategrpingrp(int idgrp,int idsousgrp) throws SQLException {
		DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,
                "groupeingrp".toUpperCase(), null);

        Statement stmt = getConnect().createStatement();
            if (!rs.next()) {
                stmt.executeUpdate("Create table"
                        + " groupeingrp"
                        + "( id_grp int,"
                        + "id_sousgrp int, "
                        + "primary key (id_grp, id_sousgrp),"
                        + "foreign key (id_grp) references"
                        + " Groupe(id_grp), "
                        + "foreign key (id_sousgrp)"
                        + " references Groupe(id_grp))");
            }
                stmt.executeUpdate("insert into"
                        + " groupeingrp values ("
                        + idgrp + "," + idsousgrp + ")");
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
         
         Personne1 p;
         Groupe gp;
        for (Composite comp : obj.getPersonnes()) {
            if (comp instanceof Personne1) {
                 p = (Personne1) comp;
                 ((Personne1DAOJDBC) persoJDBC).createtable();
                 persoJDBC.create(p);
                 this.createpersoingroup(obj.getId(),p.getId());
               } else {
                 gp = (Groupe) comp;
                 this.create(gp);
                this.creategrpingrp(obj.getId(), gp.getId());
                    }
         }
	        return obj;
	}

	@Override
	public Groupe find(int id) throws IOException, ClassNotFoundException, SQLException {
		Groupe grp = null;
        Statement stmt = getConnect().createStatement();
        ResultSet rs = stmt.executeQuery("select * from Groupe"
                            + " where id_grp=" + id);
                    System.out.println("La ligne rechercher dans la table Groupe: \n");
	                System.out.println("id_grp\t nom");
                        if (rs.next()) {
                        	 System.out.printf("%d\t%s%n", rs.getInt("id_grp"),
     	                            rs.getString("nom"));
                        rs.close();
                        stmt.close();
                    }
       return grp;
           
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
            	stmt.executeUpdate("delete from groupeingrp where id_grp="+obj.getId());
            	stmt.executeUpdate("delete from persoingroupe where id_grp="+obj.getId());
           	  stmt.executeUpdate("delete from Groupe where id_grp="+obj.getId());
            	 System.out.printf("Ligne supprimée \n");
           
            }
            rs.close();
            stmt.close();
	}
	public void droptable() throws SQLException {
		Statement stmt = getConnect().createStatement();
		stmt.execute("DROP TABLE persoingroupe");
		stmt.execute("DROP TABLE groupeingrp");
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
	public void affichegrpparsousgrp() throws SQLException {
		 DatabaseMetaData dbmd = getConnect().getMetaData();
         ResultSet rsEx = dbmd.getTables(null, null,"groupeingrp".toUpperCase(),
                 null);
         if (rsEx.next()) {
             Statement stmt = getConnect().createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT *"
                         + " FROM groupeingrp");
                     System.out.println("Table groupeingrp:\n");
                     System.out.println("id_grp\t id_sousgrp\t");
                     while (rs.next()) {
                         System.out.printf("%d\t%s%n",
                                 rs.getInt("id_grp"),
                                 rs.getInt("id_sousgrp"));
                     }
                     
                     rs.close();
                 }	
		
	}
	public void affichepersopargrp() throws SQLException {
		 DatabaseMetaData dbmd = getConnect().getMetaData();
         ResultSet rsEx = dbmd.getTables(null, null,"persoingroupe".toUpperCase(),
                 null);
         if (rsEx.next()) {
             Statement stmt = getConnect().createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT *"
                         + " FROM persoingroupe");
                     System.out.println("Table persoingroupe:\n");
                     System.out.println("id_grp\t id_perso\t");
                     while (rs.next()) {
                         System.out.printf("%d\t%s%n",
                                 rs.getInt("id_grp"),
                                 rs.getInt("id_perso"));
                     }
                     
                     rs.close();
                 }	
	}
}
