package pglp_5.JDBC;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Personne1 implements Composite,Serializable{
	 
	private static final long serialVersionUID = 1L;
 	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }      
        if (getClass() != obj.getClass()) {
            return false;
        }
        Personne1 other = (Personne1) obj;
        if (date_naissance == null) {
            if (other.date_naissance != null) {
                return false;
            }        
        } else if (!date_naissance.equals(other.date_naissance)) {       
            return false;
        }
        if (fonction == null) {
            if (other.fonction != null) {
                return false;
            }
        } else if (!fonction.equals(other.fonction)) {
            return false;
        }
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (num_telephone == null) {
            if (other.num_telephone != null) {
                return false;
            }
        } else if (!num_telephone.equals(other.num_telephone))
            return false;
        if (prenom == null) {
            if (other.prenom != null)
                return false;
        } else if (!prenom.equals(other.prenom))
            return false;
        return true;
    }
 	
	private final String nom;
     private final String prenom;
     private final String fonction;
     private final LocalDate date_naissance;
     private final ArrayList<Numero_telephone> num_telephone;
     
     public static class Builder{
    	 private final String nom;
         private final String prenom;
         private String fonction;
         private LocalDate date_naissance;
         private ArrayList<Numero_telephone> num_telephone;
        
         public Builder(final String nom,final String prenom,final String fonction,final LocalDate date_naissance) {
        	 this.nom=nom;
        	 this.prenom=prenom;
        	 this.fonction = fonction;
 			 this.date_naissance= date_naissance;
 			 this.num_telephone= new ArrayList<Numero_telephone>();
         }
         
         public Builder Num_telephone(Numero_telephone num_telephone) {
        	  
              this.num_telephone.add(num_telephone);
        	  return this;
         }
       public Personne1 build() {
    	   return new Personne1(this);
       }
     }
     private Personne1(Builder builder) {
 		nom=builder.nom;
 		prenom=builder.prenom;
 		fonction=builder.fonction;
 		date_naissance = builder.date_naissance;
 		num_telephone=builder.num_telephone;
 	}
     
     public String getNom() {
 		return nom;
 	}

 	
 	public String getPrenom() {
 		return prenom;
 	}


 	public String getFonction() {
 		return fonction;
 	}


 	public LocalDate getDate_naissance() {
 		return date_naissance;
 	}

 	
 	public ArrayList<Numero_telephone> getNumero_telephone() {
 		return num_telephone;
 	}
 	public void print() {
		System.out.println(this.nom + " " + this.prenom + ": \nfonction: " 
		+ this.fonction + "\ndate de naissance: "+this.date_naissance + "\n");	
	}
 	
	
}
