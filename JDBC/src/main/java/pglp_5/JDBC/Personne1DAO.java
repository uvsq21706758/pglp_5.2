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

	public Personne1 create(Personne1 obj) throws IOException{
		 FileOutputStream fos = new FileOutputStream(obj.getNom());
	      ObjectOutputStream oos = new ObjectOutputStream(fos);
	      oos.writeObject(obj);
	      oos.close();
	        System.out.println("Le fichier est créé!");
	        return obj;
	}

	public Personne1 find(int id) throws IOException, ClassNotFoundException{
		//File f = new File(id);
	    Personne1 p = null;
	   /* Object deserialized = null;
            if (f.exists()) {
                byte[] fileContent = Files.readAllBytes(f.toPath());
                deserialized = deserialize(fileContent);
            } else {
                System.out.println("Le fichier n'existe pas!");
            }
             p = (Personne1) deserialized;
            p.print();*/
            return p;
        
    }
	

	public Personne1 update(Personne1 obj) throws IOException {
		 File f = new File(obj.getNom());
		    if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f);
		        ObjectOutputStream oos = new ObjectOutputStream(fos);
		        oos.writeObject(obj);
		        oos.close();
		        System.out.println("Mise a jour effectuée");
		     
		    } else {
		      System.out.println("fichier n'existe pas");
		    }

	        return obj;
	}

	public void delete(Personne1 obj) {
		 File f = new File(obj.getNom());
		    if (f.exists() && f.delete()) {
		      System.out.println("fichier supprimé");
		    } else {
		      System.out.println("fichier n'existe pas");
		    }
	}

}
