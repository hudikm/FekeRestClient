/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fakerestclient;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * @author hudik1
 */
public interface JSONPlaceholder {

    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/users/{id}")
    Call<User> getUser(@Path("id") Integer id);

    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("/posts/{id}")
    Call<Post> getPost(@Path("id") Integer id);

    @GET("/posts")
    Call<List<Post>> getPostsByUserId(@Query("userId") Integer userId);
    
    @POST("/posts")
    Call<Post> newPost(@Body Post post);

}
