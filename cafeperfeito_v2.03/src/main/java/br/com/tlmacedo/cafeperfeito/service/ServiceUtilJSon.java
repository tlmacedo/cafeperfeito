package br.com.tlmacedo.cafeperfeito.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class ServiceUtilJSon {
    static ObjectMapper mapper = new ObjectMapper();

    public static Object getObjectFromJson(String json, Class classe) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            System.out.printf("jsonObject: %s", jsonObject.toString());
            return mapper.readValue(json, classe);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJsonObjectFromObject(Object object) {
        try {
            return new JSONObject(mapper.writeValueAsString(object));
        } catch (JSONException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getJsonFromObject(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            return null;
        }
    }

    public static String getJsonFromList(List list) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printJsonFromObject(Object object, String label) {
        try {
            if (label != null)
                System.out.printf(label + "\n");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printJsonFromString(String string, String label) {
        try {
            JsonFactory factory = mapper.getFactory();
            JsonParser parser = factory.createParser(string);
            JsonNode actualObj = mapper.readTree(parser);
            if (label != null)
                System.out.printf(label + "\n");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(actualObj) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printJsonFromList(List list, String label) {
        try {
            if (label != null)
                System.out.printf(label + "\n");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}