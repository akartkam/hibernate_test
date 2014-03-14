package persistence;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Currency;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.type.Type;


/**
 * This is a simple Hibernate custom mapping type for MonetaryAmount value types.
 * <p>
 * Note that this mapping type is for legacy databases that only have a
 * single numeric column to hold monetary amounts. Every <tt>MonetaryAmount</tt>
 * will be converted to USD (using some conversion magic of the class itself)
 * and saved to the database. Every value read from the database will be
 * converted to the currency prefered by the current user (UserSession thread local).
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public class MonetaryAmountSimpleUserType implements CompositeUserType {

	public Class returnedClass() { return MonetaryAmount.class; }

	public boolean isMutable() { return false; }

	public Object deepCopy(Object value) {
		return value; // MonetaryAmount is immutable
	}
	
	public Serializable disassemble(Object value, SessionImplementor session)
	{ return (Serializable) value; }
	
	public Object assemble(Serializable cached, SessionImplementor session, Object owner)
	{ return cached; }
	
	public Object replace(Object original,
			Object target, SessionImplementor session,
			Object owner)
	{ return original; }
	
	public int hashCode(Object x) {
		return x.hashCode();
		}

	public boolean equals(Object x, Object y) {
		if (x == y) return true;
		if (x == null || y == null) return false;
		return x.equals(y);
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names,
			SessionImplementor session, Object owner) throws SQLException {
		BigDecimal value = resultSet.getBigDecimal(names[0]);
		if (resultSet.wasNull())
			return null;
		Currency currency = Currency.getInstance(resultSet.getString(names[1]));
		return new MonetaryAmount(value, currency);
	}

	public void nullSafeSet(PreparedStatement statement, Object value,
			int index, SessionImplementor session) throws SQLException {
		if (value == null) {
			statement.setNull(index, StandardBasicTypes.BIG_DECIMAL.sqlType());
			statement.setNull(index + 1, StandardBasicTypes.CURRENCY.sqlType());
		} else {
			MonetaryAmount amount = (MonetaryAmount) value;
			String currencyCode = amount.getCurrency().getCurrencyCode();
			statement.setBigDecimal(index, amount.getValue());
			statement.setString(index + 1, currencyCode);
		}
	}	
	
	public String[] getPropertyNames() {
		return new String[] { "amount", "currency" };
	}

	public Type[] getPropertyTypes() {
		return new Type[] { StandardBasicTypes.BIG_DECIMAL, StandardBasicTypes.CURRENCY };
	}

	public Object getPropertyValue(Object component, int property) {
		MonetaryAmount monetaryAmount = (MonetaryAmount) component;
		if (property == 0)
			return monetaryAmount.getValue();
		else
			return monetaryAmount.getCurrency();
	}

	public void setPropertyValue(Object component, int property, Object value) {
		throw new UnsupportedOperationException("Immutable MonetaryAmount!");
	}	
		
}
