package hello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id = null;
	@Version
	@Column(name = "VERS")
    private int version;

    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "PSWD")
    private String password;
    
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
	private BillingDetails defaultBillingDetails;
	
    public BillingDetails getDefaultBillingDetails() {
		return defaultBillingDetails;
	}

	public void setDefaultBillingDetails(BillingDetails defaultBillingDetails) {
		this.defaultBillingDetails = defaultBillingDetails;
	}

   
	public int getVersion() {
		return version;
	}

	/**
     * No-arg constructor for JavaBean tools.
     */
    public User() {}
    
    public User(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
