package org.zmsoft.framework.mybatis.plugin;

import java.sql.Connection;

import org.springframework.stereotype.Component;

@Component
public class MyConnectionHolder {

	private Connection connection = null;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
