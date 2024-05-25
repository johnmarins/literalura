package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private final String URL = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu(){
        System.out.println("Escribe el nombre del libro: ");
        var nombreLibro = sc.nextLine();
        var json = consumoAPI.obtenerDatos(URL + "?search=" + nombreLibro.replace(" ","%20"));
        System.out.println(json);
        var datosLibro = conversor.obtenerDatos(json, DatosLibro.class);
        System.out.println(datosLibro);
    }
}
