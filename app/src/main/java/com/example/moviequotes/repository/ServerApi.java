package com.example.moviequotes.repository;

import com.example.moviequotes.repository.entity.QuoteDetailsResponse;
import com.example.moviequotes.repository.entity.QuoteList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerApi {

//    пример запросов
//    http://ds24.ru/test/list.php?offset=1&limit=10
//    http://ds24.ru/test/detail.php?id=2

    @GET("list.php")
    Observable<QuoteList> messages(@Query("offset") int offset, @Query("limit") int limit);

    @GET("detail.php")
    Observable<QuoteDetailsResponse> messageDetails(@Query("id") int id);
}
