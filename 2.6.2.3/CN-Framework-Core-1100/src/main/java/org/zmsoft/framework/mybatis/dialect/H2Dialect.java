package org.zmsoft.framework.mybatis.dialect;


/**
 * Dialect for H2
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class H2Dialect extends DefaultDialect {

    public boolean supportsLimit() {
        return true;
    }

	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		return new StringBuffer(sql.length() + 40).
			append(sql).
			append((offset > 0) ? " limit "+limitPlaceholder+" offset "+offsetPlaceholder : " limit "+limitPlaceholder).
			toString();
	}

	@Override
	public boolean supportsLimitOffset() {
		return true;
	}
    
//    public boolean bindLimitParametersInReverseOrder() {
//        return true;
//    }    
//
//    public boolean bindLimitParametersFirst() {
//        return false;
//    }

    

}