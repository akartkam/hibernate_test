package hello;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * An immutable class representing one bid.
 * <p>
 * If the "successful" property is used in a legacy situation
 * (see book chapter 8), it is no longer an immutable class with
 * consequences for second-level caching.
 *
 * @see Item
 * @see User
 * @author Christian Bauer
 */
@Entity
@Table(name = "BID", schema = "MKYONGDB")
public class Bid implements Serializable, Comparable {
	@Id
	@GeneratedValue
	@Column(name = "BID_ID")
    private Long id = null;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	@Column(name = "CREATE_DATE")
	private Date created = new Date();


    /**
	 * No-arg constructor for JavaBean tools
	 */
	public Bid() {}

	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Full constructor
	 *
	 * @param amount
	 * @param item
	 * @param bidder
	 

	public Bid(MonetaryAmount amount, Item item, User bidder) {
		this.amount = amount;
		this.item = item;
		this.bidder = bidder;
	}
	*/
	
	public Bid(Item item) {
		this.item = item;
	}
	// ********************** Accessor Methods ********************** //

	public Long getId() { return id; }



	public Item getItem() { return item; }



	public Date getCreated() { return created; }



    // ********************** Common Methods ********************** //

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Bid)) return false;

		final Bid bid = (Bid) o;

        if (! getItem().getId().equals(bid.getItem().getId())) return false;
        if (! (created.getTime() == bid.created.getTime()) ) return false;


		return true;
	}

	public int hashCode() {
		int result;
		result = 29  + created.hashCode();
		return result;
	}

	public String toString() {
		return  "Bid ('" + getId() + "'), " +
				"Created: '" + getCreated() + "' " ;
	}

	public int compareTo(Object o) {
		if (o instanceof Bid) {
            // Don't compare Date objects! Use the time in milliseconds!
            return Long.valueOf(this.getCreated().getTime()).compareTo(
                    Long.valueOf( ((Bid)o).getCreated().getTime())
                   );
		}
		return 0;
	}

	// ********************** Business Methods ********************** //

}