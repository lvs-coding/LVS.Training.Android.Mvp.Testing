package com.example.mvptesting;


import retrofit2.http.GET;
import rx.Observable;

public interface CharactersDataSource {
    @GET("people/")
    Observable<CharactersResponseModel> getCharacters();
}
