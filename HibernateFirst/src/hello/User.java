package hello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name = "USER", schema = "MKYONGDB")
public class User implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id = null;
    private int version = 1;

    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "PSWD")
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<Bid> bids = new ArrayList<Bid>();



    /**
     * No-arg constructor for JavaBean tools.
     */
    public User() {}
	

}
