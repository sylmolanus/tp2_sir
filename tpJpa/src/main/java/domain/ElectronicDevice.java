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
