package com.hair.ibl.fracionados.fracionados.Service;

import com.hair.ibl.fracionados.fracionados.Model.Content.Content;
import com.hair.ibl.fracionados.fracionados.Model.Content.ContentList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * RetrofitService
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public interface RetrofitService {
    @GET("contents?format=json")
    Call<ContentList> getAllContents();

    @GET("contents/{id}?format=json")
    Call<Content> getContent(@Path("id") String id);
}