/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.util;

import boolaadmin.data.Student;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Suncharn Pipithkul
 */
public abstract class StudentDBO
{
    /**
     * A method to insert student to mySQL database
     * @param ssn
     * @param first
     * @param mid
     * @param last
     * @param st
     * @param city
     * @param state
     * @param zip
     * @param year
     * @param degree
     * @param hs
     * @param imm
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static void insertStudent(String ssn, String first, String mid, String last, String st, 
                                     String city, String state, String zip, String year, String degree,
                                     String hs, String imm) throws SQLException, ClassNotFoundException
    {
        // Declare update statement
        String updateStatement = "INSERT INTO Student\n" + 
                        "VALUES\n" +
                        "('" + ssn + "','" + first + "','" + mid + "','" + last + "','" +
                        st + "','" + city + "','" + state + "','" + zip + "','" + year + "','" +
                        degree + "','" + hs + "','" + imm + "')";
        
        // Execute INSERT operation 
        try
        {
            DBOperation.dbExecuteUpdate(updateStatement);
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        catch(ClassNotFoundException e)
        {
            throw e;
        }
    }
    
    /**
     * A method that search a student by SSN
     * @param ssn
     * @return Student object
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static Student searchStudent(String ssn) throws SQLException, ClassNotFoundException
    {
        // Declare select statement
        String selectStatement = "SELECT * FROM Student WHERE ssn = '" + ssn + "';";
        
        // Declare a student object
        Student student = null;
        
        // Execute SELECT operation
        try
        {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBOperation.dbExecuteQuery(selectStatement);
            
            if(rs.next())
            {
                student = new Student(rs.getString("SSN"), rs.getString("FirstName"), rs.getString("MI"),
                                      rs.getString("LastName"), rs.getString("Street"), rs.getString("City"),
                                      rs.getString("State"), rs.getString("Zip"), rs.getString("Class"),
                                      rs.getString("Degree"), rs.getString("HS"), rs.getString("IMM"));
            }
            // Close resultSet
            rs.close();
            
            // Return Student Object
            return student;
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        catch(ClassNotFoundException e)
        {
            throw e;
        }
    }
    
//    /**
//     * A method to update existing student cost
//     * @param ssn
//     * @param cost
//     * @throws SQLException
//     * @throws ClassNotFoundException 
//     */
//    public static void updateStudentCost(String ssn, int cost) throws SQLException, ClassNotFoundException
//    {
//        // Declare update statement
//        String updateStatement = "UPDATE Student\n" + 
//                        "SET Cost = " + cost + "\n" +
//                        "WHERE SSN = " + ssn + "\n";
//        
//        // Execute UPDATE operation 
//        try
//        {
//            DBOperation.dbExecuteUpdate(updateStatement);
//        }
//        catch(SQLException ex)
//        {
//            ex.printStackTrace();
//            throw ex;
//        }
//        catch(ClassNotFoundException e)
//        {
//            throw e;
//        }
//    }
}
