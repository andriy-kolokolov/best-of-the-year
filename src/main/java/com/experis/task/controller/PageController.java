package com.experis.task.controller;

import com.experis.task.model.Movie;
import com.experis.task.model.Song;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @Value("${author}")
    private String author;

    @GetMapping("/")
    public String index(Model model) {
        // Add attributes to the model to be read by Thymeleaf
        model.addAttribute("author", author);


        // This will return the 'index' view
        return "home";
    }

    @GetMapping("/movies")
    public String getMovies(Model model) {
        List<Movie> movies = getBestMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/songs")
    public String getSongs(Model model) {
        List<Song> songs = getBestSongs();
        model.addAttribute("songs", songs);
        return "songs";
    }

    @GetMapping("/movies/{id}")
    public String getMovie(@PathVariable Long id, Model model) {
        Movie movie = findMovieById(id);
        model.addAttribute("movie", movie);
        return "movieDetail";
    }

    @GetMapping("/songs/{id}")
    public String getSong(@PathVariable Long id, Model model) {
        Song song = findSongById(id);
        model.addAttribute("song", song);
        return "songDetail";
    }

    private List<Movie> getBestMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1L, "The Shawshank Redemption"));
        movies.add(new Movie(2L, "The Godfather"));
        movies.add(new Movie(3L, "The Dark Knight"));
        movies.add(new Movie(4L, "12 Angry Men"));
        // Add other movies with their details
        return movies;
    }

    private List<Song> getBestSongs() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song(1L, "Bohemian Rhapsody - Queen"));
        songs.add(new Song(2L, "Imagine - John Lennon"));
        songs.add(new Song(3L, "One - U2"));
        songs.add(new Song(4L, "Billie Jean - Michael Jackson"));
        // Add other songs with their details
        return songs;
    }

    private Movie findMovieById(Long id) {
        return getBestMovies().stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElse(null); // Return null or throw an exception if not found
    }

    private Song findSongById(Long id) {
        return getBestSongs().stream()
                .filter(song -> song.getId() == id)
                .findFirst()
                .orElse(null); // Return null or throw an exception if not found
    }
}
