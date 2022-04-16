/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.layout;

import boolaadmin.data.Course;
import boolaadmin.data.CourseWithCheckBox;
import boolaadmin.data.Student;
import static boolaadmin.layout.RegistrationLayout.FULL_TIME_CREDIT;
import boolaadmin.util.CourseDBO;
import boolaadmin.util.CustomAlert;
import boolaadmin.util.EnrollmentDBO;
import boolaadmin.util.ValidateInput;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author Suncharn Pipithkul
 */
public class CourseTableLayout extends BorderPane
{
    public final int REGISTRATION_FEE = 5;
    public final int FULL_TIME_FIRST_PRICE = 285;
    public final int FULL_TIME_SECOND_PRICE = 265;
    public final int PART_TIME_PRICE = 300;
    public final int NON_CREDIT_PRICE = 150;
    
    private final Label lbWelcome = new Label("Welcome");
    public Student logInStudent = new Student();
    public Label lbFirst = new Label();
    public Label lbLast = new Label();
    public TableView<CourseWithCheckBox> tableCourse = new TableView<>();
    public TableColumn<CourseWithCheckBox,String> courseIdCol = new TableColumn("Course Id");
    public TableColumn<CourseWithCheckBox,String> courseNameCol = new TableColumn("Course name");
    public TableColumn<CourseWithCheckBox,String> dayCol = new TableColumn("Day");
    public TableColumn<CourseWithCheckBox,String> startCol = new TableColumn("Start time");
    public TableColumn<CourseWithCheckBox,String> endCol = new TableColumn("End time");
    public TableColumn<CourseWithCheckBox,String> roomCol = new TableColumn("Room");
    public TableColumn<CourseWithCheckBox,Integer> creditsCol = new TableColumn("Credits");
    public TableColumn<CourseWithCheckBox,CheckBox> selectCol = new TableColumn("Select");
    public Button btnSignOut = new Button("Sign out");
    public Button btnRegister = new Button("Register");
    
    /**
     * Contructor with string argument for mySQL conditional select statement and boolean isFullTime to differentiate between
     * full-time and part-time execution
     * @param condition
     * @param isFullTime 
     */
    public CourseTableLayout(String condition, boolean isFullTime)
    {
        lbWelcome.setStyle("-fx-font-size: 2em; -fx-font-weight: bold;");
        lbFirst.setStyle("-fx-font-size: 2em;");
        lbLast.setStyle("-fx-font-size: 2em;");
        
        tableCourse.getColumns().addAll(courseIdCol, courseNameCol, dayCol, startCol, endCol, roomCol, creditsCol, selectCol);
        tableCourse.setMaxWidth(USE_PREF_SIZE);
        
        // Define how to fill data for each cell.
        defineCellData();
        
        try
        {
            ObservableList<CourseWithCheckBox> courseList = CourseDBO.getCourseWithCheckBoxList(condition);
            tableCourse.setItems(courseList);
        
            btnRegister.setOnAction(e->
            {
                if(isFullTime)
                    registerFullTime(courseList);
                else
                    registerPartTime(courseList);
            });
        }
        catch(SQLException ex)
        {
            CustomAlert.displayErrorMsg(ex, "Database Connection error", "Can not retrieve courses list from database");
        }
        catch(ClassNotFoundException e)
        {
            CustomAlert.displayErrorMsg(e, "Database Connection error", "Can not retrieve courses list from database");
        }
        
        HBox hbName = new HBox(10, lbFirst, lbLast, new Region(), btnSignOut);
        hbName.setAlignment(Pos.CENTER_LEFT);
        
        VBox vbRegistration = new VBox(10);
        vbRegistration.setPadding(new Insets(20, 0, 20, 0));
        vbRegistration.getChildren().addAll(lbWelcome, hbName, tableCourse);
        
        setCenter(vbRegistration);
        setBottom(btnRegister);
    }
    
    /**
     * contructor with string argument for mySQL conditional select statement (used for non-credit)
     * @param condition 
     */
    public CourseTableLayout(String condition)
    {
        lbWelcome.setStyle("-fx-font-size: 2em; -fx-font-weight: bold;");
        lbFirst.setStyle("-fx-font-size: 2em;");
        lbLast.setStyle("-fx-font-size: 2em;");
        
        tableCourse.getColumns().addAll(courseIdCol, courseNameCol, dayCol, startCol, endCol, roomCol, creditsCol, selectCol);
        tableCourse.setMaxWidth(USE_PREF_SIZE);
        
        // Define how to fill data for each cell.
        defineCellData();
        
        try
        {
            ObservableList<CourseWithCheckBox> courseList = CourseDBO.getCourseWithCheckBoxList(condition);
            tableCourse.setItems(courseList);
        
            btnRegister.setOnAction(e->
            {
                registerNonCredit(courseList);
            });
        }
        catch(SQLException ex)
        {
            CustomAlert.displayErrorMsg(ex, "Database Connection error", "Can not retrieve courses list from database");
        }
        catch(ClassNotFoundException e)
        {
            CustomAlert.displayErrorMsg(e, "Database Connection error", "Can not retrieve courses list from database");
        }
        
        HBox hbName = new HBox(10, lbFirst, lbLast, new Region(), btnSignOut);
        hbName.setAlignment(Pos.CENTER_LEFT);
        
        VBox vbRegistration = new VBox(10);
        vbRegistration.setPadding(new Insets(20, 0, 20, 0));
        vbRegistration.getChildren().addAll(lbWelcome, hbName, tableCourse);
        
        setCenter(vbRegistration);
        setBottom(btnRegister);
    }
    
    /**
     * A method to calculate full-time cost
     * The cost = registration fee + tuition.
     * The tuition calculate as followed:
     * the first 9 credits cost $285 and after 9 credits cost $265 per 3 credits course.
     * @param numCredits
     * @return cost integer
     */
    private int calFullTimeCost(int numCredits)
    {
        int cost = 0;
        if(numCredits > 0 && numCredits <= FULL_TIME_CREDIT)
            cost = FULL_TIME_FIRST_PRICE; // First 9 credits
        else if (numCredits > FULL_TIME_CREDIT)
            cost = FULL_TIME_SECOND_PRICE; // after 9 credits
        else
            cost = 0;
        return cost;
    }
    
    /**
     * A method to calculate part-time cost
     * The cost = registration fee + tuition.
     * The tuition is $300 per 3 credits course.
     * @param numCredits
     * @return cost integer
     */
    private int calPartTimeCost(int numCredits)
    {
        int cost = PART_TIME_PRICE;
        return cost;
    }
    
    /**
     * A method to calculate non-credit cost
     * The cost = registration fee + tuition.
     * The tuition is $150 per course.
     * @param numCredits
     * @return cost integer
     */
    private int calNonCreditCost()
    {
        int cost = NON_CREDIT_PRICE;
        return cost;
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
       selectCol.setCellValueFactory(new PropertyValueFactory<>("select"));
    }
    
    /**
     * A method to validate and process full-time registration
     * @param courseList 
     */
    private void registerFullTime(ObservableList<CourseWithCheckBox> courseList)
    {
        int countCredits = 0;
        boolean isSelected = false;
        boolean isFullTime = true;
                
        ObservableList<CourseWithCheckBox> selectedList = FXCollections.observableArrayList();
                
        // Loop the course list to see which one was selected
        for(CourseWithCheckBox c : courseList)
        {
            if(c.getSelect().isSelected())
            {
                selectedList.add(c);
                isSelected = true;
                countCredits += c.getCredits(); // Count number of credits selected
            }
            System.out.println("Credit: " + countCredits);
        }
        
        // Validate registered less than 9 credits
        if(countCredits < FULL_TIME_CREDIT)
            isFullTime = false;
              
        // Validate time conflict
        String msg = ValidateInput.validateTime(selectedList);
        if(msg.length() != 0 || !msg.isEmpty())
        {
            // If there is no validation error, then insert data to mySQL database in table enrollment
            if(isSelected)
            {
                if(isFullTime)
                {
                    try
                    {
                        int numCredit = 0;

                        for(CourseWithCheckBox c : selectedList)
                        {
                            numCredit += c.getCredits();
                            System.out.println("numCredit: " + numCredit);
                            int cost = calFullTimeCost(numCredit);
                            EnrollmentDBO.insertEnrollment(logInStudent.getSsn(), c.getCourseId(), cost);
                        }
                        CustomAlert.displayInfoMsg("Course updated", "Full-time Register Successful!");
                    }
                    catch(SQLException ex1)
                    {
                        CustomAlert.displayErrorMsg(ex1, "Database Connection error", "Can not register courses to database");
                    }
                    catch(ClassNotFoundException ex2)
                    {
                        CustomAlert.displayErrorMsg(ex2, "Database Connection error", "Can not find the Driver");
                    } 
                }
                else
                    // Display message to register as part-time
                    CustomAlert.displayErrorMsg("Invalid Registration", 
                                                "Please select at least 9 credits to register as full-time.\n" +
                                                "Otherwise, select Part-time menu.");
            }
            else
                CustomAlert.displayErrorMsg("No course was selected", "Please select at least 9 credits to register as full-time.\n");
        }
        else
            CustomAlert.displayErrorMsg("Time conflict", "Courses time are elapse\n");
    }
    
    /**
     * A method to validate and process part-time registration
     * @param courseList 
     */
    private void registerPartTime(ObservableList<CourseWithCheckBox> courseList)
    {
        int countCredits = 0;
        boolean isSelected = false;
        boolean isFullTime = false;
        
        ObservableList<CourseWithCheckBox> selectedList = FXCollections.observableArrayList();
                
        // Loop the course list to see which one was selected
        for(CourseWithCheckBox c : courseList)
        {
            if(c.getSelect().isSelected())
            {
                selectedList.add(c);
                isSelected = true;
                countCredits += c.getCredits(); // Count number of credits selected
            }
        }
        
        // Validate registered less than 9 credits
        if(countCredits >= FULL_TIME_CREDIT)
            isFullTime = true;
        
        // Validate time conflict
        String msg = ValidateInput.validateTime(selectedList);
        if(msg.length() != 0 || !msg.isEmpty())
        {
            // If there is no validation error, then insert data to mySQL database in table enrollment
            if(isSelected)
            {
                if(!isFullTime)
                {
                    try
                    {
                        for(CourseWithCheckBox c : selectedList)
                            EnrollmentDBO.insertEnrollment(logInStudent.getSsn(), c.getCourseId(), PART_TIME_PRICE);
                        CustomAlert.displayInfoMsg("Course updated", "Part-time Register Successful!");
                    }
                    catch(SQLException ex1)
                    {
                        CustomAlert.displayErrorMsg(ex1, "Database Connection error", "Can not register courses to database");
                    }
                    catch(ClassNotFoundException ex2)
                    {
                        CustomAlert.displayErrorMsg(ex2, "Database Connection error", "Can not find the Driver");
                    }
                }
                else
                    // Display message to register as part-time
                    CustomAlert.displayErrorMsg("Invalid Registration", 
                                                "Please select at most 6 credits to register as part-time.\n" +
                                                "Otherwise, select full-time menu.");
            }
            else
                CustomAlert.displayErrorMsg("No course was selected", "Please select at most 6 credits to register as part-time.\n");
        }
        else
            CustomAlert.displayErrorMsg("Time conflict", "Courses time are elapse\n");
    }
    
    /**
     * A method to validate and process non-credit registration
     * @param courseList 
     */
    private void registerNonCredit(ObservableList<CourseWithCheckBox> courseList)
    {
        boolean isSelected = false;
                
        ObservableList<CourseWithCheckBox> selectedList = FXCollections.observableArrayList();
                
        // Loop the course list to see which one was selected
        for(CourseWithCheckBox c : courseList)
        {
            if(c.getSelect().isSelected())
            {
                selectedList.add(c);
                isSelected = true;
            }
        }
        
        // Validate time conflict
        String msg = ValidateInput.validateTime(selectedList);
        if(msg.length() != 0 || !msg.isEmpty())
        {
            // If there is no validation error, then insert data to mySQL database in table enrollment
            if(isSelected)
            {
                try
                {
                    for(CourseWithCheckBox c : selectedList)
                        EnrollmentDBO.insertEnrollment(logInStudent.getSsn(), c.getCourseId(), NON_CREDIT_PRICE);
                    CustomAlert.displayInfoMsg("Course updated", "Non-credit Register Successful!");
                }
                catch(SQLException ex1)
                {
                    CustomAlert.displayErrorMsg(ex1, "Database Connection error", "Can not register courses to database");
                }
                catch(ClassNotFoundException ex2)
                {
                    CustomAlert.displayErrorMsg(ex2, "Database Connection error", "Can not find the Driver");
                }
            }
            else
                CustomAlert.displayErrorMsg("No course was selected", "Please select at most 6 credits to register as part-time.\n");
        }
        else
            CustomAlert.displayErrorMsg("Time conflict", "Courses time are elapse\n");
    }
}
