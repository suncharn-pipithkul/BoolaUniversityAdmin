/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.util;

import boolaadmin.data.Course;
import boolaadmin.data.CourseWithCheckBox;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Suncharn Pipithkul
 */
public abstract class CourseDBO
{
    /**
     * A method to get table named course from database with conditional clause to filter the record
     * @param condition
     * @return courseList as ObservableList
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ObservableList<CourseWithCheckBox> getCourseWithCheckBoxList (String condition) throws SQLException, ClassNotFoundException 
    {
        //Declare a SELECT statement
        String selectStatement = "SELECT * FROM course where " + condition;
 
        //Execute SELECT statement
        try 
        {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBOperation.dbExecuteQuery(selectStatement);
 
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<CourseWithCheckBox> courseList = FXCollections.observableArrayList();
            
            while (rs.next()) 
            {
                CourseWithCheckBox course = new CourseWithCheckBox();
                course.setCourseId(rs.getString("CourseId"));
                course.setName(rs.getString("Name"));
                course.setDay(rs.getString("Day"));
                course.setStartTime(rs.getTime("StartTime"));
                course.setEndTime(rs.getTime("EndTime"));
                course.setRoom(rs.getString("Room"));
                course.setCredits(rs.getInt("Credits"));
                //Add course to the ObservableList
                courseList.add(course);
            }
 
            // Close resultSet
            rs.close();
            
            //Return courseList object
            return courseList;
        } 
        catch (SQLException e) 
        {
            throw e;
        }
    }
    
    /**
     * A method to get table named course from database
     * @return courseList as ObservableList
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ObservableList<CourseWithCheckBox> getCourseWithCheckBoxList () throws SQLException, ClassNotFoundException 
    {
        //Declare a SELECT statement
        String selectStatement = "SELECT * FROM course";
 
        //Execute SELECT statement
        try 
        {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBOperation.dbExecuteQuery(selectStatement);
 
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<CourseWithCheckBox> courseList = FXCollections.observableArrayList();
            
            while (rs.next()) 
            {
                CourseWithCheckBox course = new CourseWithCheckBox();
                course.setCourseId(rs.getString("CourseId"));
                course.setName(rs.getString("Name"));
                course.setDay(rs.getString("Day"));
                course.setStartTime(rs.getTime("StartTime"));
                course.setEndTime(rs.getTime("EndTime"));
                course.setRoom(rs.getString("Room"));
                course.setCredits(rs.getInt("Credits"));
                //Add course to the ObservableList
                courseList.add(course);
            }
 
            // Close resultSet
            rs.close();
            
            //Return courseList object
            return courseList;
        } 
        catch (SQLException e) 
        {
            throw e;
        }
    }
    
    /**
     * A method to get table named course from database with conditional clause to filter the record
     * @param condition
     * @return courseList as ObservableList
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ObservableList<Course> getCourseList (String ssn) throws SQLException, ClassNotFoundException 
    {
        //Declare a SELECT statement
        String selectStatement = "SELECT a.CourseId, a.Name, a.Day, a.StartTime,"
                                 + "a.EndTime, a.Room, a.Credits, b.Cost\n"
                                 + "FROM course a\n"
                                 + "JOIN enrollment b\n"
                                 + "on a.courseId = b.courseId\n"
                                 + "WHERE b.ssn =" + ssn;
 
        //Execute SELECT statement
        try 
        {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBOperation.dbExecuteQuery(selectStatement);
 
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Course> courseList = FXCollections.observableArrayList();
            
            while (rs.next()) 
            {
                Course course = new Course();
                course.setCourseId(rs.getString("CourseId"));
                course.setName(rs.getString("Name"));
                course.setDay(rs.getString("Day"));
                course.setStartTime(rs.getTime("StartTime"));
                course.setEndTime(rs.getTime("EndTime"));
                course.setRoom(rs.getString("Room"));
                course.setCredits(rs.getInt("Credits"));
                //Add course to the ObservableList
                courseList.add(course);
            }
 
            // Close resultSet
            rs.close();
            
            //Return courseList object
            return courseList;
        } 
        catch (SQLException e) 
        {
            throw e;
        }
    }
    
    /**
     * A method to get table named course from database
     * @return courseList as ObservableList
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ObservableList<Course> getCourseList () throws SQLException, ClassNotFoundException 
    {
        //Declare a SELECT statement
        String selectStatement = "SELECT * FROM course";
 
        //Execute SELECT statement
        try 
        {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBOperation.dbExecuteQuery(selectStatement);
 
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Course> courseList = FXCollections.observableArrayList();
            
            while (rs.next()) 
            {
                Course course = new Course();
                course.setCourseId(rs.getString("CourseId"));
                course.setName(rs.getString("Name"));
                course.setDay(rs.getString("Day"));
                course.setStartTime(rs.getTime("StartTime"));
                course.setEndTime(rs.getTime("EndTime"));
                course.setRoom(rs.getString("Room"));
                course.setCredits(rs.getInt("Credits"));
                //Add course to the ObservableList
                courseList.add(course);
            }
 
            // Close resultSet
            rs.close();
            
            //Return courseList object
            return courseList;
        } 
        catch (SQLException e) 
        {
            throw e;
        }
    }
}
