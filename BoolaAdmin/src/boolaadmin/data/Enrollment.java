/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaadmin.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Suncharn Pipithkul
 */
public class Enrollment
{
    /**
     * Member variables
     */
    private SimpleStringProperty ssn;
    private SimpleStringProperty courseId;
    private SimpleIntegerProperty cost;

    /**
     * Default Constructor
     */
    public Enrollment()
    {
        this.ssn = new SimpleStringProperty();
        this.courseId = new SimpleStringProperty();
        this.cost = new SimpleIntegerProperty();
    }
    
    /**
     * Constructor with arguments
     * @param ssn
     * @param courseId 
     */
    public Enrollment(String ssn, String courseId, int cost)
    {
        this.ssn = new SimpleStringProperty(ssn);
        this.courseId = new SimpleStringProperty(courseId);
        this.cost = new SimpleIntegerProperty(cost);

    }

    /**
     * Getter for SSN String
     * @return SSN String
     */
    public String getSsn()
    {
        return ssn.get();
    }
    
    /**
     * Getter for SSN property
     * @return SSN property
     */
    public SimpleStringProperty ssnProperty()
    {
        return ssn;
    }
    
    /**
     * Setter for SSN String
     * @param ssn 
     */
    public void setSsn(String ssn)
    {
        this.ssn.set(ssn);
    }

    /**
     * Getter for courseId String
     * @return courseId String
     */
    public String getCourseId()
    {
        return courseId.get();
    }
    
    /**
     * Getter for courseId property
     * @return courseId property
     */
    public SimpleStringProperty courseIdProperty()
    {
        return courseId;
    }

    /**
     * Setter for courseId string
     * @param courseId 
     */
    public void setCourseId(String courseId)
    {
        this.courseId.set(courseId);
    }

    /**
     * Getter for cost integer value
     * @return cost value
     */
    public int getCost()
    {
        return cost.get();
    }
    
    /**
     * Getter for cost property
     * @return cost property
     */
    public SimpleIntegerProperty costProperty()
    {
        return cost;
    }

    /**
     * Setter for cost integer value
     * @param cost 
     */
    public void setCost(int cost)
    {
        this.cost.set(cost);
    }
}
