/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.util;

import boolaadmin.data.CourseWithCheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author Suncharn Pipithkul
 */
public abstract class ValidateInput
{
    /**
     * A method that creates a label for error notification.
     * @return the Label
     */
    public static Label createErrorLabel()
    {
        Label lbError = new Label();
        lbError.setTextFill(Color.RED);
        lbError.setVisible(false);
        return lbError;
    }
    
    /**
     * A method that validates a TextField object.
     * If the field is empty, the error label for empty field appears 
     * and the TextField's border become red. It returns error message + new line.
     * Else the error label disappears, TextField style is reset, then returns empty string literal.
     * @param tf
     * @param lbError
     * @param errMsg
     * @return error message + new line or empty string
     */
    public static String validateTextField(TextField tf, Label lbError, String errMsg)
    {
        // Empty TextField
        if (tf.getText().length() == 0 || tf.getText().isEmpty())
        {
            tf.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
            lbError.setVisible(true);
            lbError.setText(errMsg);
            return errMsg + "\n";
        }
        else
            return "";
    }
    
    /**
     * A method that validates a TextField object using regular expression.
     * If the field is empty, the error label for empty field appears 
     * and the TextField's border become red. It returns error message + new line.
     * Else if the field text does not match the regular expression, the error label for invalid input appears 
     * and the TextField's border become red. It returns error message + new line.
     * Else the error label disappears, TextField style is reset, then returns empty string literal.
     * @param tf
     * @param lbError
     * @param errMsg1
     * @param regexErr
     * @param errMsg2
     * @return error message + new line or empty string
     */
    public static String validateTextField(TextField tf, Label lbError, String errMsg1, String regexErr, String errMsg2)
    {
        // Empty TextField
        if (tf.getText().length() == 0 || tf.getText().isEmpty())
        {
            tf.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
            lbError.setVisible(true);
            lbError.setText(errMsg1);
            return errMsg1 + "\n";
        }
        // TextField input does not have the appropriate formatted
        else if (!tf.getText().matches(regexErr))
        {
            tf.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
            lbError.setVisible(true);
            lbError.setText(errMsg2);
            return errMsg2 + "\n";
        }
        else
            return "";
    }
    
    /**
     * A method that validates a ComboBox object.
     * If the box is not selected, the error label appears 
     * and the ComboBox's border become red. It returns error message + new line.
     * Else the error label disappears, ComboBox style is reset, then returns empty string literal.
     * @param cb
     * @param lbError
     * @param errMsg
     * @return error message + new line or empty string
     */
    public static String validateComboBox(ComboBox cb, Label lbError, String errMsg)
    {
        if(cb.getValue() == null)
        {
            cb.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
            lbError.setVisible(true);
            lbError.setText(errMsg); 
            return errMsg + "\n";
        }
        else
        {
            cb.setStyle(null);
            lbError.setVisible(false);
            return "";
        }
    }
    
    /**
     * A method that validates a CheckBox object.
     * If the box is unchecked, the error label appears 
     * and the CheckBox's border become red. It returns error message + new line.
     * Else the error label disappears, CheckBox style is reset, then returns empty string literal.
     * @param chb
     * @param lbError
     * @param errMsg
     * @return error message + new line or empty string
     */
    public static String validateCheckBox(CheckBox chb, Label lbError, String errMsg)
    {
        if(!chb.isSelected())
        {
            chb.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
            lbError.setVisible(true);
            lbError.setText(errMsg);
            return errMsg + "\n";
        }
        else
        {
            chb.setStyle(null);
            lbError.setVisible(false);
            return "";
        }
    }
    
    /**
     * A method to validate if a time elapse in course selected
     * @param selectedList
     * @return 
     */
    public static String validateTime(ObservableList<CourseWithCheckBox> selectedList)
    {
       String msg = "";
       for(int x = 0; x < selectedList.size() - 1; ++x)
       {
          for(int y = x + 1; y < selectedList.size(); ++y)
          {
              if(selectedList.get(x).getDay().equals(selectedList.get(y).getDay())) // If it's the same day
              {
                  if((selectedList.get(x).getStartTime().after(selectedList.get(y).getStartTime())
                      && selectedList.get(x).getStartTime().before(selectedList.get(y).getEndTime()))
                          || (selectedList.get(x).getEndTime().before(selectedList.get(y).getEndTime()) 
                              && selectedList.get(x).getEndTime().after(selectedList.get(y).getStartTime())))
                      msg += selectedList.get(x).getName() + " is elapsed with " + selectedList.get(y).getName() + "\n";
              }
          }
       }
       return msg;
    }
    
    /**
     * A method that add ChangeListener to a control object.
     * The ChangeListener listens for a change in value of the control object,
     * then reset style of the control object and reset Error Label associates
     * to that control object.
     * @param control
     * @param lbError 
     */
    public static void addErrorLabelListener(Control control, Label lbError)
    {
        // Remove error message when user click
        control.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                                Boolean oldValue, Boolean newValue)
            {
                if (newValue)
                {
                    control.setStyle(null);
                    lbError.setText("");
                    lbError.setVisible(false);
                }
            }
        });
    }
}
