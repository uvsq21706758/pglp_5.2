package pglp_5.JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Numero_telephoneDAO extends DAO<Numero_telephone>{

    public Numero_telephoneDAO() throws SQLException {
		super();
	}

	@Override
	public Numero_telephone create(Numero_telephone obj) throws IOException, SQLException {
        this.getConnect();
        String telephoneTABLE = "CREATE TABLE Telephones("
                + "num_id int,"
                + "type varchar(30),"
                + "numero varchar(30),"
                + "PRIMARY KEY (num_id)"
                + ")";
        Statement stmt = getConnect().createStatement();
        stmt.execute(telephoneTABLE);
        if(con != null) {
            
                con.close();}
        return obj;
	}

	@Override
	public Numero_telephone find(int id) throws IOException, ClassNotFoundException, SQLException {
		Numero_telephone num =null;      
	      
		   
		      ResultSet result = this.con.createStatement(
		        ResultSet.TYPE_SCROLL_INSENSITIVE,
		        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Telephones WHERE num_id = " + id);
		      if(result.first())
		        num = new Numero_telephone(
		          id,
		          result.getString("type"),
		          result.getString("numero"
		        ));         
		      if(con != null) {
		    	  con.close();
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
		 Statement stmt = con.createStatement();
         stmt.execute("DROP TABLE Telephones");
         if(con != null) {
             
                 System.out.println("close");
                 con.close();}
	}

}
