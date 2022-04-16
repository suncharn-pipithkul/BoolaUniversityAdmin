/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.util;

import boolaadmin.data.CourseWithCheckBox;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author Suncharn Pipithkul
 */
public abstract class EnrollmentDBO
{
    public static void insertEnrollment(String ssn, String courseId, int cost) throws SQLException, ClassNotFoundException
    {
        // Declare update statement
        String update = "INSERT INTO enrollment\n" + 
                        "VALUES\n" +
                        "('" + ssn + "','" + courseId + "','" + cost +"')";
        
        // Execute INSERT operation 
        try
        {
            DBOperation.dbExecuteUpdate(update);
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
}
