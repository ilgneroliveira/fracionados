package com.hair.ibl.fracionados.fracionados.Model.Contact.Show;

import java.util.ArrayList;

/**
 * Post
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class Form {
    private String title;
    private String description;
    private String success_message;
    private String failure_message;
    private ArrayList<Element> elements;

    public Form() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuccess_message() {
        return success_message;
    }

    public void setSuccess_message(String success_message) {
        this.success_message = success_message;
    }

    public String getFailure_message() {
        return failure_message;
    }

    public void setFailure_message(String failure_message) {
        this.failure_message = failure_message;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }
}
