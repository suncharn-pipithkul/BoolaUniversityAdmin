/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.layout;

import boolaadmin.data.CourseWithCheckBox;
import boolaadmin.data.CourseWithCost;
import boolaadmin.data.Enrollment;
import boolaadmin.data.Student;
import boolaadmin.util.CourseDBO;
import boolaadmin.util.CourseWithCostDBO;
import boolaadmin.util.CustomAlert;
import boolaadmin.util.StudentDBO;
import boolaadmin.util.ValidateInput;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Suncharn Pipithkul
 */
public class ReceivableLayout extends BorderPane
{
    private SignInLayout signInLayout = new SignInLayout();
    public Label lbTitle = new Label();
    public Label lbFirst = new Label();
    public Label lbLast = new Label();
    public Label lbCreditCourse = new Label("Credit courses:");
    public TableView tbCreditCourse = new TableView();
    public TableView tbNonCreditCourse = new TableView();
    public TableColumn<CourseWithCost,String> cCourseIdCol = new TableColumn("Course Id");
    public TableColumn<CourseWithCost,String> cCourseNameCol = new TableColumn("Course name");
    public TableColumn<CourseWithCost,String> cDayCol = new TableColumn("Day");
    public TableColumn<CourseWithCost,String> cStartCol = new TableColumn("Start time");
    public TableColumn<CourseWithCost,String> cEndCol = new TableColumn("End time");
    public TableColumn<CourseWithCost,String> cRoomCol = new TableColumn("Room");
    public TableColumn<CourseWithCost,Integer> cCreditsCol = new TableColumn("Credits");
    public TableColumn<Enrollment,Integer> cCostCol = new TableColumn("Cost");
    public Label lbSubCreditCost = new Label();
    
    public TableColumn<CourseWithCost,String> nCourseIdCol = new TableColumn("Course Id");
    public TableColumn<CourseWithCost,String> nCourseNameCol = new TableColumn("Course name");
    public TableColumn<CourseWithCost,String> nDayCol = new TableColumn("Day");
    public TableColumn<CourseWithCost,String> nStartCol = new TableColumn("Start time");
    public TableColumn<CourseWithCost,String> nEndCol = new TableColumn("End time");
    public TableColumn<CourseWithCost,String> nRoomCol = new TableColumn("Room");
    public TableColumn<CourseWithCost,Integer> nCreditsCol = new TableColumn("Credits");
    public TableColumn<Enrollment,Integer> nCostCol = new TableColumn("Cost");
    public Label lbNonCreditCourse = new Label("Non-credit courses:");
    public Label lbSubNonCreditCost = new Label();
    public Label lbTotalCost = new Label();
    
    public ReceivableLayout(String title, String condition1, String condition2)
    {
        lbFirst.setStyle("-fx-font-size: 2em;");
        lbLast.setStyle("-fx-font-size: 2em;");
        lbCreditCourse.setStyle("-fx-font-size: 2em; -fx-font-weight: bold;");
        lbNonCreditCourse.setStyle("-fx-font-size: 2em; -fx-font-weight: bold;");
        
        tbCreditCourse.getColumns().addAll(cCourseIdCol, cCourseNameCol, cDayCol, cStartCol, cEndCol, cRoomCol, cCreditsCol, cCostCol);
        tbCreditCourse.setMaxWidth(USE_PREF_SIZE);
        tbNonCreditCourse.getColumns().addAll(nCourseIdCol, nCourseNameCol, nDayCol, nStartCol, nEndCol, nRoomCol, nCreditsCol, nCostCol);
        tbNonCreditCourse.setMaxWidth(USE_PREF_SIZE);
        
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
                        ObservableList<CourseWithCost> creditCourseList = CourseWithCostDBO.getCourseList(ssn, condition1);
                        ObservableList<CourseWithCost> nonCreditCourseList = CourseWithCostDBO.getCourseList(ssn, condition2);

                        tbCreditCourse.setItems(creditCourseList);
                        tbNonCreditCourse.setItems(nonCreditCourseList);
                        int subCreditCost = 0;
                        int subNonCreditCost = 0;
                        for(CourseWithCost c : creditCourseList)
                            subCreditCost += c.getCost();
                        for(CourseWithCost c : nonCreditCourseList)
                            subNonCreditCost += c.getCost();
                        lbSubCreditCost.setText("Credit cost: $" + subCreditCost);
                        lbSubNonCreditCost.setText("Non-Credit cost: $" + subNonCreditCost);
                        int totalCost = subCreditCost + subNonCreditCost;
                        lbTotalCost.setText("Total cost: $" + totalCost);
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
        VBox vbTables = new VBox(10, signInLayout, hbName,
                lbCreditCourse, tbCreditCourse, lbSubCreditCost,
                lbNonCreditCourse, tbNonCreditCourse, lbSubNonCreditCost, lbTotalCost);
        
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
       cCourseIdCol.setCellValueFactory(new PropertyValueFactory<>("courseId"));
       cCourseNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       cDayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
       cStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
       cEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
       cRoomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
       cCreditsCol.setCellValueFactory(new PropertyValueFactory<>("credits")); 
       cCostCol.setCellValueFactory(new PropertyValueFactory<>("Cost"));
       
       nCourseIdCol.setCellValueFactory(new PropertyValueFactory<>("courseId"));
       nCourseNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       nDayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
       nStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
       nEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
       nRoomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
       nCreditsCol.setCellValueFactory(new PropertyValueFactory<>("credits")); 
       nCostCol.setCellValueFactory(new PropertyValueFactory<>("Cost"));
    }
}
