package com.hair.ibl.fracionados.fracionados.Model.Content.Show;

import com.google.gson.annotations.SerializedName;
import com.hair.ibl.fracionados.fracionados.Model.Content.List.ContentItem;

import java.util.ArrayList;

/**
 * ContentData
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ContentData {
    @SerializedName("content")
    private Content content = new Content();

    /**
     * @return The content
     */
    public Content getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    public void setContent_item(Content content) {
        this.content = content;
    }
}
