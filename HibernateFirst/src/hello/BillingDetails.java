package hello;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorValue("GENERIC")
//@Table(name="BILLING_DETAILS")
public abstract class BillingDetails {
	@Column(name = "OWNER", nullable = false)
	private String owner;
	
	@GenericGenerator(name = "generator", strategy = "foreign", 
	parameters = @Parameter(name = "property", value = "users"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "BD_ID", unique = true, nullable = false)
	private Long id;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	//@JoinColumn(name="USERS_ID")
	private User users;

	/*public Long getId() {
		return id;
	}*/

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public void setId(Long id) {
//		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
