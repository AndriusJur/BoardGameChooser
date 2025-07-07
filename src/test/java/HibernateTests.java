import com.excercise.entities.Boardgame;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HibernateTests {
//tests adapted from  https://github.com/marcobehlerjetbrains/hibernate-tutorial/blob/main/src/test/java/org/example/HibernateFullTest.java

    private SessionFactory sessionFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @AfterEach
    protected void tearDown() throws Exception {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @Test
    public void testBasicUsage() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory was not initialized. Check your hibernate.cfg.xml and entity mappings.");
        }

        // Save boardgame
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Boardgame boardgame = new Boardgame("TEST");
        session.persist(boardgame);

        session.getTransaction().commit();
        session.close();

        List<Boardgame> result = session.createQuery("SELECT b FROM Boardgame b", Boardgame.class).getResultList();//entity's name, not columns

        for (Boardgame bg : result) {
            System.out.println("Title (" + bg.name + ") : " + bg.getTimesPlayed());
        }

        session.getTransaction().commit();
        session.close();
        assertThat(result).isNotEmpty();
    }


    @Test
    public void testToTestTesting() {//remove when unnecessary
        assertThat(1).isGreaterThanOrEqualTo(0);
    }

}
