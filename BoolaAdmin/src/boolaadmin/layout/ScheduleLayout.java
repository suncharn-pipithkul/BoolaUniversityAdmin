/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.layout;

import boolaadmin.data.Course;
import boolaadmin.data.CourseWithCheckBox;
import boolaadmin.data.CourseWithCost;
import boolaadmin.data.Enrollment;
import boolaadmin.data.Student;
import boolaadmin.util.CourseDBO;
import boolaadmin.util.CustomAlert;
import boolaadmin.util.StudentDBO;
import boolaadmin.util.ValidateInput;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.VBox;

/**
 *
 * @author Suncharn Pipithkul
 */
public class ScheduleLayout extends BorderPane
{
    private SignInLayout signInLayout = new SignInLayout();
    public Label lbTitle = new Label();
    public Label lbFirst = new Label();
    public Label lbLast = new Label();
    public TableView tbCourse = new TableView();
    public TableColumn<Course,String> courseIdCol = new TableColumn("Course Id");
    public TableColumn<Course,String> courseNameCol = new TableColumn("Course name");
    public TableColumn<Course,String> dayCol = new TableColumn("Day");
    public TableColumn<Course,String> startCol = new TableColumn("Start time");
    public TableColumn<Course,String> endCol = new TableColumn("End time");
    public TableColumn<Course,String> roomCol = new TableColumn("Room");
    public TableColumn<Course,Integer> creditsCol = new TableColumn("Credits");
    
    public ScheduleLayout(String title)
    {
        lbFirst.setStyle("-fx-font-size: 2em;");
        lbLast.setStyle("-fx-font-size: 2em;");
        
        tbCourse.getColumns().addAll(courseIdCol, courseNameCol, dayCol, startCol, endCol, roomCol, creditsCol);
        tbCourse.setMaxWidth(USE_PREF_SIZE);
        
        // Define how to fill data for each cell.
        defineCellData();
        
        signInLayout.btnSignIn.setOnAction(e->
        {
            String msg = "";
            msg += ValidateInput.validateTextField(signInLayout.tfSSN, signInLayout.lbErrSSN, "Please enter SSN", "\\d{9}", "Please enter 9 digits"); // Validate SSN
            
            // If error message is empty (Valid input field), check if the student is in database
            if(msg.length() == 0 || msg.isEmpty())
            {
                try
                {
                    String ssn = signInLayout.tfSSN.getText();
                    // try search for the student
                    Student student = StudentDBO.searchStudent(ssn);
                    
                    lbFirst.setText(student.getFirstName());
                    lbLast.setText(student.getLastName());
                    
                    try
                    {
                        ObservableList<Course> courseList = CourseDBO.getCourseList(ssn);
                        tbCourse.setItems(courseList);
                    }
                    catch(SQLException ex)
                    {
                        CustomAlert.displayErrorMsg(ex, "Database Connection error", "Can not retrieve courses list from database");
                    }
                    catch(ClassNotFoundException exc)
                    {
                        CustomAlert.displayErrorMsg(exc, "Database Connection error", "Can not rind the Driver");
                    }
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
        
        HBox hbName = new HBox(10, lbFirst, lbLast);
        VBox vbTables = new VBox(10, signInLayout, hbName, tbCourse);
        
        setCenter(vbTables);
        
        lbTitle.setStyle("-fx-font-size: 3em; -fx-font-weight: bold;");
        lbTitle.setText(title);
        setPadding(new Insets(20, 20, 40, 20));
        setTop(lbTitle);
    }
    
    /**
     * A method that defines how to fill data for each cell.
     */
    private void defineCellData()
    {
       courseIdCol.setCellValueFactory(new PropertyValueFactory<>("courseId"));
       courseNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
       startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
       endCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
       roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
       creditsCol.setCellValueFactory(new PropertyValueFactory<>("credits")); 
    }
}
