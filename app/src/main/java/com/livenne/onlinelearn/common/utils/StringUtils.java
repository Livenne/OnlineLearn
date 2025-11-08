package com.livenne.onlinelearn.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtils{
    public static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T formJson(String json,TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T formJson(String json,Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DecodedJWT getDecoded(){
        return JWT.decode(NetworkUtils.token);
    }
}
