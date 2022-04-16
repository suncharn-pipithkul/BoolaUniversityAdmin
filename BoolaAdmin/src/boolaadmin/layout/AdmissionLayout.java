/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.layout;

import boolaadmin.util.CustomAlert;
import boolaadmin.util.StudentDBO;
import boolaadmin.util.ValidateInput;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Suncharn Pipithkul
 */
public class AdmissionLayout extends BorderPane
{
    private static Alert alSuccess = new Alert(Alert.AlertType.INFORMATION);
    private static Alert alConnect = new Alert(Alert.AlertType.ERROR);
    
    public Label lbTitle = new Label();
    public TextField tfSSN = new TextField();
    public TextField tfFirstName = new TextField();
    public TextField tfMidName = new TextField();
    public TextField tfLastName = new TextField();
    public TextField tfStreet = new TextField();
    public TextField tfCity = new TextField();
    public ComboBox cbState = new ComboBox();
    public TextField tfZip = new TextField();
    public DatePicker dpToday = new DatePicker();
    public ComboBox cbYear = new ComboBox();
    public ComboBox cbDegree = new ComboBox();
    public CheckBox chbHS = new CheckBox("High School Diploma");
    public CheckBox chbIMM = new CheckBox("Immunization Record");
    public Button btnSubmit = new Button("Submit");
    public GridPane gpAdmission = new GridPane();
    private Label lbErrSSN;
    private Label lbErrName;
    private Label lbErrStreet;
    private Label lbErrCity;
    private Label lbErrState;
    private Label lbErrZip;
    private Label lbErrYear;
    private Label lbErrDegree;
    private Label lbErrHS;
    private Label lbErrIMM;
    
    private String[] strYear = {"Freshman", "Sophomore", "Junior", "Senior"};
    private String[] strDegree = {"AS in Computer Programming", "AA in Humanities"};
    private String[] strStates = 
    {
            "AK Alaska", "AL Alabama", "AR Arkansas", "AS American Samoa", "AZ Arizona", 
            "CA California", "CO Colorado", "CT Connecticut", "DC District of Columbia", "DE Delaware", 
            "FL Florida", "GA Georgia", "GU Guam", "HI Hawaii", "IA Iowa",
            "ID Idaho", "IL Illinois", "IN Indiana", "KS Kansas", "KY Kentucky",
            "LA Louisiana", "MA Massachusetts", "MD Maryland", "ME Maine", "MI Michigan",
            "MN Minnesota", "MO Missouri", "MP Northern Marian Islands", "MS Mississippi", "MT Montana",
            "NC North Carolina", "ND North Dakota", "NE Nebraska", "NH New Hampshire", "NJ New Jersey",
            "NM New Mexico", "NV Nevada", "NY New York", "OH Ohio", "OK Oklahoma",
            "OR Oregon", "PA Pennsylvania", "PR Puerto Rico", "RI Rhode Island", "SC South Carolina",
            "SD South Dakota", "TN Tennessee", "TX Texas", "UT Utah", "VA Virginia", 
            "VI U.S. Virgin Islands", "VT Vermont", "WA Washington", "WV West Virginia", "WI Wisconsin",
            "WY Wyoming"
    };

    /**
     * Constructor with arguments boolean to indicate which field to add to GridPane and String title
     * @param nonMatriculated 
     */
    public AdmissionLayout(boolean isMatriculated, String title)
    {
        initAdmissionLayout(isMatriculated, title);
    }
    
    /**
     * A method to initialize Admission Layout.
     * It sets the title with String title argument.
     * If isMatriculated is true, it adds extra fields to the layout.
     * The extra fields are DatePicker todayDate, ComboBox year, ComboBox degree,
     * CheckBox high school diploma.
     * @param isMaticulated
     * @param title 
     */
    private void initAdmissionLayout(boolean isMatriculated, String title)
    {
        lbTitle.setText(title);
        lbTitle.setStyle("-fx-font-size: 3em; -fx-font-weight: bold;");
        
        // Error Message Label
        lbErrSSN = ValidateInput.createErrorLabel();
        lbErrName = ValidateInput.createErrorLabel();
        lbErrStreet = ValidateInput.createErrorLabel();
        lbErrCity = ValidateInput.createErrorLabel();
        lbErrState = ValidateInput.createErrorLabel();
        lbErrZip = ValidateInput.createErrorLabel();
        lbErrYear = ValidateInput.createErrorLabel();
        lbErrDegree = ValidateInput.createErrorLabel();
        lbErrHS = ValidateInput.createErrorLabel();
        lbErrIMM = ValidateInput.createErrorLabel();
        
        // Row 0: Title
        gpAdmission.add(lbTitle, 0, 0, 10, 1);
        
        // Row 1: SSN
        gpAdmission.add(new Label("SSN:"), 0, 1);
        gpAdmission.add(tfSSN, 1, 1);
        gpAdmission.add(lbErrSSN, 2, 1, 10, 1);
        ValidateInput.addErrorLabelListener(tfSSN, lbErrSSN);
        
        // Row 2: Full Name
        gpAdmission.add(new Label("First name:"), 0, 2);
        gpAdmission.add(tfFirstName, 1, 2, 2, 1);
        tfFirstName.setPrefColumnCount(10);
        gpAdmission.add(new Label("MI:"), 3, 2);
        gpAdmission.add(tfMidName, 4, 2);
        tfMidName.setPrefColumnCount(1);
        gpAdmission.add(new Label("Last name:"), 5, 2);
        gpAdmission.add(tfLastName, 6, 2);
        tfLastName.setPrefColumnCount(10);
        gpAdmission.add(lbErrName, 7, 2);
        ValidateInput.addErrorLabelListener(tfFirstName, lbErrName);
        ValidateInput.addErrorLabelListener(tfLastName, lbErrName);
        
        // Row 3: Street Address
        gpAdmission.add(new Label("Street address:"), 0, 3);
        gpAdmission.add(tfStreet, 1, 3, 4, 1);
        gpAdmission.add(lbErrStreet, 5, 3, 6, 1);
        ValidateInput.addErrorLabelListener(tfStreet, lbErrStreet);
        
        // Row 4: City/State
        // City
        gpAdmission.add(new Label("City:"), 0, 4);
        gpAdmission.add(tfCity, 1, 4);
        gpAdmission.add(lbErrCity, 2, 4, 9, 1);
        ValidateInput.addErrorLabelListener(tfCity, lbErrCity);
        
        // State
        for(int i = 0; i < strStates.length; ++i)
            cbState.getItems().add(strStates[i]);
        gpAdmission.add(new Label("State/Territory:"), 5, 4);
        gpAdmission.add(cbState, 6, 4);
        gpAdmission.add(lbErrState, 7, 4);
        ValidateInput.addErrorLabelListener(cbState, lbErrState);
        
        // Row 5: ZIP code
        gpAdmission.add(new Label("ZIP:"), 0, 5);
        gpAdmission.add(tfZip, 1, 5);
        gpAdmission.add(lbErrZip, 2, 5, 6, 1);
        ValidateInput.addErrorLabelListener(tfZip, lbErrZip);
        
        if(isMatriculated)
        {
            // Row 6: Current Date
            gpAdmission.add(new Label("Today's date:"), 0, 6);
            gpAdmission.add(dpToday, 1, 6);

            // Row 7: Year of Matriculation
            for (int i = 0; i < strYear.length; ++i)
                    cbYear.getItems().add(strYear[i]);
            gpAdmission.add(new Label("Year of matriculation:"), 0, 7);
            gpAdmission.add(cbYear, 1, 7);
            gpAdmission.add(lbErrYear, 2, 7, 8, 1);
            ValidateInput.addErrorLabelListener(cbYear, lbErrYear);

            // Row 8: Degree
            for (int i = 0; i < strDegree.length; ++i)
                    cbDegree.getItems().add(strDegree[i]);
            gpAdmission.add(new Label("Degree:"), 0, 8);
            gpAdmission.add(cbDegree, 1, 8);
            gpAdmission.add(lbErrDegree, 2, 8, 8, 1);
            ValidateInput.addErrorLabelListener(cbDegree, lbErrDegree);

            // Row 9: Prerequisites High school diploma
            // High school diploma
            gpAdmission.add(chbHS, 0, 9);
            gpAdmission.add(lbErrHS, 0, 10);
            ValidateInput.addErrorLabelListener(chbHS, lbErrHS);
            
            btnSubmit.setOnAction(e ->
            {
                String msg = "";
                msg += ValidateInput.validateTextField(tfSSN, lbErrSSN, "Please enter SSN", "\\d{9}", "Please enter 9 digits"); // Validate SSN
                msg += ValidateInput.validateTextField(tfFirstName, lbErrName, "Please enter full name"); // Validate FirstName
                msg += ValidateInput.validateTextField(tfLastName, lbErrName, "Please enter full name"); // Validate LastName
                msg += ValidateInput.validateTextField(tfStreet, lbErrStreet, "Please enter street address", "\\d+\\s[A-z\\s]+", "Please enter a valid street address"); // Validate Street address
                msg += ValidateInput.validateTextField(tfCity, lbErrCity, "Please enter city"); // Validate City
                msg += ValidateInput.validateComboBox(cbState, lbErrState, "Please select a state"); // Validate State
                msg += ValidateInput.validateTextField(tfZip, lbErrZip, "Please enter ZIP code", "\\d{5}", "Please enter 5 digits"); // Validate ZIP
                msg += ValidateInput.validateComboBox(cbYear, lbErrYear, "Please select year of matriculation"); // Validate Year
                msg += ValidateInput.validateComboBox(cbDegree, lbErrDegree, "Please select your degree"); // Validate Degree
                
                // If error message is empty (Valid input field), Write the data to mySQL database
                if(msg.length() == 0 || msg.isEmpty())
                {
                    try
                    {
                        StudentDBO.insertStudent(tfSSN.getText(), tfFirstName.getText(), tfMidName.getText(), tfLastName.getText(),
                                                 tfStreet.getText(), tfCity.getText(), cbState.getValue().toString().substring(0,2),
                                                 tfZip.getText(), cbYear.getValue().toString(), cbDegree.getValue().toString().substring(0,2),
                                                 (chbHS.isSelected() ? "y" : "n"), (chbIMM.isSelected() ? "y" : "n"));
                        CustomAlert.displayInfoMsg("Information Submitted", "Enrolled Successful!");
                        clearAllField();
                    }
                    catch(SQLException ex)
                    {
                        CustomAlert.displayErrorMsg(ex, "Database Connection error", "Can not submit student to database");
                    }
                    catch(ClassNotFoundException except)
                    {
                        CustomAlert.displayErrorMsg(except, "Database Connection error", "Can not submit student to database");
                    }
                }
            });
        }
        else
        {
            btnSubmit.setOnAction(e ->
            {
                String msg = "";
                msg += ValidateInput.validateTextField(tfSSN, lbErrSSN, "Please enter SSN", "\\d{9}", "Please enter 9 digits"); // Validate SSN
                msg += ValidateInput.validateTextField(tfFirstName, lbErrName, "Please enter full name"); // Validate FirstName
                msg += ValidateInput.validateTextField(tfLastName, lbErrName, "Please enter full name"); // Validate LastName
                msg += ValidateInput.validateTextField(tfStreet, lbErrStreet, "Please enter street address", "\\d+\\s[A-z\\s]+", "Please enter a valid street address"); // Validate Street address
                msg += ValidateInput.validateTextField(tfCity, lbErrCity, "Please enter city"); // Validate City
                msg += ValidateInput.validateComboBox(cbState, lbErrState, "Please select a state"); // Validate State
                msg += ValidateInput.validateTextField(tfZip, lbErrZip, "Please enter ZIP code", "\\d{5}", "Please enter 5 digits"); // Validate ZIP
                
                // If error message is empty (Valid input field), Write the data to mySQL database
                if(msg.length() == 0 || msg.isEmpty())
                {
                    try
                    {
                        StudentDBO.insertStudent(tfSSN.getText(), tfFirstName.getText(), tfMidName.getText(), tfLastName.getText(),
                                                 tfStreet.getText(), tfCity.getText(), cbState.getValue().toString().substring(0,2),
                                                 tfZip.getText(), "NM", "", "", (chbIMM.isSelected() ? "y" : "n"));
                        CustomAlert.displayInfoMsg("Information Submitted", "Enrolled Successful!");
                        clearAllField();
                    }
                    catch(SQLException ex)
                    {
                        CustomAlert.displayErrorMsg(ex, "Database Connection error", "Can not submit student to database");
                    }
                    catch(ClassNotFoundException except)
                    {
                        CustomAlert.displayErrorMsg(except, "Database Connection error", "Can not submit student to database");
                    }
                }
            });
        }
        
        // Row 9: Prerequisites Immunization Record
        // Immunization Record
        gpAdmission.add(chbIMM, 1, 9);
        gpAdmission.add(lbErrIMM, 1, 10);
        ValidateInput.addErrorLabelListener(chbIMM, lbErrIMM);
        
        gpAdmission.setHgap(15);
        gpAdmission.setVgap(10);
        gpAdmission.setPadding(new Insets(10, 10, 10, 10));
        
        setPadding(new Insets(0, 20, 40, 20));
        setCenter(gpAdmission);
        setBottom(btnSubmit);
    }
    
    /**
     * A method to clear all input field
     */
    public void clearAllField()
    {
        tfSSN.clear(); 
        tfFirstName.clear(); 
        tfMidName.clear(); 
        tfLastName.clear();
        tfStreet.clear(); 
        tfCity.clear(); 
        cbState.setValue(null); 
        tfZip.clear();
        cbYear.setValue(null); 
        cbDegree.setValue(null); 
        dpToday.setValue(null); 
        dpToday.getEditor().clear();
        chbHS.setSelected(false); 
        chbIMM.setSelected(false);
    }
}
