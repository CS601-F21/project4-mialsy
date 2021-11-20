package mialsy.project4;

import mialsy.project4.models.Event;
import mialsy.project4.models.Transaction;
import mialsy.project4.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project4Application {
//	private static Session session;

	public static void main(String[] args) {
		SpringApplication.run(Project4Application.class, args);
//
//		// Hibernate
//		// Create config
//		Configuration configuration = new Configuration()
//				.addAnnotatedClass(User.class)
//				.addAnnotatedClass(Event.class)
//				.addAnnotatedClass(Transaction.class)
//				.configure();
//		System.out.println(configuration);
//
//		// Get session
//		SessionFactory sessionFactory = configuration.buildSessionFactory();
//		Session session = sessionFactory.openSession();
//
//		User user = new User();
//		user.setName("test user");
//		user.setTwitterId(12121l);
//
//		Event event = new Event();
//		event.setName("event test");
//		event.setDescription("desc");
//		event.setPrice(30.2);
//
//		Transaction transaction = new Transaction();
//		transaction.setEvent(event);
//		transaction.setUser(user);
//
//		session.save(user);
//		session.save(event);
//		session.save(transaction);
//
//		session.beginTransaction().commit();
//		session.close();
	}

}
