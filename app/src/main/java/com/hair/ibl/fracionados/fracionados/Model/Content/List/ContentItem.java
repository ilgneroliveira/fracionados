package com.hair.ibl.fracionados.fracionados.Model.Content.List;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * ContentItem
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ContentItem {
    private String title;
    private String content;
    private String slug;

    public ContentItem() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
