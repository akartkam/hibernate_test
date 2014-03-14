package hello;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetails {
	@Column(name = "OWNER", nullable = false)
	private String owner;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "BILLING_DETAILS_ID")
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
