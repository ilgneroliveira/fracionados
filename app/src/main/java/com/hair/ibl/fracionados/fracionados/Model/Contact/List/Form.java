package com.hair.ibl.fracionados.fracionados.Model.Contact.List;

/**
 * Post
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class Form {
    private String title;
    private String slug;

    public Form() {
    }

    public Form(String[] form) {
        title = form[0].replaceAll("<.*?>","");
        slug = form[1].replaceAll("<.*?>","").trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
