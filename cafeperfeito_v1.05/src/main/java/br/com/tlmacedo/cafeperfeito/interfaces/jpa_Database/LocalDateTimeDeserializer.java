package br.com.tlmacedo.cafeperfeito.interfaces.jpa_Database;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATAHORA;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctx)
            throws IOException {
        String str = p.getText();
        try {
//            return LocalDateTime.parse(str, LocalDateTimeSerializer.DATE_FORMATTER);
            return LocalDateTime.parse(str, DTF_DATAHORA);
        } catch (DateTimeParseException e) {
            System.err.println(e);
            return null;
        }
    }
}