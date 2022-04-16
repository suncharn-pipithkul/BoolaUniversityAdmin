/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.layout;

import boolaadmin.util.ValidateInput;
import boolaadmin.data.Student;
import boolaadmin.util.CustomAlert;
import boolaadmin.util.StudentDBO;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Suncharn Pipithkul
 */
public class RegistrationLayout extends BorderPane
{
    public final static int FULL_TIME_CREDIT = 9;
    public Label lbTitle = new Label();
    private SignInLayout signInLayout = new SignInLayout();
    private CourseTableLayout tableLayout;
    
    /**
     * Contructor with argument for title, SQL condition where clause, boolean for isFullTime
     * @param title
     * @param condition
     * @param isFullTime 
     */
    public RegistrationLayout(String title, String condition, boolean isFullTime)
    {
        lbTitle.setText(title);
        lbTitle.setStyle("-fx-font-size: 3em; -fx-font-weight: bold;");
        
        setCenter(signInLayout);
        
        signInLayout.btnSignIn.setOnAction(e->
        {
            String msg = "";
            msg += ValidateInput.validateTextField(signInLayout.tfSSN, signInLayout.lbErrSSN, "Please enter SSN", "\\d{9}", "Please enter 9 digits"); // Validate SSN
            
            // If error message is empty (Valid input field), check if the student is in database
            if(msg.length() == 0 || msg.isEmpty())
            {
                try
                {
                    // try search for the student
                    Student student = StudentDBO.searchStudent(signInLayout.tfSSN.getText());
                    
                    signInLayout.tfSSN.clear();
                    tableLayout = new CourseTableLayout(condition, isFullTime);
                    tableLayout.lbFirst.setText(student.getFirstName());
                    tableLayout.lbLast.setText(student.getLastName());
                    tableLayout.logInStudent = student;
                    
                    tableLayout.btnSignOut.setOnAction(event-> setCenter(signInLayout));
                    
                    setCenter(tableLayout);
                }
                catch(SQLException ex1)
                {
                    ex1.printStackTrace();
                    CustomAlert.displayErrorMsg(ex1, "Database Connection error", "Can not find student in the database");
                }
                catch(ClassNotFoundException ex2)
                {
                    ex2.printStackTrace();
                    CustomAlert.displayErrorMsg(ex2, "Database Connection error", "Can not find Driver");
                }
                catch(Exception ex3)
                {
                   CustomAlert.displayErrorMsg("Invalid SSN", "The student have not gone through admission process"); 
                }
            }
        });
        
        setPadding(new Insets(20, 20, 40, 20));
        setTop(lbTitle);
    }
    
    /**
     * Contructor with argument for title and SQL condition where clause
     * @param title
     * @param condition
     */
    public RegistrationLayout(String title, String condition)
    {
        lbTitle.setText(title);
        lbTitle.setStyle("-fx-font-size: 3em; -fx-font-weight: bold;");
        
        setCenter(signInLayout);
        
        signInLayout.btnSignIn.setOnAction(e->
        {
            String msg = "";
            msg += ValidateInput.validateTextField(signInLayout.tfSSN, signInLayout.lbErrSSN, "Please enter SSN", "\\d{9}", "Please enter 9 digits"); // Validate SSN
            
            // If error message is empty (Valid input field), check if the student is in database
            if(msg.length() == 0 || msg.isEmpty())
            {
                try
                {
                    // try search for the student
                    Student student = StudentDBO.searchStudent(signInLayout.tfSSN.getText());
                    
                    signInLayout.tfSSN.clear();
                    tableLayout = new CourseTableLayout(condition);
                    tableLayout.lbFirst.setText(student.getFirstName());
                    tableLayout.lbLast.setText(student.getLastName());
                    tableLayout.logInStudent = student;
                    
                    tableLayout.btnSignOut.setOnAction(event-> setCenter(signInLayout));
                    
                    setCenter(tableLayout);
                }
                catch(SQLException ex1)
                {
                    ex1.printStackTrace();
                    CustomAlert.displayErrorMsg(ex1, "Database Connection error", "Can not find student in the database");
                }
                catch(ClassNotFoundException ex2)
                {
                    ex2.printStackTrace();
                    CustomAlert.displayErrorMsg(ex2, "Database Connection error", "Can not find Driver");
                }
                catch(Exception ex3)
                {
                   CustomAlert.displayErrorMsg("Invalid SSN", "The student have not gone through admission process"); 
                }
            }
        });
        
        setPadding(new Insets(20, 20, 40, 20));
        setTop(lbTitle);
    }
}
