package pglp_5.JDBC;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class Groupe implements Composite, Serializable{
	
	private static final long serialVersionUID = 1L;

	private ArrayList<Composite> personnes = new ArrayList<Composite>();
	
	private String nom;
    
    public Groupe(final String nom) {
        this.nom = nom;
    }
   
    
	public void print() {
	
		for(Composite composant: personnes) {
			composant.print();
		}
	}
	
	public void add(Composite composant) {
		personnes.add(composant);
	}
	
	public void remove(Composite composant) {
		personnes.remove(composant);
	}
	 public String getNom() {
	        return nom;
	    }
	 public void hierarchique() {
	        Iterator<Composite> iterator = personnes.iterator();
	        System.out.println(this.getNom() + ":    ");
	        while (iterator.hasNext()) {
	            Composite comp = iterator.next();
	            comp.print();
	        }
	 }
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Groupe other = (Groupe) obj;
	        if (personnes == null) {
	            if (other.personnes != null)
	                return false;
	        } else if (!personnes.equals(other.personnes))
	            return false;
	        if (nom == null) {
	            if (other.nom != null)
	                return false;
	        } else if (!nom.equals(other.nom))
	            return false;
	        return true;
	    }
	 
}