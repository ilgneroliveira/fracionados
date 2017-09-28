package com.hair.ibl.fracionados.fracionados.Model.Content.List;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * ContentData
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ContentData {
    @SerializedName("contents")
    private ArrayList<ContentItem> contents = new ArrayList<>();

    /**
     * @return The content_item
     */
    public ArrayList<ContentItem> getContent_item() {
        return contents;
    }

    /**
     * @param contents The content_item
     */
    public void setContent_item(ArrayList<ContentItem> contents) {
        this.contents = contents;
    }
}
