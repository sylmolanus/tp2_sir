package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Heater")
@Table(name = "Chauffage")
public class Heater extends Device {

}
