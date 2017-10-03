package com.hair.ibl.fracionados.fracionados.Model.Blog.List;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * BlogData
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class BlogData {
    @SerializedName("posts")
    private ArrayList<Post> posts = new ArrayList<>();

    /**
     * @return The posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * @param posts The content_item
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
