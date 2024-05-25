package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") Object autor,
        @JsonAlias("subjects") Object genero,
        @JsonAlias("languages") Object idioma,
        @JsonAlias("download_count") Integer descargas
        ) {
}
