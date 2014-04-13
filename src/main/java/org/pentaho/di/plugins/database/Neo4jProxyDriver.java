package org.pentaho.di.plugins.database;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class Neo4jProxyDriver implements Driver {
  
  static Driver driver;
  
  static {
    try {
      //java.sql.DriverManager.registerDriver(new Neo4jroxyDriver());
      Driver newInstance = org.neo4j.jdbc.Driver.class.newInstance();
      driver = DriverProxyInvocationChain.getProxy( Driver.class, newInstance );
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public Connection connect( String url, Properties info ) throws SQLException {
    return driver.connect( url, info );
  }

  @Override
  public boolean acceptsURL( String url ) throws SQLException {
    return driver.acceptsURL( url );
  }

  @Override
  public DriverPropertyInfo[] getPropertyInfo( String url, Properties info ) throws SQLException {
    return driver.getPropertyInfo( url, info );
  }

  @Override
  public int getMajorVersion() {
    return driver.getMajorVersion();
  }

  @Override
  public int getMinorVersion() {
    return driver.getMinorVersion();
  }

  @Override
  public boolean jdbcCompliant() {
    return driver.jdbcCompliant();
  }

  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return null;
  }

}
