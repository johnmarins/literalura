package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.model.DatosCatalogo;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner sc = new Scanner(System.in);
    private final String URL = "https://gutendex.com/books/";
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> libros;

    private final LibroRepository repository;
    public Principal(LibroRepository repository) {
        this.repository = repository;
    }

    public void muestraElMenu(){

        var opcion = -1;
        var menu = """
                
                1 - Buscar por palabras clave
                2 - Listar todos libros consultados
                3 - Buscar libros entre fechas de vida del autor
                4 - Listar libros por autor
                5 - Listar libros por titulo
                6 - Listar top libros por descargas
                7 - Listar libros por idioma
                
                0 - Salir
                """;
        while (opcion != 0) {
            System.out.println(menu);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    consultaPalabrasClave();
                    break;
                case 2:
                    listarLibrosConsultados();
                    break;
                case 3:
                    listarLibrosEntreFechas();
                    break;
                case 4:
                    listarLibrosPorNombreAutor();
                    break;
                case 5:
                    listarLibrosTitulo();
                    break;
                case 6:
                    listarTop5Descargas();
                    break;
                case 7:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }
    }

    private void consultaPalabrasClave() {
        System.out.println("Escribe los terminos de búsqueda (Titulo y/o autor) : ");
        var nombreLibro = sc.nextLine();
        var json = consumoAPI.obtenerDatos(URL + "?search=" + nombreLibro.replace(" ","%20"));

        var resultados = conversor.obtenerDatos(json, DatosCatalogo.class);

        if (resultados.cantResultados()==0) {
            System.out.println();

            System.out.println("No hay resultados coincidentes");
            System.out.println();
        } else {
            Libro libro = new Libro(resultados);
            try {
                repository.save(libro);
            } catch (DataIntegrityViolationException e) {
                System.out.println("Este libro ya existe en la base de datos. ");
            }

            System.out.printf("""
                
                %s
                
                """, libro);
        }

    }

    private void listarLibrosConsultados() {
        libros = repository.findAll();
        libros.stream().sorted(Comparator.comparing(Libro::getId))
                .forEach(System.out::println);
        System.out.println();
    }

    private void listarLibrosEntreFechas() {
        System.out.println("Indique la fecha inicial para el nacimiento del autor: ");
        var anno_nacimiento = sc.nextInt();
        sc.nextLine();
        System.out.println("Indique la fecha final para el fallecimiento del autor");
        var anno_fallecimiento = sc.nextInt();
        sc.nextLine();
        List<Libro> filtroLibros =repository.librosPorFechaNacimientoFechaFallecimientoAutor(anno_nacimiento, anno_fallecimiento);
        System.out.println("*** Libros filtrados ***");
        filtroLibros.forEach(System.out::println);
        System.out.println();
    }

    public void listarLibrosPorNombreAutor(){
        System.out.println("Escribe el nombre del autor");
        var nombreLibro =sc.nextLine();
        List<Libro> librosEncontrados =repository.librosPorAutor(nombreLibro);
        librosEncontrados.forEach(System.out::println);
        System.out.println();
    }

    public void listarLibrosTitulo(){
        System.out.println("Escribe el nombre del libro");
        var nombreLibro =sc.nextLine();
        List<Libro> librosEncontrados =repository.librosPorTitulo(nombreLibro);
        librosEncontrados.forEach(System.out::println);
        System.out.println();
    }

    public void listarTop5Descargas() {
        List<Libro> topDescargas = repository.findTop5ByOrderByDescargasDesc();
        topDescargas.forEach(System.out::println);
        System.out.println();
    }

    public void listarLibrosPorIdioma() {
        System.out.println("Escribe el idioma que quieres consultar");
        var idioma = sc.nextLine();
        List<Libro> librosPorIdioma = repository.librosPorIdioma(idioma);
        librosPorIdioma.forEach(System.out::println);
        System.out.println();
    }
}
