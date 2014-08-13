package hello;

import javax.persistence.*;

@Entity
//@AttributeOverride(name = "owner", column = @Column(name = "CC_OWNER", nullable = false))
@Table(name = "CREDITCARD")
public class CreditCard extends BillingDetails {
/*	@Id
	@GeneratedValue
	@Column(name = "CREDIT_CARD_ID")
	private Long id = null;*/
	@Column(name = "NUMBER", nullable = false)
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
