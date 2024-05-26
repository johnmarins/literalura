package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.model.DatosCatalogo;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;


import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    private final Scanner sc = new Scanner(System.in);
    private final String URL = "https://gutendex.com/books/";
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    private final LibroRepository repository;
    public Principal(LibroRepository repository) {
        this.repository = repository;
    }

    public void muestraElMenu(){

        var opcion = -1;
        var menu = """
                1 - Buscar por palabras clave
                2 - Listar todos libros consultados
                3 - Buscar libros entre fechas
                4 - Listar libros por autor
                5 - Listar libros por categoria
                6 - Listar libros por idioma
                
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
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }
    }

    ArrayList<Libro> libroArrayList = new ArrayList<>();

    private void consultaPalabrasClave() {
        System.out.println("Escribe los terminos de búsqueda (Titulo y/o autor) : ");
        var nombreLibro = sc.nextLine();
        var json = consumoAPI.obtenerDatos(URL + "?search=" + nombreLibro.replace(" ","%20"));
        System.out.println(json);

        var resultados = conversor.obtenerDatos(json, DatosCatalogo.class);

        if (resultados.cantResultados()==0) {
            System.out.println();

            System.out.println("No hay resultados coincidentes");
            System.out.println();
        } else {
            Libro libro = new Libro(resultados);
            repository.save(libro);

            System.out.printf("""
                
                %s
                
                """, libro);
        }

    }

    private void listarLibrosConsultados() {
        if (libroArrayList.isEmpty()){
            System.out.println("No hay libros registrados hasta el momento");
            System.out.println();
        } else {
            libroArrayList.forEach(System.out::println);
            System.out.println();
        }

    }
}
