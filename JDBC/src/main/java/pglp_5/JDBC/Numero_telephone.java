package pglp_5.JDBC;


import java.io.Serializable;

public class Numero_telephone implements Serializable{
  
	private static final long serialVersionUID = 1L;
	private Type_tel type;
	private String numero;
	
	public Numero_telephone(Type_tel type,String numero) {
		this.type=type;
		this.numero=numero;
	}
	
	public Type_tel getType() {
		return type;
	}
	
	public String getNumero() {
		return numero;
	}
	
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Numero_telephone other = (Numero_telephone) obj;
	        if (type == null) {
	            if (other.type != null)
	                return false;
	        } else if (!type.equals(other.type))
	            return false;
	        if (numero == null) {
	            if (other.numero != null)
	                return false;
	        } else if (!numero.equals(other.numero))
	            return false;
	        return true;
	    }

	public void affiche() {
		System.out.println( ": \nType: "
                + this.type + "\nNumerp: "
                + this.numero + "\n");
	}
	
}
