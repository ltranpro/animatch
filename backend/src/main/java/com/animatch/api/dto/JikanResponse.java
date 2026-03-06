package com.animatch.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class JikanResponse {
    private List<AnimeData> data;

    @Data
    public static class AnimeData {
        private String title;
        private String synopsis;
        private Images images;

        @Data
        public static class Images {
            private Jpg jpg;

            @Data
            public static class Jpg {
                private String image_url;
            }
        }
    }
}