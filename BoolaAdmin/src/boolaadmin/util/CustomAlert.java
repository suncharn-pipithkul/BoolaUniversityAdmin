/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.util;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

/**
 *
 * @author Suncharn Pipithkul
 */
public abstract class CustomAlert
{
    private static Alert alInformation = new Alert(Alert.AlertType.INFORMATION);
    private static Alert alError = new Alert(Alert.AlertType.ERROR);
    
    /**
     * A method that display caught Exception message
     * @param ex
     * @param title
     * @param contentText 
     */
    public static void displayErrorMsg(Exception ex, String title, String contentText)
    {
        TextArea taAlert = new TextArea(ex.getMessage());
        taAlert.setWrapText(true);
        alError.setTitle(title);
        alError.setHeaderText(null);
        alError.setContentText(contentText);
        alError.getDialogPane().setExpandableContent(taAlert);
        alError.showAndWait();
    }
    
    /**
     * A method that display error message
     * @param title
     * @param contentText 
     */
    public static void displayErrorMsg(String title, String contentText)
    {
        alError.setTitle(title);
        alError.setHeaderText(null);
        alError.setContentText(contentText);
        alError.showAndWait();
    }
    
    /**
     * A method that display success message
     * @param title
     * @param contentText 
     */
    public static void displayInfoMsg(String title, String contentText)
    {
        alInformation.setTitle(title);
        alInformation.setHeaderText(null);
        alInformation.setContentText(contentText);
        alInformation.showAndWait();
    }
}
