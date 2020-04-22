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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Personne1DAO extends DAO<Personne1>{
	
	public Personne1DAO() throws SQLException {
		super();
	}
	
	public void createtable() throws SQLException {
   	 DatabaseMetaData dbmd = getConnect().getMetaData();
	        ResultSet rs = dbmd.getTables(null, null,"Personnes".toUpperCase(), null);
	        String createperso="CREATE TABLE Personnes("
                + "id_perso int,"
                + "nom varchar(30),"
                + "prenom varchar(30),"
                +"fonction varchar(30),"
                +"date_naissance varchar(30),"
                + "PRIMARY KEY (id_perso)"
                + ")";
	         Statement stmt = getConnect().createStatement();
	            if (!rs.next()) {
	            	stmt.execute(createperso);
	            }	
	            System.out.println("Table Personnes crée");
	            rs.close();
	            stmt.close();
   }
	 public void affichetable() throws SQLException {
	    	DatabaseMetaData dbmd = getConnect().getMetaData();
	        ResultSet rs = dbmd.getTables(null, null,"Personnes".toUpperCase(), null);
	        Statement stmt = getConnect().createStatement();
	        
	    	rs = stmt.executeQuery("SELECT * FROM Personnes");

	         System.out.println("Table Personnes: \n");
	         System.out.println("id_perso\t nom\t prenom\t fonction\t date naissance");
	         while (rs.next()) {
	             System.out.printf("%d\t%s \t%s\t %s\t %s%n", rs.getInt("id_perso"),
	                     rs.getString("nom"), rs.getString("prenom"),
	                     rs.getString("fonction"),rs.getString("date_naissance"));
	         }
	         rs.close();
	         stmt.close();
	      
	    }

	public Personne1 create(Personne1 obj) throws IOException, SQLException{
		 String insertperso =("insert into Personnes"
                 + " values (?, ?, ?,?,?)");
         PreparedStatement prpstmt = getConnect().prepareStatement(insertperso);

         prpstmt.setInt(1, obj.getId());
         prpstmt.setString(2, obj.getNom());
         prpstmt.setString(3, obj.getPrenom());
         prpstmt.setString(4, obj.getFonction());
         prpstmt.setDate(5, Date.valueOf(obj.getDate_naissance()));
         prpstmt.executeUpdate();
         prpstmt.close();
         System.out.println("\n ligne insérer est bien enregistée ");
      
        return obj;
	}

	public Personne1 find(int id) throws IOException, ClassNotFoundException, SQLException{
		Personne1 perso = null;
        Statement stmt = getConnect().createStatement();
        ResultSet rs = stmt.executeQuery("select * from Personnes"
                            + " where id_num=" + id);
                    System.out.println("La ligne rechercher dans la table Personnes: \n");
	                System.out.println("id_perso\t nom\t prenom\t fonction\t date naissance");
                        if (rs.next()) {
                        	 System.out.printf("%d\t%s \t%s\t %s\t %s%n", rs.getInt("id_perso"),
            	                     rs.getString("nom"), rs.getString("prenom"),
            	                     rs.getString("fonction"),rs.getString("date_naissance"));
            	         
                        rs.close();
                        stmt.close();
                    }
       return perso;
        
    }
	

	public Personne1 update(Personne1 obj) throws IOException, SQLException {
		Statement stmt = getConnect().createStatement();
	    ResultSet result = stmt.executeQuery("select * from Personnes where id_perso=" + obj.getId());
	                if (result.next()) {
	                	this.delete(obj);
	                    this.create(obj);
	                    System.out.println("La mise à jour du table Personnes effectuée");
	                }
	                stmt.close();
	                return obj;
	}

	public void delete(Personne1 obj) throws SQLException {
		Statement stmt = getConnect().createStatement();
        ResultSet rs = stmt.executeQuery("select * from Personnes where id_perso=" + obj.getId());
            if (rs.next()) {
           	  stmt.executeUpdate("delete from Personnes where id_perso="+obj.getId());
            	 System.out.printf("Ligne supprimée \n");
            rs.close();
            stmt.close();
            }
	}

}
