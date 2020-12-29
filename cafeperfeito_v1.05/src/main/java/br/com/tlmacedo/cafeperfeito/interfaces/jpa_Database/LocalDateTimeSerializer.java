package br.com.tlmacedo.cafeperfeito.interfaces.jpa_Database;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATAHORA;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    //static final DateTimeFormatter DATE_FORMATTER =  DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        try {
//            String s = value.format(DATE_FORMATTER);
            String s = value.format(DTF_DATAHORA);
            gen.writeString(s);
        } catch (DateTimeParseException e) {
            System.err.println(e);
            gen.writeString("");
        }
    }
}