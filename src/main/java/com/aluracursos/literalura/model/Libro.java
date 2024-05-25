package com.aluracursos.literalura.model;

public class Libro {
    private String titulo;
    private String autor;
    private Integer annoNacimiento;
    private Integer annoFallecido;
    private String tematica;
    private String categoria;
    private String idioma;
    private Integer descargas;

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autor().get(0).nombre();
        this.annoNacimiento = datosLibro.autor().get(0).annoNacimiento();
        this.annoFallecido = datosLibro.autor().get(0).annoFallecido();
        try {
            this.tematica = datosLibro.tematica().get(0);
        } catch (Exception e) {
            this.tematica = "";
        }
        try {
            this.categoria = datosLibro.categoria().get(0);
        } catch (Exception e) {
            this.categoria = "";
        }
        this.idioma = datosLibro.idioma().get(0);
        this.descargas = datosLibro.descargas();
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
