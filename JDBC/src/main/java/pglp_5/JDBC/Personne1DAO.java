package pglp_5.JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class Personne1DAO extends DAO<Personne1>{
	
	public Personne1 create(Personne1 obj) throws IOException{
		 FileOutputStream fos = new FileOutputStream(obj.getNom()+".txt");
	      ObjectOutputStream oos = new ObjectOutputStream(fos);
	      oos.writeObject(obj);
	      oos.close();
	        System.out.println("Le fichier est créé!");
	        return obj;
	}

	public Personne1 find(String id) throws IOException, ClassNotFoundException{
		File f = new File(id);
	    Personne1 p = null;
	    Object deserialized = null;
            if (f.exists()) {
                byte[] fileContent = Files.readAllBytes(f.toPath());
                deserialized = deserialize(fileContent);
            } else {
                System.out.println("Le fichier n'existe pas!");
            }
             p = (Personne1) deserialized;
            p.print();
            return p;
        
    }
	

	public Personne1 update(Personne1 obj) throws IOException {
		 File f = new File(obj.getNom()+".txt");
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
		 File f = new File(obj.getNom()+".txt");
		    if (f.exists() && f.delete()) {
		      System.out.println("fichier supprimé");
		    } else {
		      System.out.println("fichier n'existe pas");
		    }
	}

}
