package com.hair.ibl.fracionados.fracionados.Model.Blog.List;

import com.google.gson.annotations.SerializedName;

/**
 * BlogList
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class BlogList {
    @SerializedName("data")
    private BlogData data = new BlogData();

    /**
     * @return The data
     */
    public BlogData getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(BlogData data) {
        this.data = data;
    }
}
