package com.eliasshallouf.examples.library_management_system.service.utils;

public class JsonHelper {
    public static String asJsonString(final Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
