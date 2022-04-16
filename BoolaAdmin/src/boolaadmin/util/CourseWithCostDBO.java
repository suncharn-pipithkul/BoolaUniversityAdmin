/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.util;

import boolaadmin.data.CourseWithCheckBox;
import boolaadmin.data.CourseWithCost;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Suncharn Pipithkul
 */
public abstract class CourseWithCostDBO
{
    /**
     * A method to get table named course joined with cost from table enrollment
     * from database with conditional clause to filter the record
     * @param ssn
     * @return courseList as ObservableList
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ObservableList<CourseWithCost> getCourseList (String ssn, String condition) throws SQLException, ClassNotFoundException 
    {
        //Declare a SELECT statement
        String selectStatement = "SELECT a.CourseId, a.Name, a.Day, a.StartTime,"
                                 + "a.EndTime, a.Room, a.Credits, b.Cost\n"
                                 + "FROM course a\n"
                                 + "JOIN enrollment b\n"
                                 + "on a.courseId = b.courseId\n"
                                 + "WHERE b.ssn =" + ssn + " AND a." + condition;
 
        //Execute SELECT statement
        try 
        {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBOperation.dbExecuteQuery(selectStatement);
 
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<CourseWithCost> courseList = FXCollections.observableArrayList();
            
            while (rs.next()) 
            {
                CourseWithCost course = new CourseWithCost();
                course.setCourseId(rs.getString("CourseId"));
                course.setName(rs.getString("Name"));
                course.setDay(rs.getString("Day"));
                course.setStartTime(rs.getTime("StartTime"));
                course.setEndTime(rs.getTime("EndTime"));
                course.setRoom(rs.getString("Room"));
                course.setCredits(rs.getInt("Credits"));
                course.setCost(rs.getInt("Cost"));
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
