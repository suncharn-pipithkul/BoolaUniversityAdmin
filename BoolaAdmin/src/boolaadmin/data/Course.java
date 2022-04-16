/*
 * 
 */
package boolaadmin.data;

import java.sql.Time;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Suncharn Pipithkul
 */
public class Course
{
    /**
     * Member variables
     */
    private SimpleStringProperty courseId;
    private SimpleStringProperty name;
    private SimpleStringProperty day;
    private SimpleObjectProperty<Time> startTime;
    private SimpleObjectProperty<Time> endTime;
    private SimpleStringProperty room;
    private SimpleIntegerProperty credits;
    
    /**
     * Default constructor
     */
    public Course()
    {
        this.courseId = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.day = new SimpleStringProperty();
        this.startTime = new SimpleObjectProperty<Time>();
        this.endTime = new SimpleObjectProperty<Time>();
        this.room = new SimpleStringProperty();
        this.credits = new SimpleIntegerProperty();
    }
    
    /**
     * Constructor with arguments
     * @param courseId
     * @param name
     * @param day
     * @param start
     * @param end
     * @param room
     * @param credits 
     */
    public Course(String courseId, String name, String day, Time start, Time end, String room, int credits)
    {
        this.courseId = new SimpleStringProperty(courseId);
        this.name = new SimpleStringProperty(name);
        this.day = new SimpleStringProperty(day);
        this.startTime = new SimpleObjectProperty<Time>(start);
        this.endTime = new SimpleObjectProperty<Time>(end);
        this.room = new SimpleStringProperty(room);
        this.credits = new SimpleIntegerProperty(credits);
    }
    
    /**
     * Setter for courseId
     * @param id 
     */
    public void setCourseId(String id)
    { this.courseId.set(id); }
    
    /**
     * Getter for courseId
     * @return String course ID
     */
    public String getCourseId()
    { return this.courseId.get(); }
    
    /**
     * Setter for course name
     * @param name 
     */
    public void setName(String name)
    { this.name.set(name); }
    
    /**
     * Getter for course name
     * @return String course name
     */
    public String getName()
    { return this.name.get(); }
    
    /**
     * Setter for class day
     * @param day 
     */
    public void setDay(String day)
    { this.day.set(day); }
    
    /**
     * Getter for class day
     * @return String day
     */
    public String getDay()
    { return this.day.get(); }
    
    /**
     * Setter for course start time
     * @param t 
     */
    public void setStartTime(Time t)
    { this.startTime.set(t); }
    
    /**
     * Getter for course start time
     * @return Time start time
     */
    public Time getStartTime()
    { return this.startTime.get(); }
    
    /**
     * Setter for course end time
     * @param t 
     */
    public void setEndTime(Time t)
    { this.endTime.set(t); }
    
    /**
     * Getter for course end time
     * @return Time end time
     */
    public Time getEndTime()
    { return this.endTime.get(); }
    
    /**
     * Setter for meeting room
     * @param room 
     */
    public void setRoom(String room)
    { this.room.set(room); }
    
    /**
     * Getter for meeting room
     * @return String meeting room
     */
    public String getRoom()
    { return this.room.get(); }
    
    /**
     * Setter number of credits
     * @param credits 
     */
    public void setCredits(int credits)
    { this.credits.set(credits); }
    
    /**
     * Getter number of credits
     * @return integer number of credits
     */
    public int getCredits()
    { return this.credits.get(); }
}
