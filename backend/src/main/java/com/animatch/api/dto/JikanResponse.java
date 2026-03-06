package com.animatch.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class JikanResponse {
    private List<AnimeData> data;

    @Data
    public static class AnimeData {
        private String title;
        private String synopsis;
        private Double score;
        private Integer rank;
        private Integer popularity;
        private String type;
        private String source;
        private Integer episodes;
        private String status;
        private String duration;
        private String rating;
        private Integer year;
        private Images images;
        private Trailer trailer;
        private List<Genre> genres;
        private List<Studio> studios;
    }

    @Data
    public static class Images {
        private Jpg jpg;
    }

    @Data
    public static class Jpg {
        private String image_url;
        @JsonProperty("large_image_url")
        private String large_image_url;
    }

    @Data
    public static class Trailer {
        @JsonProperty("youtube_id")
        private String youtube_id;
        private String url;
    }

    @Data
    public static class Genre {
        private String name;
    }

    @Data
    public static class Studio {
        private String name;
    }
}