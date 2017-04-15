# tp2_sir
A LA DECOUVERTE DE JPA

Ce TP a été réalisé par le binôme SYLLA MOHAMED LAMINE et LE QUANG dans le cadre des TP du cours de SIR. 

Les objectifs de ce TP sont les suivants :
-Comprendre les mécanismes de JPA
-Réaliser une application en utilisant JPA en se plaçant dans un cadre classique de développement sans serveur d’application au départ.

Sujet

L’objectif de ce projet est de construire une application type réseau social permettant de comparer sa consommation électrique avec ses amis, ses voisins, … dans la lignée de opower. 
OPower est une société américaine qui est fondée sur un principe de base déjà porteur : grâce à son logiciel, il permet aux consommateurs de maîtriser leur consommation d’énergie. En effet, il travaille conjointement avec des fournisseurs de services publics (électricité, gaz, téléphone, etc.) pour promouvoir l'efficacité énergétique. Mais lorsqu’il se met à surfer sur la vague Facebook, Opower fait de l’économie d’énergie un jeu… qui pourrait séduire ses clients !
Opower a créé une application (liée à Facebook) qui permet de suivre sa consommation électrique dans le cadre d’un réseau social. Ainsi les consommateurs peuvent comparer leur consommation d’électricité avec celle de ses voisins sur le réseau social… De l’économie d’énergie ludique ! 

Pour ce faire, le modèle métier est assez simple, il utilise le concept de Personne ayant un nom un prénom, un mail, une ou plusieurs résidence. Chaque résidence a une taille, un nombre de pièce, des chauffages, des équipements électroniques. Ses équipements ont une consommation moyenne en Watt/h. 
Prenez la liberté de compléter ce modèle métier au maximum

Nous avons utilisé un template de projet pour la construction d’application autonome utilisant JPA, hibernate et hsqldb, apartir du lien suivant : https://goo.gl/ofZbOu :. 
On a Décompressez ce projet sur notre compte et nous l'avons importez dans eclipse

Pour eclipse 4.X

Depuis eclipse 4.X, le support de maven s’est amélioré. Pour importer votre projet. File -> import -> maven -> existing maven project.
Votre projet est configuré.


Démarrage de la base de données: 
Puis dans la version copiée sur notre compte, nous allons dans le répertoire du projet.Nous avons là le script de démarrage de la base de données (run-hsqldb-server.sh) et le script du démarrage du Manager (show-hsqldb.sh). On lance le système de base de données, puis le Manager. on peut se connecter à la base de données (login : sa – et pas de mot de passe : -- URL de connexion : jdbc:hsqldb:hsql://localhost/ ). Le fichier de données se trouve dans le répertoire Data. On peut supprimer l'ensemble des fichiers de ce répertoire si on souhaite réinitialisez complètement notre système de base de données.

Question 0.
Lorsqu'on regarde rapidement le pom.xml, on constate que c’est un projet simple avec deux dépendances (hibernate et hsqldb (driver jdbc pour hsqldb)).

Question 1.

On transforme  la "classe Person" en "entité person". 


Nous travaillons uniquement sur le champs id et les attributs de la classe. On Crée plusieurs instances de cette classe. Et on rend ces instances persistantes.On peut voir dans le manager de la base de données le résultat.

Vous devez obtenir ce genre de code mais sur vos classes métier

@Entity
@Table(name = "PERSONNE")
public class Person {

	private long id;
	@Column(name = "Nom")
	private String name;
	@Column(name = "Prénom")
	private String firstName;
	@Column(name = "E-mail")
	private String mail;

	private List<Person> friends;
	private List<Home> residences;

	public Person(String nam, String fn, String mail) {
		this.name = nam;
		this.firstName = fn;
		this.mail = mail;
		this.setFriends(new ArrayList<Person>());
		this.setResidences(new ArrayList<Home>());
	}

	public Person() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Person [nom=" + name + ", prenom=" + firstName + ", email=" + mail + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Transient
	public List<Home> getResidences() {
		return residences;
	}

	public void setResidences(List<Home> residences) {
		this.residences = residences;
	}

	@Transient
	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
}

Question 2.

Même travail avec une première association entre deux entités.

On obtient un code suivant.On remplace les attributs mis à Transient sur nos classes précédentes.

@Entity
@Table(name = "PERSONNE")
public class Person {

	private long id;
	@Column(name = "Nom")
	private String name;
	@Column(name = "Prénom")
	private String firstName;
	@Column(name = "E-mail")
	private String mail;

	private List<Person> friends;
	private List<Home> residences;

	public Person(String nam, String fn, String mail) {
		this.name = nam;
		this.firstName = fn;
		this.mail = mail;
		this.setFriends(new ArrayList<Person>());
		this.setResidences(new ArrayList<Home>());
	}

	public Person() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Person [nom=" + name + ", prenom=" + firstName + ", email=" + mail + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
	public List<Home> getResidences() {
		return residences;
	}

	public void setResidences(List<Home> residences) {
		this.residences = residences;
	}

	@ManyToMany
	@JoinTable(name = "amis")
	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
}

Question 3.

On intégre une classe de service permettant de peupler la base mais aussi de faire des requêtes sur la base de données.
Le code pour créer les entités est le suivant :

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
		test.listHome();
		manager.close();
		factory.close();
		System.out.println(".. done");
	}

	private void createHome() {
		int numOfHome = manager.createQuery("SELECT a FROM Home a", Home.class).getResultList().size();
		if (numOfHome == 0) {
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

		}
	}

	private void listHome() {
		List<Home> resultList = manager.createQuery("SELECT a FROM Home a", Home.class).getResultList();
		System.out.println("num of homes : " + resultList.size());
		for (Home next : resultList) {
			System.out.println("maison de : " + next.getPerson().getFirstName());
		}
	}
}

Question 4. Connexion à une base mysql

On a Modifié le fichier persistence.xml afin de se connecter sur une base de données MySQL de l'istic.
Pour ce faire on connecter vous sur http://anteros.istic.univ-rennes1.fr pour vous créer notre base de données. Puis on modifie notre fichier persistence.xml pour nous connecter à notre base de données.
Il est aussi nécessaire d’ajoutez le driver jdbc vers mysql. Pour ce faire on ajoute la dépendance dans le pom.xml

<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.21</version>
		</dependency>
    
    Question 5. 
    Gérons au minimum une relation d’héritage, les requêtes, une requête nommée.
    
    la classe mère :
    
    package domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Appareil")
@Table(name = "Appareil")
public class Device {

	private long id;
	private String name;
	private Home home;

	public Device() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "home_device")
	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
    
    
    la calsse fille :
    package domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Equipement_Electronique")
@Table(name = "Equipement_Electronique")
public class ElectronicDevice extends Device {

	@Column(name = "ConsommationMoyenne")
	private double consumption;

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
}

