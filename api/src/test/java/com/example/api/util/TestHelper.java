package com.example.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestHelper {

   public static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
   public static final String AUTH_TOKEN = "SHOP_API_KEY";

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
