package com.hair.ibl.fracionados.fracionados.Model.Contact.Show;

import com.google.gson.annotations.SerializedName;

/**
 * PostShow
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ContentShow {
    @SerializedName("data")
    private ContentData data = new ContentData();

    /**
     * @return The data
     */
    public ContentData getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(ContentData data) {
        this.data = data;
    }
}
