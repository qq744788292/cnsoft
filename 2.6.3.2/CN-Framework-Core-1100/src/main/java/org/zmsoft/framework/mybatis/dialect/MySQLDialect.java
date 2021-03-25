package org.zmsoft.framework.mybatis.dialect;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * Mysql分页器
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Service("DefaultDialect")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MySQLDialect extends DefaultDialect {

	public MySQLDialect() {
		dbName = "MySQL";
	}

	public boolean supportsLimitOffset() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {
			return sql + super.getSupportsOrderby() + " limit " + offsetPlaceholder + "," + limitPlaceholder;
		} else {
			return sql + super.getSupportsOrderby() + " limit " + limitPlaceholder;
		}
	}

}
