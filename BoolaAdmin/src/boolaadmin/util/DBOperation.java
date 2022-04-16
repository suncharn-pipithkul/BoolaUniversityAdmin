/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.util;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Suncharn Pipithkul
 */
public abstract class DBOperation
{
    /**
     * Member variables
     */
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connect = null;
    private static final String CONNECT_URL = "jdbc:mysql://localhost/boolaadmin?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Whyd0youask?";
 
    /**
     * A method to connect to Database
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static void dbConnect() throws SQLException, ClassNotFoundException 
    {
        //Setting mySQL JDBC Driver
        try 
        {
            Class.forName(JDBC_DRIVER);
        } 
        catch (ClassNotFoundException e) 
        {
            throw new ClassNotFoundException("Can not find mySQL JDBC Driver" , e);
        }
 
        //Establish the Connection using URL, username, password
        try 
        {
            connect = DriverManager.getConnection(CONNECT_URL, USERNAME, PASSWORD);
        } 
        catch (SQLException e) 
        {
            throw new SQLException("Connection Failed! Check output console" + e.toString(), e);
        }
    }
 
    /**
     * A method to close Connection to database
     * @throws SQLException 
     */
    public static void dbDisconnect() throws SQLException 
    {
        try 
        {
            if (connect != null && !connect.isClosed())
                connect.close();
        }
        catch (SQLException e)
        {
           throw new SQLException("Can not disconnect from database", e);
        }
    }
 
    /**
     * A method to execute database query operation
     * @param query
     * @return ResultSet
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ResultSet dbExecuteQuery(String query) throws SQLException, ClassNotFoundException 
    {
        //Declare statement, resultSet and CachedResultSet as null
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSet crs = null;
        try 
        {
            //Connect to DB (Establish mySQL Connection)
            dbConnect();
 
            //Create statement
            statement = connect.createStatement();
 
            //Execute select (query) operation
            resultSet = statement.executeQuery(query);
 
            //CachedRowSet Implementation to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } 
        catch (SQLException e) 
        {
            throw new SQLException("Problem occurred at executeQuery operation : " + e, e);
        } 
        finally 
        {
            if (resultSet != null) 
                resultSet.close(); //Close resultSet
            
            if (statement != null) 
                statement.close(); //Close Statement
            //Close connection
            dbDisconnect();
        }
        
        //Return CachedRowSet
        return crs;
    }
 
    /**
     * A method to execute database update operation (insert, delete, update)
     * @param update
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static void dbExecuteUpdate(String update) throws SQLException, ClassNotFoundException 
    {
        //Declare statement as null
        Statement stmt = null;
        try 
        {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            //Create Statement
            stmt = connect.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(update);
        } 
        catch (SQLException e) 
        {
            throw new SQLException("Problem occurred at executeUpdate operation : " + e, e);
        } 
        finally 
        {
            if (stmt != null) 
                stmt.close(); //Close statement
            //Close connection
            dbDisconnect();
        }
    }
}