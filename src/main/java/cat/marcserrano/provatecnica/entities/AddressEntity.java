 	package cat.marcserrano.provatecnica.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="user_addresses")
public class AddressEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer address_id;
	private String street;
	private String city;
	private String zipcode;
	private String country;
	
	public AddressEntity(Integer address_id, String street, String city, String zipcode, String country) {
		super();
		this.address_id = address_id;
		this.street = street;
		this.city = city;
		this.zipcode = zipcode;
		this.country = country;
	}
	
	public AddressEntity(String street, String city, String zipcode, String country) {
		super();
		this.street = street;
		this.city = city;
		this.zipcode = zipcode;
		this.country = country;
	}
	
	public AddressEntity() {
		super();
	}

	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressEntity other = (AddressEntity) obj;
		return address_id == other.address_id;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", zipcode=" + zipcode + ", country=" + country + "]";
	}
}
