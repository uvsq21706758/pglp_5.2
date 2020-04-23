package pglp_5.JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;

public class GroupeDAOJDBC extends DAO<Groupe>{

	public GroupeDAOJDBC() throws SQLException {
		super();
	}

	@Override
	public Groupe create(Groupe obj) throws IOException {
		 FileOutputStream fos = new FileOutputStream(obj.getNom());
	      ObjectOutputStream oos = new ObjectOutputStream(fos);
	      oos.writeObject(obj);
	      oos.close();
	        System.out.println("Le fichier est créé!");
	        return obj;
	}

	@Override
	public Groupe find(int id) throws IOException, ClassNotFoundException {
		//File f = new File(id);
		Groupe grp = null;
	   /* Object deserialized = null;
            if (f.exists()) {
                byte[] fileContent = Files.readAllBytes(f.toPath());
                deserialized = deserialize(fileContent);
            } else {
                System.out.println("Le fichier n'existe pas!");
            }
            grp = (Groupe) deserialized;
            grp.print();*/
            return grp;
	}

	@Override
	public Groupe update(Groupe obj) throws IOException {
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

	@Override
	public void delete(Groupe obj) {
		 File f = new File(obj.getNom());
		    if (f.exists() && f.delete()) {
		      System.out.println("fichier supprimé");
		    } else {
		      System.out.println("fichier n'existe pas");
		    }
	}

}
