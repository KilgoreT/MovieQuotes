package com.example.moviequotes.repository;

import com.example.moviequotes.repository.entity.QuoteList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerApi {
//    http://ds24.ru/test/list.php?offset=1&limit=10
//    http://ds24.ru/test/detail.php?id=2
    @GET("list.php")
    Observable<QuoteList> messages(@Query("offset") int offset, @Query("limit") int limit);

    @GET("detail.php")
    Observable<String> messageDetails(@Query("id") int id);
}
