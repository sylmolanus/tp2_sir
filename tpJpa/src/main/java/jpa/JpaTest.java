package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.Person;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		try {
			test.createHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		//test.listHome();
		manager.close();
		factory.close();
		System.out.println(".. done");
	}

	private void createHome() {
		//int numOfHome = manager.createQuery("SELECT a FROM Home a", Home.class).getResultList().size();
		//if (numOfHome == 0) {
			for (int i = 0; i < 10; i++) {
				ElectronicDevice elec = new ElectronicDevice();
				elec.setName("electro" + i);
				elec.setConsumption(10 + i);
				manager.persist(elec);
			}

			for (int i = 0; i < 10; i++) {
				Heater heat = new Heater();
				heat.setName("heater" + i);
				manager.persist(heat);
			}

			for (int i = 0; i < 10; i++) {
				Person person = new Person("toto" + i, "lolo" + i, "heheh@coco.fr" + i);
				manager.persist(person);
				Home home = new Home(25.5, 5, person);
				manager.persist(home);
			}

		//}
	}

	private void listHome() {
		List<Home> resultList = manager.createQuery("SELECT a FROM Home a", Home.class).getResultList();
		System.out.println("num of homes : " + resultList.size());
		for (Home next : resultList) {
			System.out.println("maison de : " + next.getPerson().getFirstName());
		}
	}
}
