package com.hair.ibl.fracionados.fracionados.Model.Blog.Show;

import com.google.gson.annotations.SerializedName;
import com.hair.ibl.fracionados.fracionados.Model.Blog.List.Post;

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
     * @param posts The posts
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
