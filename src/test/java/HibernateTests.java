import com.excercise.entities.Boardgame;
import com.excercise.entities.Player;
import com.excercise.services.PlayerBoardgamePlays;
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
    public void testThatBoardgameIsCreatedAndRetrieved() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(new Boardgame("TEST ENTRY"));
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        List<Boardgame> result = session.createQuery( "select b from Boardgame b" , Boardgame.class).list();
        for ( Boardgame boardgame : result) {
            System.out.println( "Boardgame : " + boardgame.getName() + boardgame.getId() );
        }
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testThatPlayerIsCreatedAndRetrieved(){
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.persist(new Player("TEST USER "));
        session.persist(new Player("TEST USER THE SEQUEL, the ID should show 2:  "));


        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        List<Player> result = session.createQuery( "select p from Player p" , Player.class).list();
        for ( Player p : result) {
            System.out.println( "Player : " + p.getName() + p.getId() );
        }
        session.getTransaction().commit();
        session.close();

    }
    @Test
    public void testThatAddAPlayAddsAPlay(){ //TODO: remove unclosed connection
        //creating a player
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Player player= new Player("TEST USER");
        session.persist(player);


        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();


        // add a play
        session.beginTransaction();

        Boardgame bg=new Boardgame("TEST");
        PlayerBoardgamePlays plays= new PlayerBoardgamePlays(player,bg);


        session.persist(plays);


        session.getTransaction().commit();
        session.close();


        //retrieve played  games
        session = sessionFactory.openSession();
        session.beginTransaction();

        plays.toString();

        session.getTransaction().commit();
        session.close();
    }







}
