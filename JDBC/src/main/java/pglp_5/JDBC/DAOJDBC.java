package pglp_5.JDBC;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class DAOJDBC <T>{
	String driver ="org.apache.derby.jdbc.EmbeddedDriver";
	String dburl = "jdbc:derby:data;create=true";
	
	Connection con;
	public DAOJDBC() throws SQLException {
	   con=DriverManager.getConnection(dburl);
	}
	
	public abstract T create(T obj) throws IOException, SQLException;
   
	public abstract T find(int id) throws IOException, ClassNotFoundException, SQLException;
    
	public abstract T update(T obj) throws IOException, SQLException;
    
	public abstract void delete(T obj)throws SQLException;
	
	public Object deserialize(final byte[] bytes) throws ClassNotFoundException,
    IOException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
	
	public byte[] serialize(final Object obj) throws IOException {
	        ByteArrayOutputStream b = new ByteArrayOutputStream();
	        ObjectOutputStream o = new ObjectOutputStream(b);
	        o.writeObject(obj);
	        return b.toByteArray();
	    }
	 public Connection getConnect() {
	        return con;
	    }
}
