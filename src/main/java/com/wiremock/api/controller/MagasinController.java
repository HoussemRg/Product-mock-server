package com.wiremock.api.controller;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.wiremock.api.config.JsonFileLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RestController
@RequestMapping("/api/carto/magasins")
public class MagasinController {

    HttpHeaders headers = new HttpHeaders(
            new HttpHeader("Content-Type", "application/json"),
            new HttpHeader("Access-Control-Allow-Origin", "http://localhost:4200"),
            new HttpHeader("Access-Control-Allow-Credentials", "true"),
            new HttpHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"),
            new HttpHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, Origin")
    );
    @Autowired
    private WireMockServer wireMockServer;

    @PostConstruct
    public void setupMocks() throws IOException {
        wireMockServer.stubFor(any(urlMatching(".*"))
                .atPriority(100) // Lower priority than your specific stubs
                .willReturn(aResponse()));

        wireMockServer.stubFor(options(urlMatching("/api/carto/.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Access-Control-Allow-Origin", "http://localhost:4200")
                        .withHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                        .withHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, Origin")
                        .withHeader("Access-Control-Allow-Credentials", "true")
                ));
        setupMagasinsMocks();
        setupEntrepotsMocks();
    }

    private void setupMagasinsMocks() throws IOException {
        // Page 1
        wireMockServer.stubFor(get(urlPathEqualTo("/api/carto/magasins"))
                .withQueryParam("page", equalTo("0"))
                .withQueryParam("size", equalTo("10"))

                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        // ADD THESE CORS HEADERS TO EVERY RESPONSE
                        .withHeader("Access-Control-Allow-Origin", "http://localhost:4200")
                        .withHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                        .withHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, Origin")
                        .withHeader("Access-Control-Allow-Credentials", "true")
                        .withBody(JsonFileLoader.loadMagasinsPage1())
                        .withBody(JsonFileLoader.loadMagasinsPage1())
                ));

        // Page 2
        wireMockServer.stubFor(get(urlPathEqualTo("/api/carto/magasins"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("size", equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        // ADD THESE CORS HEADERS TO EVERY RESPONSE
                        .withHeader("Access-Control-Allow-Origin", "http://localhost:4200")
                        .withHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                        .withHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, Origin")
                        .withHeader("Access-Control-Allow-Credentials", "true")
                        .withBody(JsonFileLoader.loadMagasinsPage1())
                        .withBody(JsonFileLoader.loadMagasinsPage2())
                ));
    }

    private void setupEntrepotsMocks() throws IOException {
        // Page 1
        wireMockServer.stubFor(get(urlPathEqualTo("/api/carto/entrepots"))
                .withQueryParam("page", equalTo("0"))
                .withQueryParam("size", equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        // ADD THESE CORS HEADERS TO EVERY RESPONSE
                        .withHeader("Access-Control-Allow-Origin", "http://localhost:4200")
                        .withHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                        .withHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, Origin")
                        .withHeader("Access-Control-Allow-Credentials", "true")
                        .withBody(JsonFileLoader.loadMagasinsPage1())
                ));

        // Page 2
        wireMockServer.stubFor(get(urlPathEqualTo("/api/carto/entrepots"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("size", equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        // ADD THESE CORS HEADERS TO EVERY RESPONSE
                        .withHeader("Access-Control-Allow-Origin", "http://localhost:4200")
                        .withHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                        .withHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, Origin")
                        .withHeader("Access-Control-Allow-Credentials", "true")
                        .withBody(JsonFileLoader.loadMagasinsPage1())
                        .withBody(JsonFileLoader.loadEntrepotsPage2())
                ));
    }
}
