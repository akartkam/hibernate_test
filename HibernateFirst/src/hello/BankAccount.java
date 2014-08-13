package hello;

import javax.persistence.*;


@Entity
//@AttributeOverride(name = "owner", column = @Column(name = "BA_OWNER", nullable = false))
@Table(name = "BANKACCOUNT")
public class BankAccount extends BillingDetails {
/*	@Id
	@GeneratedValue
	@Column(name = "BANK_ACCOUNT_ID")
	private Long id = null;*/
	@Column(name = "NUMBER", nullable = false)
	private String account;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
