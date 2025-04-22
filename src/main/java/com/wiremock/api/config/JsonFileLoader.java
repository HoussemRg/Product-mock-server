package com.wiremock.api.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonFileLoader {
    public static String loadMagasinsPage1() throws IOException {
        return loadJsonFile("data/magasins_page1.json");
    }

    public static String loadMagasinsPage2() throws IOException {
        return loadJsonFile("data/magasins_page2.json");
    }

    public static String loadEntrepotsPage1() throws IOException {
        return loadJsonFile("data/entrepots_page1.json");
    }

    public static String loadEntrepotsPage2() throws IOException {
        return loadJsonFile("data/entrepots_page2.json");
    }

    private static String loadJsonFile(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
