package org.zmsoft.jfp.framework.mybatis.plugin.dialect;

public class DerbyDialect extends DefaultDialect{

	public boolean supportsLimit() {
		return false;
	}

	public boolean supportsLimitOffset() {
		return false;
	}

	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		throw new UnsupportedOperationException( "paged queries not supported" );
	}

}
