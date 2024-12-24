package com.aluraoracle.api_foro.perfil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PerfilDeserializer extends JsonDeserializer<Perfil> {

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public Perfil deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String nombre = node.asText();
        return perfilRepository.findByNombre(nombre)
                .orElseThrow(() -> new IOException("Perfil not found: " + nombre));
    }
}