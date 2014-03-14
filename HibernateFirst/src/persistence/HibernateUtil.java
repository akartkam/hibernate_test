package persistence;

import java.util.List;

import hello.CENamingStrategy;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;

/**
 * Startup Hibernate and provide access to the singleton SessionFactory
 */
public class HibernateUtil {

  private static SessionFactory sessionFactory;

  static {
    try {
    	//sessionFactory = new Configuration().configure().buildSessionFactory();  
    	Configuration configuration = new Configuration();
    	configuration.setNamingStrategy( new CENamingStrategy() );
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
        		  applySettings(configuration.getProperties()).buildServiceRegistry();        
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);    	
    } catch (Throwable ex) {
       throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
      // Alternatively, we could look up in JNDI here
      return sessionFactory;
  }

  public static void shutdown() {
      // Close caches and connection pools
      getSessionFactory().close();
  }
  
  @SuppressWarnings("unchecked")
	public static List<Object> getRecordsOfType(final Class classObject)

	{

		final Session session = getSessionFactory().openSession();

		try {

			return session.createCriteria(classObject).list();

		}

		finally {

			//session.close();

		}

	}

}