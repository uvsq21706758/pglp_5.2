package pglp_5.JDBC;



public abstract class AbstractDAOFactory {
	 public enum Type {
	        JDBC, Serialisation
	    };
	 public static AbstractDAOFactory getFactory(Type type) {

		 AbstractDAOFactory factory = null;

	        switch (type) {
	        case JDBC:
	            factory = new DAOFactoryJDBC();
	            break;
	        case Serialisation:
	            factory = new DAOFactory();
	            break;
	        }

	        return factory;
	    }
}
