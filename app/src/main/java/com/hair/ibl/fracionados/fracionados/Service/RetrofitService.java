package com.hair.ibl.fracionados.fracionados.Service;

import com.hair.ibl.fracionados.fracionados.Model.Content.List.ContentList;
import com.hair.ibl.fracionados.fracionados.Model.Content.Show.ContentShow;

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

    @GET("contents/{slug}?format=json")
    Call<ContentShow> getContent(@Path("slug") String slug);
}