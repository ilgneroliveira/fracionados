package com.hair.ibl.fracionados.fracionados.Model.Contact.Show;

import com.google.gson.annotations.SerializedName;
import com.hair.ibl.fracionados.fracionados.Model.Contact.List.Form;

/**
 * BlogData
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ContentData {
    @SerializedName("content")
    private Form content = new Form();

    /**
     * @return The content
     */
    public Form getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    public void setContent_item(Form content) {
        this.content = content;
    }
}
