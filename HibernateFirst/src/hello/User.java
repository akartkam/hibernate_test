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

@Entity
@Table(name = "USER")
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
    
    //cascade = CascadeType.ALL,
    @ManyToMany
    @JoinTable(
        name="USER_BID",
        joinColumns={@JoinColumn(name="USER_ID")},
        inverseJoinColumns={@JoinColumn(name="BID_ID")})
    private List<Bid> bids = new ArrayList<Bid>();

    @OneToOne
    @PrimaryKeyJoinColumn
    private AddressEntity shippingAddress;

    public AddressEntity getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressEntity shippingAddress) {
		this.shippingAddress = shippingAddress;
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
    
    public void addBid(Bid bid) {
        if (bid == null)
            throw new IllegalArgumentException("Can't add a null Bid.");
        
        bid.setUser(this);
        this.getBids().add(bid);
       
        // Don't have to set the "other" side, a Bid can only be instantiated with a given item
    }
	

}
