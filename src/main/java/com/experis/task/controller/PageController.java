package com.experis.task.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @Value("${author}")
    private String author;

    @GetMapping("/")
    public String index(Model model){
        // Add attributes to the model to be read by Thymeleaf
        model.addAttribute("author", author);
        // This will return the 'index' view
        return "main";
    }

    @GetMapping("/movies")
    public String getMovies(Model model) {
        String movies = String.join(", ", getBestMovies());
        model.addAttribute("movies", movies);
        return "movies"; // assuming there's a movies.html template to render the view
    }

    @GetMapping("/songs")
    public String getSongs(Model model) {
        String songs = String.join(", ", getBestSongs());
        model.addAttribute("songs", songs);
        return "songs"; // assuming there's a songs.html template to render the view
    }

    private List<String> getBestMovies() {
        return List.of("The Shawshank Redemption", "The Godfather", "The Dark Knight", "12 Angry Men");
    }

    private List<String> getBestSongs() {
        return List.of("Bohemian Rhapsody - Queen", "Imagine - John Lennon", "One - U2", "Billie Jean - Michael Jackson");
    }
}
