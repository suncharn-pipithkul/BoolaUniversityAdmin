/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.layout;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Suncharn Pipithkul
 */
public class RootLayout extends BorderPane
{
    public MenuBar menuBar;
    public Menu mAdmission;
    public MenuItem miMatriculate;
    public MenuItem miNonMatriculate;
    public MenuItem miExit;
    
    public Menu mRegistration;
    public MenuItem miFullTime;
    public MenuItem miPartTime;
    public MenuItem miNonCredit;
    
    public Menu mReports;
    public MenuItem miReceivable;
    public MenuItem miSchedule;
    
    public RootLayout()
    {
       // Category 1: Admission
       miMatriculate = new MenuItem("Matriculated");
       miNonMatriculate = new MenuItem("Non-Matriculated");
       miExit = new MenuItem("Exit");
       miMatriculate.setAccelerator(KeyCombination.keyCombination("Ctrl+M"));
       miNonMatriculate.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
       miExit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
       mAdmission = new Menu("Registration", null, miMatriculate, miNonMatriculate, miExit);
       
       // Category 2: Registration
       miFullTime = new MenuItem("Full-Time");
       miPartTime = new MenuItem("Part-Time");
       miNonCredit = new MenuItem("Non-Credit");
       miFullTime.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+F"));
       miPartTime.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
       miNonCredit.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+C"));
       mRegistration = new Menu("Registration", null, miFullTime, miPartTime, miNonCredit);
       
       // Category 3: Reports
       miReceivable = new MenuItem("Receivables");
       miSchedule = new MenuItem("Class Schedule");
       miReceivable.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
       miSchedule.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
       mReports = new Menu("Reports", null, miReceivable, miSchedule);
       
       menuBar = new MenuBar(mAdmission, mRegistration, mReports);
       setTop(menuBar);
    }
}
