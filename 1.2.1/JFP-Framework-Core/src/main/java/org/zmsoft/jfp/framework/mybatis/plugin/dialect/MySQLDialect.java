package org.zmsoft.jfp.framework.mybatis.plugin.dialect;
/**
 * Mysql分页器
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class MySQLDialect extends DefaultDialect{

	public boolean supportsLimitOffset(){
		return true;
	}
	
    public boolean supportsLimit() {   
        return true;   
    }  
    
	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
        if (offset > 0) {   
        	return sql + super.getSupportsOrderby() + " limit "+offsetPlaceholder+","+limitPlaceholder; 
        } else {   
            return sql + super.getSupportsOrderby() + " limit "+limitPlaceholder;
        }  
	}   
  
}
