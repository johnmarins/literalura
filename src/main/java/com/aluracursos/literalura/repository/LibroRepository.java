package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT l FROM Libro l WHERE l.annoNacimiento >= :annoNacimiento and l.annoFallecido <= :annoFallecimiento")
    List<Libro> librosPorFechaNacimientoFechaFallecimientoAutor(int annoNacimiento, int annoFallecimiento);

    @Query("SELECT l FROM Libro l WHERE l.titulo ILIKE %:titulo%")
    List<Libro> librosPorTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.autor ILIKE %:autor%")
    List<Libro> librosPorAutor(String autor);

    List<Libro> findTop5ByOrderByDescargasDesc();

    @Query("SELECT l FROM Libro l WHERE idioma = :idioma")
    List<Libro> librosPorIdioma(String idioma);
}
