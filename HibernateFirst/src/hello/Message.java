package hello;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import persistence.MonetaryAmount;


@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "MESSAGES",schema = "MKYONGDB")
public class Message {
	@Id
	@GeneratedValue
	@Column(name = "MESSAGE_ID")
	private Long id;


	@Transient
	private String text;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NEXT_MESSAGE_ID")
	private Message nextMessage;
	
	@Embedded //For value type object
	private Address home_Address;
	
	@org.hibernate.annotations.Type(type = "persistence.MonetaryAmountSimpleUserType")
	@org.hibernate.annotations.Columns(columns = {
			@Column(name = "INITIAL_PRICE"),
			@Column(name = "INITIAL_PRICE_CURRENCY", length = 3) })
	private MonetaryAmount initialPrice;
	
	
	@ElementCollection
    @CollectionTable(	name = "ITEM_IMAGE", 	joinColumns = @JoinColumn(name = "MESSAGE_ID"))
	@Column(name = "FILENAME", nullable = false)
	private Set<String> images = new HashSet<String>();

	private Message() {
	}

	public Message(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}
	
	
	
	public MonetaryAmount getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(MonetaryAmount initialPrice) {
		this.initialPrice = initialPrice;
	}

	//@Basic(optional = false)
	@Access(AccessType.PROPERTY)
	@Column(name = "MESSAGE_TEXT")
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public Message getNextMessage() {
		return nextMessage;
	}

	public void setNextMessage(Message nextMessage) {
		this.nextMessage = nextMessage;
	}
	
	public Address getAddress() {
	        return home_Address;
	}
	public void setAddress(Address address) {
	        this.home_Address = address;
	}



	public Set<String> getImages() {
		return images;
	}

	public void addImage(String image) {
		if (image == null)
            throw new IllegalArgumentException("Can't add a null image.");
		getImages().add(image);
      
	}	
	
	
}