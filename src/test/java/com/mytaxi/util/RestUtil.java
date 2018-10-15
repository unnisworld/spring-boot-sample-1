package com.mytaxi.util;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import static org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

// TODO : Added as a temporarily to workaround the @JsonTest annotation issue is fixed.
public final class RestUtil 
{

    public static String toJson(Object object) 
    {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        objectMapper.setDateFormat(df);
        objectMapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
        ObjectWriter writer = objectMapper.writer();
        try {
            return writer.withDefaultPrettyPrinter().writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String data, Class<T> type) 
    {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        objectMapper.setDateFormat(df);
        try {
            return objectMapper.readValue(data, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
