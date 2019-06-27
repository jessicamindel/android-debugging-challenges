package com.codepath.debuggingchallenges.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codepath.debuggingchallenges.R;
import com.codepath.debuggingchallenges.adapters.MoviesAdapter;
import com.codepath.debuggingchallenges.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    private static final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";

    RecyclerView rvMovies;
    MoviesAdapter adapter;
    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        rvMovies = findViewById(R.id.rvMovies);
        // FIXED: Instantiate movies, no pointer for adapter to use otherwise
        movies = new ArrayList<>();

        // Create the adapter to convert the array to views
        // FIXED: Previously declared new variable, overrode access to object's adapter member
        adapter = new MoviesAdapter(movies);

        // Attach the adapter to a ListView
        rvMovies.setAdapter(adapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        fetchMovies();
        // No movies added yet because asynchronous
        Log.i("MoviesActivity", "Has " + movies.size() + " movies");
    }


    private void fetchMovies() {
        // FIXED: Use API key to authenticate, previously incomplete querystring
        String url = " https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray moviesJson = response.getJSONArray("results");
                    // FIXED: Use the same object previously pointed to instead of creating a new one
                    movies.clear();
                    movies.addAll(Movie.fromJSONArray(moviesJson));
                    // FIXED: Update the dataset to re-render the view
                    Log.i("MoviesActivity", "Added " + movies.size() + " movies");
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // FIXED: Added onFailure method for debugging purposes
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("MoviesActivity", statusCode + " fetch failed");
                throwable.printStackTrace();
            }
        });
    }
}
