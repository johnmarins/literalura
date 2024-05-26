package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.model.DatosCatalogo;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private final String URL = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu(){

        var opcion = -1;
        var menu = """
                1 - Buscar por palabras clave
                2 - Buscar libros entre fechas
                3 - Listar todos libros consultados
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
                    break;
                case 3:
                    listarLibrosConsultados();
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

            var datosLibro = conversor.obtenerDatos(json, DatosLibro.class);

            Libro libro = new Libro(resultados);
            libroArrayList.add(libro);

            System.out.printf("""
                
                %s
                
                """, libro);
        }

    }

    private void listarLibrosConsultados() {
        libroArrayList.forEach(System.out::println);
        System.out.println();
    }
}
