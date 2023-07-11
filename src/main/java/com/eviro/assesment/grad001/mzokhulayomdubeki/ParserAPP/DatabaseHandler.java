package com.eviro.assesment.grad001.mzokhulayomdubeki.ParserAPP;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a database handler entity.
 */
@Entity
public class DatabaseHandler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String httpImageLink;

    /**
     * Get the database handler ID.
     *
     * @return The database handler ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the database handler ID.
     *
     * @param id The database handler ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the database handler name.
     *
     * @return The database handler name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the database handler name.
     *
     * @param name The database handler name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the database handler surname.
     *
     * @return The database handler surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the database handler surname.
     *
     * @param surname The database handler surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get the HTTP image link.
     *
     * @return The HTTP image link.
     */
    public String getHttpImageLink() {
        return httpImageLink;
    }

    /**
     * Set the HTTP image link.
     *
     * @param httpImageLink The HTTP image link.
     */
    public void setHttpImageLink(String httpImageLink) {
        this.httpImageLink = httpImageLink;
    }
}
