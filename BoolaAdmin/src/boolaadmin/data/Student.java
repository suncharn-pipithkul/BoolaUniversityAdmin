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
public class Student
{
    /**
     * Member Variables
     */
    public SimpleStringProperty ssn;
    public SimpleStringProperty firstName;
    public SimpleStringProperty midName;
    public SimpleStringProperty lastName;
    public SimpleStringProperty street;
    public SimpleStringProperty city;
    public SimpleStringProperty state;
    public SimpleStringProperty zip;
    public SimpleStringProperty year;
    public SimpleStringProperty degree;
    public SimpleStringProperty hs;
    public SimpleStringProperty imm;

    /**
     * Default constructor
     */
    public Student()
    {
        this.ssn = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.midName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.street = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.state = new SimpleStringProperty();
        this.zip = new SimpleStringProperty();
        this.year = new SimpleStringProperty();
        this.degree = new SimpleStringProperty();
        this.hs = new SimpleStringProperty();
        this.imm = new SimpleStringProperty();
    }
    
    /**
     * Constructor with all informations
     * @param ssn
     * @param first
     * @param mid
     * @param last
     * @param st
     * @param city
     * @param state
     * @param zip
     * @param year
     * @param degree
     * @param hs
     * @param imm 
     */
    public Student(String ssn, String first, String mid, String last, String st, 
                  String city, String state, String zip, String year, String degree,
                  String hs, String imm)
    {
        this.ssn = new SimpleStringProperty(ssn);
        this.firstName = new SimpleStringProperty(first);
        this.midName = new SimpleStringProperty(mid);
        this.lastName = new SimpleStringProperty(last);
        this.street = new SimpleStringProperty(st);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.zip = new SimpleStringProperty(zip);
        this.year = new SimpleStringProperty(year);
        this.degree = new SimpleStringProperty(degree);
        this.hs = new SimpleStringProperty(hs);
        this.imm = new SimpleStringProperty(imm);
    }
    
    /**
     * Constructor without year, degree, high school
     * @param ssn
     * @param first
     * @param mid
     * @param last
     * @param st
     * @param city
     * @param state
     * @param zip
     * @param imm 
     */
    public Student(String ssn, String first, String mid, String last, String st, 
                  String city, String state, String zip, String imm)
    {
        this.ssn = new SimpleStringProperty(ssn);
        this.firstName = new SimpleStringProperty(first);
        this.midName = new SimpleStringProperty(mid);
        this.lastName = new SimpleStringProperty(last);
        this.street = new SimpleStringProperty(st);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.zip = new SimpleStringProperty(zip);
        this.year = new SimpleStringProperty();
        this.degree = new SimpleStringProperty();
        this.hs = new SimpleStringProperty();
        this.imm = new SimpleStringProperty(imm);
    }

    //===============================================================//
    //=====================     SSN     =============================//
    /**
     * Setter for SSN value
     * @param ssn 
     */
    public void setSsn(String ssn) 
    { this.ssn.set(ssn); }
    
    /**
     * Getter for SSN value
     * @return SSN String
     */
    public String getSsn() 
    { return ssn.get(); }
    
    /**
     * Getter for SSN property
     * @return SSN property
     */
    public SimpleStringProperty ssnProperty() 
    { return ssn; }
    //===============================================================//
    //==============     First Name     =============================//
    /**
     * Setter for first name value
     * @param firstName 
     */
    public void setFirstName(String firstName) 
    { this.firstName.set(firstName); }
    
    /**
     * Getter for first name value
     * @return firstName String
     */
    public String getFirstName() 
    { return firstName.get(); }
    
    /**
     * Getter for first name property
     * @return firstName property
     */
    public SimpleStringProperty firstNameProperty()
    { return firstName; }
    //==============================================================//
    //==============     Mid Name     =============================//
    /**
     * Setter for middle name value
     * @param midName 
     */
    public void setMidName(String midName) 
    { this.midName.set(midName); }
    
    /**
     * Getter for middle name value
     * @return midName String
     */
    public String getMidName() 
    { return midName.get(); }
    
    /**
     * Getter for middle name property
     * @return midName property
     */
    public SimpleStringProperty midNameProperty() 
    { return midName; }
    //==============================================================//
    //==============     Last Name     =============================//
    /**
     * Setter last name value
     * @param lastName 
     */
    public void setLastName(String lastName) 
    { this.lastName.set(lastName); }
    
    /**
     * Getter last name value
     * @return lastName String
     */
    public String getLastName() 
    { return lastName.get(); }
    
    /**
     * Getter for lastName property
     * @return last name property
     */
    public SimpleStringProperty lastNameProperty() 
    { return lastName; }
    //==============================================================//
    //=================     Street     =============================//
    /**
     * Setter for street value
     * @param street 
     */
    public void setStreet(String street) 
    { this.street.set(street); }
    
    /**
     * Getter for street value
     * @return street String
     */
    public String getStreet() 
    { return street.get(); }
    
    /**
     * Getter for street property
     * @return street property
     */
    public SimpleStringProperty streetProperty() 
    { return street; }
    //==============================================================//
    //===================     City     =============================//
    /**
     * Setter for city value
     * @param city 
     */
    public void setCity(String city) 
    { this.city.set(city); }
    
    /**
     * Getter for city value
     * @return city String
     */
    public String getCity() 
    { return city.get(); }
    
    /**
     * Getter for city property
     * @return city property
     */
    public SimpleStringProperty cityProperty() 
    { return city; }
    //===============================================================//
    //===================     State     =============================//
    /**
     * Setter for state value
     * @param state 
     */
    public void setState(String state) 
    { this.state.set(state); }
    
    /**
     * Getter for state value
     * @return state String
     */
    public String getState() 
    { return state.get(); }
    
    /**
     * Getter for state property
     * @return state property
     */
    public SimpleStringProperty stateProperty() 
    { return state; }
    //=============================================================//
    //===================     Zip     =============================//
    /**
     * Setter for zip value
     * @param zip 
     */
    public void setZip(String zip) 
    { this.zip.set(zip); }
    
    /**
     * Getter for zip value
     * @return zip String
     */
    public String getZip() 
    { return zip.get(); }
    
    /**
     * Getter for zip property
     * @return zip property
     */
    public SimpleStringProperty zipProperty() 
    { return zip; }
    //==============================================================//
    //===================     Year     =============================//
    /**
     * Setter for year value
     * @param year 
     */
    public void setYear(String year) 
    { this.year.set(year); }
    
    /**
     * Getter for year value
     * @return year String
     */
    public String getYear() 
    { return year.get(); }
    
    /**
     * Getter for year property
     * @return year property
     */
    public SimpleStringProperty yearProperty() 
    { return year; }
    //================================================================//
    //===================     Degree     =============================//
    /**
     * Setter for degree value
     * @param degree 
     */
    public void setDegree(String degree) 
    { this.degree.set(degree); }
    
    /**
     * Getter for degree value
     * @return degree String
     */
    public String getDegree() 
    { return degree.get(); }
    
    /**
     * Getter for degree property
     * @return degree property
     */
    public SimpleStringProperty degreeProperty() 
    { return degree; }
    //=====================================================================//
    //===================     High School     =============================//
    /**
     * Setter for high school value
     * @param hs 
     */
    public void setHs(String hs) 
    { this.hs.set(hs); }
    
    /**
     * Getter for high school value
     * @return high school String
     */
    public String getHs() 
    { return hs.get(); }
    
    /**
     * Getter for high school property
     * @return high school property
     */
    public SimpleStringProperty hsProperty() 
    { return hs; }
    //======================================================================//
    //===================     Immunization     =============================//
    /**
     * Setter for immunization value
     * @param imm 
     */
    public void setImm(String imm) 
    { this.imm.set(imm); }
    
    /**
     * Getter for immunization value
     * @return immunization String
     */
    public String getImm() 
    { return imm.get(); }
    
    /**
     * Getter for immunization property
     * @return immunization property
     */
    public SimpleStringProperty immProperty() 
    { return imm; }
}
