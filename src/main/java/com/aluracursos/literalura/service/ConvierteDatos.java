package com.aluracursos.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
//            JsonNode jsonNode = mapper.readTree(json);
//            JsonNode primerElemento = jsonNode.get("results").get(0);
//            String primerJson = primerElemento.toPrettyString();
//
            return mapper.readValue(json, clase);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
