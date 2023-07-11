package com.eviro.assesment.grad001.mzokhulayomdubeki.ParserAPP;

import jakarta.persistence.*;

/**
 * Represents an account profile entity.
 */
@Entity
@Table(name = "account_profiles")
public class AccountProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String httpImageLink;
    @Transient
    private String base64ImageData;


    /**
     * Get the base64-encoded image data.
     *
     * @return The base64-encoded image data.
     */
    public String getBase64ImageData() {
        return base64ImageData;
    }

    /**
     * Set the base64-encoded image data.
     *
     * @param base64ImageData The base64-encoded image data.
     */
    public void setBase64ImageData(String base64ImageData) {
        this.base64ImageData = base64ImageData;
    }

    /**
     * Get the account profile ID.
     *
     * @return The account profile ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the account profile ID.
     *
     * @param id The account profile ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the account profile name.
     *
     * @return The account profile name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the account profile name.
     *
     * @param name The account profile name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the account profile surname.
     *
     * @return The account profile surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the account profile surname.
     *
     * @param surname The account profile surname.
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
