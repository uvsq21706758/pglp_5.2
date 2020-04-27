package pglp_5.JDBC;

import java.io.*;

public abstract class DAO <T>{
	
	public abstract T create(T obj) throws IOException;
   
	public abstract T find(String id) throws IOException, ClassNotFoundException;
    
	public abstract T update(T obj) throws IOException;
    
	public abstract void delete(T obj);
	
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
}
