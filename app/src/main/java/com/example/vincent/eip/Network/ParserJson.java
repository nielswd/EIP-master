package com.example.vincent.eip.Network;

/**
 * Created by Vincent RAGOT on 27/11/2016.
 */

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ParserJson<T> {
    private T value;

    public ParserJson() {}

    public ParserJson(T val) {
        value = val;
    }

    public String writeJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return (mapper.writeValueAsString(value));
    }

    public T writeObject(String str, Class<T> classe)
    {
        T retValue = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            retValue = mapper.readValue(str, classe);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (retValue);
    }
}
