/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin;

import boolaadmin.layout.AdmissionLayout;
import boolaadmin.layout.ReceivableLayout;
import boolaadmin.layout.RegistrationLayout;
import boolaadmin.layout.RootLayout;
import boolaadmin.layout.ScheduleLayout;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Suncharn Pipithkul
 */
public class BoolaAdmin extends Application
{
    private Stage primaryStage;
    public static final String condition1 = "Credits = 3";
    public static final String condition2 = "Credits = 0";
    
    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        
        RootLayout root = new RootLayout();
        AdmissionLayout matriculateLayout = new AdmissionLayout(true, "Matriculated Admission");
        AdmissionLayout nonMatriculateLayout = new AdmissionLayout(false, "Non-Matriculated Admission");
        RegistrationLayout fullTimeLayout = new RegistrationLayout("Full-Time Registration", condition1, true);
        RegistrationLayout partTimeLayout = new RegistrationLayout("Part-Time Registration", condition1, false);
        RegistrationLayout nonCreditLayout = new RegistrationLayout("Non-Credit Registration", condition2);
        ReceivableLayout receivableLayout = new ReceivableLayout("Receivable", condition1, condition2);
        ScheduleLayout scheduleLayout = new ScheduleLayout("Class Schedule");
        
        
        root.miMatriculate.setOnAction(e-> { root.setCenter(matriculateLayout);});
        root.miNonMatriculate.setOnAction(e-> { root.setCenter(nonMatriculateLayout);});
        root.miExit.setOnAction(e -> { handleCloseEvent();});
        root.miFullTime.setOnAction(e-> {root.setCenter(fullTimeLayout);});
        root.miPartTime.setOnAction(e-> {root.setCenter(partTimeLayout);});
        root.miNonCredit.setOnAction(e-> {root.setCenter(nonCreditLayout);});
        root.miReceivable.setOnAction(e-> {root.setCenter(receivableLayout);});
        root.miSchedule.setOnAction(e-> {root.setCenter(scheduleLayout);});
        
        Scene scene = new Scene(root, 1000, 500);
        
        primaryStage.setTitle("Boola Boola University Administrative Program");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e ->
        {
            e.consume(); // Consume the WINDOW_CLOSE_REQUEST event
            handleCloseEvent();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
    /**
     * A method that display an Alert asking user to confirm exiting request
     */
    public void handleCloseEvent()
    {
        // Alert for exiting the program
        Alert alClosing = new Alert(Alert.AlertType.CONFIRMATION);
        alClosing.setTitle("Are you sure?");
        alClosing.setHeaderText(null);
        alClosing.setContentText("Do you want to close the program?");
        
        Optional<ButtonType> result = alClosing.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
            primaryStage.close();
        else
            alClosing.close();
    }
    
}
