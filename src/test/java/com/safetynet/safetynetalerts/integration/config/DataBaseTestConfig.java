package com.safetynet.safetynetalerts.integration.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.safetynet.safetynetalerts.config.DataBaseConfig;

import java.sql.*;

public class DataBaseTestConfig extends DataBaseConfig {

    private static final Logger logger = LogManager.getLogger("DataBaseTestConfig");

    public Connection getConnection() throws ClassNotFoundException, SQLException {
    	System.out.println("get connection");
        logger.info("Create DB connection");
        Class.forName("com.mysql.cj.jdbc.Driver");
        /*
        String username = FilesReader.getDAOParameter("username");
        String password = FilesReader.getDAOParameter("password");
        */
        return DriverManager.getConnection(
        		"jdbc:mysql://localhost:3306/safetynetalertsprod?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "rootroot");
    }

    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
                logger.info("Closing DB connection");
            } catch (SQLException e) {
                logger.error("Error while closing connection",e);
            }
        }
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if(ps!=null){
            try {
                ps.close();
                logger.info("Closing Prepared Statement");
            } catch (SQLException e) {
                logger.error("Error while closing prepared statement",e);
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
                logger.info("Closing Result Set");
            } catch (SQLException e) {
                logger.error("Error while closing result set",e);
            }
        }
    }
    
    
}
