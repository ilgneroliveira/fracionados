package com.hair.ibl.fracionados.fracionados.Model.Blog.Show;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * BlogData
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class BlogData {
    @SerializedName("post")
    private Post post = new Post();

    /**
     * @return The posts
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post The post
     */
    public void setPost(Post post) {
        this.post = post;
    }
}
