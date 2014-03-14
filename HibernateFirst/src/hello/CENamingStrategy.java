package hello;

import org.hibernate.annotations.common.util.StringHelper;
import org.hibernate.cfg.ImprovedNamingStrategy;

public class CENamingStrategy extends ImprovedNamingStrategy {
	public String classToTableName(String className) {
		return StringHelper.unqualify(className);
	}

	public String propertyToColumnName(String propertyName) {
		return propertyName;
	}

	@Override
	public String tableName(String tableName) {
		return "CE_" + tableName;
	}

	@Override
	public String columnName(String columnName) {
		return "CE_"+columnName;
	}

    @Override
	public String collectionTableName(String ownerEntity,
            String ownerEntityTable,
            String associatedEntity,
            String associatedEntityTable,
            String propertyName) {
		return "CE_" + classToTableName(ownerEntity) + '_'
				+ propertyToColumnName(propertyName);
	}
}