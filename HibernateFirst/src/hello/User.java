package hello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER", schema = "MKYONGDB")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id = null;
    private int version = 1;

    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "PSWD")
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Bid> bids = new ArrayList<Bid>();



    /**
     * No-arg constructor for JavaBean tools.
     */
    public User() {}
    
    public User(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
    
    public List<Bid> getBids() { return bids; }

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
