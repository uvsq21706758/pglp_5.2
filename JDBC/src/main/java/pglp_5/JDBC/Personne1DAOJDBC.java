package pglp_5.JDBC;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Personne1DAOJDBC extends DAO<Personne1>{
	
	private DAO<Numero_telephone> numJDBC;
	
	public Personne1DAOJDBC() throws SQLException, IOException {
		super();
		numJDBC = new DAOFactoryJDBC().getNumero_telephoneDAO();
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
	 
	 public void createassoc(int obj,int objt) throws SQLException {
	   	 DatabaseMetaData dbmd = getConnect().getMetaData();
	        ResultSet rs = dbmd.getTables(null, null,"Association".toUpperCase(), null);
		 String assoc="Create Table Association"
                  + " (id_perso int,"
                  + " id_num int, "
                  + "primary key (id_perso, id_num),"
                  + "foreign key (id_perso) references"
                  + " Personnes(id_perso),"
                  + "foreign key (id_num) references"
                  + " Telephones(id_num))";
		  Statement stmt = getConnect().createStatement();
          if (!rs.next()) {
		  stmt.execute(assoc);
          }
          stmt.executeUpdate("insert into Association values ("
                  + obj + "," + objt + ")");
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
         for (Numero_telephone num : obj.getNumero_telephone()) {
        	 ((Numero_telephoneDAOJDBC) numJDBC).createtable();
             numJDBC.create(num);
             this.createassoc(obj.getId(), num.getId());
         }
          return obj;
	}

	public Personne1 find(int id) throws IOException, ClassNotFoundException, SQLException{
		Personne1 perso = null;
        Statement stmt = getConnect().createStatement();
        ResultSet rs = stmt.executeQuery("select * from Personnes"
                            + " where id_perso=" + id);
                    System.out.println("La ligne rechercher dans la table Personnes: \n");
	                System.out.println("id_perso\t nom\t prenom\t fonction\t date naissance");
                        if (rs.next()) {
                        	 System.out.printf("%d\t%s \t%s\t %s\t %s%n", rs.getInt("id_perso"),
            	                     rs.getString("nom"), rs.getString("prenom"),
            	                     rs.getString("fonction"),rs.getString("date_naissance"));
            	         
                        rs.close();
                        stmt.close();
                    }else {
                    	System.out.println("identifiant introuvable!");
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
	                    result.close();
	                    stmt.close();
	                }else {
                    	System.out.println("Pas de mise à jour,l'identifiant inexistant!");
                    }
	                
	                return obj;
	}

	public void delete(Personne1 obj) throws SQLException {
		Statement stmt = getConnect().createStatement();
		int id_num;
        ResultSet rs = stmt.executeQuery("select * from Association where id_perso=" + obj.getId());
            if(rs.next()) {
            	id_num = rs.getInt("id_num");
              stmt.executeUpdate("delete from Association where id_perso="+obj.getId()+"and id_num="+id_num);
              stmt.executeUpdate("delete from persoingroupe where id_perso="+obj.getId());
              stmt.executeUpdate("delete from Telephones where id_num="+id_num);
           	  stmt.executeUpdate("delete from Personnes where id_perso="+obj.getId());
            	rs.close();
            stmt.close();
           	  System.out.printf("Ligne supprimée \n");
           
            }else {
            	System.out.println("suppression impossible,identifiant introuvable!");
            }
            
	}
	 public void droptable() throws SQLException {
	    	Statement stmt = getConnect().createStatement();
	    	stmt.execute("DROP TABLE Association");
	    	stmt.execute("DROP TABLE persoingroupe");
	    	stmt.execute("DROP TABLE Personnes");
	    	stmt.close();
	    	System.out.println("Tables supprimées \n");
	    	
	    }
	 public void affichetable() throws SQLException {
	    	DatabaseMetaData dbmd = getConnect().getMetaData();
	        ResultSet rst = dbmd.getTables(null, null,"Personnes".toUpperCase(), null);
	        Statement stmt = getConnect().createStatement();
	        if(rst.next()){
	    	ResultSet rs = stmt.executeQuery("SELECT * FROM Personnes");

	         System.out.println("Table Personnes: \n");
	         System.out.println("id_perso\t nom\t prenom\t fonction\t date naissance");
	         while (rs.next()) {
	             System.out.printf("%d\t%s \t%s\t %s\t %s%n", rs.getInt("id_perso"),
	                     rs.getString("nom"), rs.getString("prenom"),
	                     rs.getString("fonction"),rs.getString("date_naissance"));
	         }
	         rs.close();
	         stmt.close();
	 }else {
             	System.out.println("Table vide!");
             }
	      
	    }
	 public void afficheassoc() throws SQLException {
		 DatabaseMetaData dbmd = getConnect().getMetaData();
	            ResultSet rst = dbmd.getTables(null, null,"Association".toUpperCase(),
	                    null);
	            if (rst.next()) {
	                Statement stmt = getConnect().createStatement();
	                    ResultSet rs = stmt.executeQuery("SELECT *"
	                            + " FROM Association");
	                        System.out.println("Table Association:\n");
	                        System.out.println("id_perso\t id_num\t");
	                        while (rs.next()) {
	                            System.out.printf("%d\t\t%d\t%n",
	                                    rs.getInt("id_perso"),
	                                    rs.getInt("id_num"));
	                        }
	                        
	                        rs.close();
	                    }
	         else {
                	System.out.println("Table vide!");
                }
	                
	          
	 }
}
