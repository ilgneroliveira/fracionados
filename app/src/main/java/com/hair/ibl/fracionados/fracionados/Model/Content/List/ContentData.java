package com.hair.ibl.fracionados.fracionados.Model.Content.List;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * BlogData
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ContentData {
    @SerializedName("contents")
    private ArrayList<Content> contents = new ArrayList<>();

    /**
     * @return The content_item
     */
    public ArrayList<Content> getContent_item() {
        return contents;
    }

    /**
     * @param contents The content_item
     */
    public void setContent_item(ArrayList<Content> contents) {
        this.contents = contents;
    }
}
