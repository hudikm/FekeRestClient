/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fakerestclient;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author hudik1
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //Create retrofit client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholder service = retrofit.create(JSONPlaceholder.class);
        
        //Get all users
        Call<List<User>> users = service.getUsers();
        users.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> rspns) {
                List<User> users = rspns.body();
                for (User user : users) {
                    System.out.println(user.getUsername() + " " + user.getEmail());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable thrwbl) {
                Logger.getLogger("").log(Level.SEVERE, "Chyba", thrwbl);
            }
        });

        //Get user by ID
        Call<User> user = service.getUser(5);
        Response<User> execute = user.execute();
        if (execute.isSuccessful()) {
            User body = execute.body();
            System.out.println("User by id " + body.getUsername() + " Email:" + body.getEmail());

        } else {
            ResponseBody errorBody = execute.errorBody();

            System.out.println(execute.code());
        }
        //Send new post on server
        Post newPostVar = new Post();
        newPostVar.setTitle("Skuska");
        newPostVar.setUserId(2);
        newPostVar.setBody("Sprava post");
        Call<Post> newPost = service.newPost(newPostVar);
        Response<Post> execNewPost = newPost.execute();
        if (execNewPost.isSuccessful()) {
            Post post2 = execNewPost.body();
            System.out.println("Title " + post2.getTitle() + " Body:" + post2.getBody() + "Id:" + post2.getId());

        } else {
            ResponseBody errorBody = execNewPost.errorBody();
            System.out.println(execute.code());
        }

        //Get all posts
        Call<List<Post>> posts = service.getPosts();
        Response<List<Post>> executePost = posts.execute();
        if (executePost.isSuccessful()) {
            List<Post> body = executePost.body();
            for (Post post : body) {
                System.out.println("All posts " + post.getTitle() + " Email:" + post.getTitle());
            }

        } else {
            ResponseBody errorBody = execute.errorBody();

            System.out.println(execute.code());
        }

        // Get Posts By userId
        Call<List<Post>> postsByUser = service.getPostsByUserId(1);
        Response<List<Post>> executePostByUser = postsByUser.execute();
        if (executePost.isSuccessful()) {
            List<Post> body = executePostByUser.body();
            for (Post post : body) {
                System.out.println("PostByUser " + post.getTitle() + " Email:" + post.getTitle());
            }

        } else {
            ResponseBody errorBody = execute.errorBody();

            System.out.println(execute.code());
        }

    }

}
