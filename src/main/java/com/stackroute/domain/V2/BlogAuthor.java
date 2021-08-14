package com.stackroute.domain.V2;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Add Annotation to declare this class as JPA Entity
 */
@Entity
public class BlogAuthor {

    /**
     * Add annotation to specify and autogenerate the id
     */
    @Id
    private int id;
    private String firstName;
    private String lastName;

    /**
     * default constructor
     */
    public BlogAuthor() {
    }

    /**
     * parametrized constructor
     */
    public BlogAuthor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * getters and setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
