/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.layout;

import boolaadmin.data.Student;
import boolaadmin.util.CustomAlert;
import boolaadmin.util.StudentDBO;
import boolaadmin.util.ValidateInput;
import java.sql.SQLException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Suncharn Pipithkul
 */
public class SignInLayout extends VBox
{
    private String strSignIn = "Sign in";
    private Label lbSignIn = new Label(strSignIn);
    public Label lbErrSSN;
    public TextField tfSSN = new TextField();
    public Button btnSignIn = new Button(strSignIn);
    
    public SignInLayout()
    {
        // Error Message Label
        lbErrSSN = ValidateInput.createErrorLabel();
        ValidateInput.addErrorLabelListener(tfSSN, lbErrSSN);        
        
        lbSignIn.setStyle("-fx-font-size: 2em; -fx-font-weight: bold;");
        
        HBox hbSSN = new HBox(10, new Label("SSN:"), tfSSN, lbErrSSN);
        hbSSN.setAlignment(Pos.CENTER_LEFT);
        setSpacing(10);
        getChildren().addAll(lbSignIn, hbSSN, btnSignIn);
    }
}
