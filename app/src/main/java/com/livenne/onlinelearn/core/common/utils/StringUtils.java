package com.livenne.onlinelearn.core.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livenne.onlinelearn.core.network.NetworkUtils;

public class StringUtils{
    public static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            if (obj == null) return null;
            return obj.toString();
        }
    }


    public static <T> T formJson(String json,TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            if (e instanceof JsonParseException) {
                try {
                    String wrappedJson = "\"" + json + "\"";
                    return mapper.readValue(wrappedJson, typeReference);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T formJson(String json,Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            if (e instanceof JsonParseException) {
                try {
                    String wrappedJson = "\"" + json + "\"";
                    return mapper.readValue(wrappedJson, clazz);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            e.printStackTrace();
            return null;
        }
    }

    public static DecodedJWT getDecoded(){
        return JWT.decode(NetworkUtils.token);
    }
}
