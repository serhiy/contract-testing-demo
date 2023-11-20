package com.onlinebrewery.orders;

import java.io.IOException;

public class TestDataReader {
    public static String readFile(String name) throws IOException {
        var classLoader = TestDataReader.class.getClassLoader();
        try(var resource = classLoader.getResourceAsStream(name)) {
            return new String(resource.readAllBytes());
        } catch (Exception e) {
            throw e;
        }
    }
}
