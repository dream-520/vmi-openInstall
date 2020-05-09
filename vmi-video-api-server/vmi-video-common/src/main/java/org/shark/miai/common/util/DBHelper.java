package org.shark.miai.common.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DBHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DBHelper.class);
	
	/**
	 * 链接数据库
	 * @return Connection
	 */
	public static Connection getConnection(SqlSession sqlSession){
		LOGGER.info("get connection!~~~");
		
        Connection conn = null;
        try {
            conn =  sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
            LOGGER.info("===This Connection isClosed ? "+conn.isClosed());
        } catch (Exception e) {
        	LOGGER.error(e.getMessage() , e);
        }
        return conn;
    }
	
	/**
	 * 关闭连接
	 * @param rs - ResultSet
	 * @param st - Statement
	 * @param conn - Connection
	 */
	public static void closeDBA(ResultSet rs , Statement st , Connection conn){
		LOGGER.info("close dba!~~~");
		
		if(rs != null) {
			try{
				rs.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage() , e);
			}
		}
		if(st != null) {
			try{
				st.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage() , e);
			}
		}
		if(conn != null) {
			try{
				conn.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage() , e);
			}
		}
	}
	
	private DBHelper(){}

}




