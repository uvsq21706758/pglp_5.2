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


public class Numero_telephoneDAO extends DAO<Numero_telephone>{

    public Numero_telephoneDAO() throws SQLException {
		super();
	}

	@Override
	public Numero_telephone create(Numero_telephone obj) throws IOException, SQLException {
		  DatabaseMetaData dbmd = getConnect().getMetaData();
	        ResultSet rs = dbmd.getTables(null, null,"Telephones".toUpperCase(), null);
	        String createtel="CREATE TABLE Telephones("
                    + "id_num int,"
                    + "numero varchar(30),"
                    + "type varchar(30),"
                    + "PRIMARY KEY (id_num)"
                    + ")";
	         Statement stmt = getConnect().createStatement();
	            if (!rs.next()) {
	            	stmt.execute(createtel);
	            }
	            
	                String insertnum= ("insert into Telephones"
	                        + " values (?, ?, ?)");
	                PreparedStatement prpstmt = getConnect().prepareStatement(insertnum);

	                prpstmt.setInt(1, obj.getId());
	                prpstmt.setString(2, obj.getType());
	                prpstmt.setString(3, obj.getNumero());

	                prpstmt.executeUpdate();
	                prpstmt.close();

	                rs = stmt.executeQuery("SELECT * FROM Telephones");

	                System.out.println("Table Telephones: \n");
	                System.out.println("id_num\t type\t numero");
	                while (rs.next()) {
	                    System.out.printf("%d\t%s\t%s%n", rs.getInt("id_num"),
	                            rs.getString("type"), rs.getString("numero"));
	                }
	                
	                System.out.println("\n Table enregistée ");
	                rs.close();
	               stmt.close();
	            
	            return obj;
	        
	}

	@Override
	public Numero_telephone find(int id) throws IOException, ClassNotFoundException, SQLException {
		Numero_telephone num = null;
        Statement stmt = getConnect().createStatement();
        ResultSet rs = stmt.executeQuery("select * from Telephones"
                            + " where id_num=" + id);
                    System.out.println("La ligne rechercher dans la table Telephones: \n");
	                System.out.println("id_num\t type\t numero");
                        if (rs.next()) {
                        	 System.out.printf("%d\t%s\t%s%n", rs.getInt("id_num"),
     	                            rs.getString("type"), rs.getString("numero"));
                        rs.close();
                        stmt.close();
                    }
       return num;
		  
	}

	@Override
	public Numero_telephone update(Numero_telephone obj) throws IOException {
		File f = new File(obj.getNumero());
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

	@Override
	public void delete(Numero_telephone obj) throws SQLException {
		 Statement stmt = getConnect().createStatement();
         ResultSet rs = stmt.executeQuery("select * from Telephones where id_num=" + obj.getId());
             if (rs.next()) {
            	  stmt.executeUpdate("delete from Telephones where id_num="+obj.getId());
             	 System.out.printf("Ligne supprimée");
             rs.close();
             stmt.close();
	}
	}

}
