package pglp_5.JDBC;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pglp_5.JDBC.Personne1.Builder;
/**
 * Unit test for simple App.
 */
public class AppTest {
    Personne1 employee;
    DAO<Personne1> personne;
    
    @Before
    public void setUp() throws IOException {
        Numero_telephone portable =new Numero_telephone(1,"portable", "06123456775");
        Builder b = new Builder(1,"elgaamouss", "manale", "employee",LocalDate.of(1996, 8, 27));
        b.Num_telephone(portable);
        Personne1 p = b.build();
        employee = p;
        personne = DAOFactory.getPersonne1DAO();
    }
  
    private Object deserialize(final byte[] bytes) throws ClassNotFoundException, IOException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
   
    private byte[] serialize(final Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }
   
    @Test
    public void ConsistencyTest() throws IOException, ClassNotFoundException {
        Personne1 perso = employee;
        byte[] serialized1 = serialize(perso);
        byte[] serialized2 = serialize(perso);

        Object deserialized1 = deserialize(serialized1);
        Object deserialized2 = deserialize(serialized2);
        Assert.assertEquals(deserialized1, deserialized2);
        Assert.assertEquals(perso, deserialized1);
        Assert.assertEquals(perso, deserialized2);
    }
    
    @Test
    public void createTest() throws IOException, ClassNotFoundException {   
    	
        personne.create(employee);
        
        File search = new File(employee.getNom()+".txt");
        Object deserialized = null;
        
        byte[] fileContent = Files.readAllBytes(search.toPath());
       
        deserialized = deserialize(fileContent);
        Personne1 expected = (Personne1) deserialized;
        assertTrue(search.exists());
        assertEquals(expected, employee);
       
    }
   
    @Test
    public void deleteTest() throws IOException, ClassNotFoundException {      
        Numero_telephone portable =new Numero_telephone(1,"portable", "0634256788");
        Builder b = new Builder(1,"hadir", "julia", "chef de projet",LocalDate.of(1992,3,2));
        b.Num_telephone(portable);
        Personne1 chef = b.build();
       File search = new File(chef.getNom()+".txt");
        personne.create(chef);
        assertTrue(search.exists());
        personne.delete(chef);
        assertTrue(!search.exists());
    }
    
    @Test
    public void updateTest() throws IOException, ClassNotFoundException {  
    	personne= DAOFactory.getPersonne1DAO();
        File search = new File(employee.getNom()+".txt");

        personne.create(employee);
        personne.update(employee);
        Object deserialized = null;
        
        byte[] fileContent = Files.readAllBytes(search.toPath());
       
        deserialized = deserialize(fileContent);
        Personne1 expected = (Personne1) deserialized;
        
        assertTrue(search.exists());
        assertEquals(expected, employee);
    }  
    
  @Test
    public void findTest() throws IOException, ClassNotFoundException {  
        File search = new File(employee.getNom()+".txt");
        Personne1 expected;
        personne.create(employee);
        expected = personne.find("elgaamouss");
        assertTrue(search.exists());
        assertEquals(expected, employee);
    }

}