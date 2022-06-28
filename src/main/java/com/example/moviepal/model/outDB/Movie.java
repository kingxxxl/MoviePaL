package com.example.moviepal.model.outDB;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
//@Entity
public class Movie implements Serializable {

    private static final long serialVersionUID = -4439114469417994310L;

    @Id
    @JsonProperty("imdbID")
    private String id;
    private String title;
    private String year;
    private String type;
    private String poster;
    private String runtime;
    @JsonProperty("imdbRating")
    private String imdbRating;
}
