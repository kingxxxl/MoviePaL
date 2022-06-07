package com.example.moviepal.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Movie {
    @Id
    @JsonProperty("imdbID")
    private String id;
    private String title;
    private String year;
}
