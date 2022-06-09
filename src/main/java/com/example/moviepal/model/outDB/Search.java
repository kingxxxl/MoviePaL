package com.example.moviepal.model.outDB;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Search {
    private List<Search> search;
    private String title;
    private String year;
    private String type;
    @JsonProperty("imdbID")
    private String id;
}
