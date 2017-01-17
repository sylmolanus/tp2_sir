package domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Maison")
public class Home {

	private long id;
	@Column(name = "Taille")
	private double size;
	@Column(name = "NombreDePieces")
	private int nbRooms;
	@Column(name = "Propriétaire")
	private Person person;

	private List<Device> devices;

	public Home(double length, int nbRooms, Person pers) {
		this.size = length;
		this.nbRooms = nbRooms;
		this.person = pers;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getNbRooms() {
		return nbRooms;
	}

	public void setNbRooms(int nbRooms) {
		this.nbRooms = nbRooms;
	}

	@OneToMany(mappedBy = "home", cascade = CascadeType.PERSIST)
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@ManyToOne
	@JoinColumn(name = "resident")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
