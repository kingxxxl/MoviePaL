package com.example.moviepal.model.outDB;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Id;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
//@Entity
public class Movie {
    @Id
    @JsonProperty("imdbID")
    private String id;
    private String title;
    private String year;
    private String type;
}
