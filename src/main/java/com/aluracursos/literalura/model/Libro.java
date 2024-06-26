package com.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private Integer annoNacimiento;
    private Integer annoFallecido;
    private String tematica;
    private String categoria;
    private String idioma;
    private Integer descargas;

    public Libro(){}

    public Libro(DatosCatalogo datosCatalogo) {
        try {
            this.titulo = datosCatalogo.resultados().get(0).titulo().substring(0,254);
        } catch (Exception e) {
            this.titulo = datosCatalogo.resultados().get(0).titulo();
        }

        try {
            this.autor = datosCatalogo.resultados().get(0).autor().get(0).nombre();
        } catch (Exception e) {
            this.autor = null;
        }
        this.annoNacimiento = datosCatalogo.resultados().get(0).autor().get(0).annoNacimiento();
        this.annoFallecido = datosCatalogo.resultados().get(0).autor().get(0).annoFallecido();
        try {
            this.tematica = datosCatalogo.resultados().get(0).tematica().get(0);
        } catch (Exception e) {
            this.tematica = null;
        }
        try {
            this.categoria = datosCatalogo.resultados().get(0).categoria().get(0);
        } catch (Exception e) {
            this.categoria = null;
        }
        this.idioma = datosCatalogo.resultados().get(0).idioma().get(0);
        this.descargas = datosCatalogo.resultados().get(0).descargas();
    }

    @Override
    public String toString() {
        return  "Titulo : '" + titulo + '\'' +
                ", Autor : '" + autor + '\'' +
                ", Año de Nacimiento : " + annoNacimiento +
                ", Año de Fallecimiento : " + annoFallecido +
                ", Tematica : '" + tematica + '\'' +
                ", Categoria : '" + categoria + '\'' +
                ", Idioma : '" + idioma + '\'' +
                ", Descargas : " + descargas;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Integer getAnnoNacimiento() {
        return annoNacimiento;
    }

    public void setAnnoNacimiento(Integer annoNacimiento) {
        this.annoNacimiento = annoNacimiento;
    }

    public Integer getAnnoFallecido() {
        return annoFallecido;
    }

    public void setAnnoFallecido(Integer annoFallecido) {
        this.annoFallecido = annoFallecido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}