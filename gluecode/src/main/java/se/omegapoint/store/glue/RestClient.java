package se.omegapoint.store.glue;


import com.fasterxml.jackson.databind.ObjectMapper;
import gherkin.deps.com.google.gson.Gson;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import se.omegapoint.store.glue.dto.ErrorDTO;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RestClient {

    private RestTemplate restTemplate;

    public RestClient(){
        this.restTemplate = new RestTemplate();
    }

    public <R> R get(URI uri, Class<R> returnType){
        return get(uri, new HttpHeaders(), returnType);
    }

    private <R> R get(URI uri, HttpHeaders headers, Class<R> returnType){
        final HttpEntity<Void> httpEntity = new HttpEntity<>(null, headers);
        final ResponseEntity<R> exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, returnType);
        assertThat(exchange.getStatusCode()).as("Return code for GET " + uri.getPath()).isEqualTo(HttpStatus.OK);
        return exchange.getBody();
    }

    public <R> R get(URI uri, ParameterizedTypeReference<R> returnType){
        final ResponseEntity<R> exchange = restTemplate.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY, returnType);
        assertThat(exchange.getStatusCode())
                .as("Return code for GET " + uri.getPath())
                .isEqualTo(HttpStatus.OK);
        return exchange.getBody();
    }

    public <T> void postToUri(URI uri, T objectToPost){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        final HttpEntity<T> httpEntity = new HttpEntity<>(objectToPost, headers);
        final ResponseEntity<Void> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Void.class);
        assertThat(exchange.getStatusCode())
                .as("Return code for void POST")
                .isIn(HttpStatus.OK, HttpStatus.CREATED);
    }

    public <R,T> R postToUri(URI uri, T objectToPost, ParameterizedTypeReference<R> returnType){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        final HttpEntity<T> httpEntity = new HttpEntity<>(objectToPost, headers);
        final ResponseEntity<R> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, returnType);
        assertThat(exchange.getStatusCode())
                .as("Return code for " + objectToPost.getClass() + " POST")
                .isIn(HttpStatus.OK, HttpStatus.CREATED);
        return exchange.getBody();
    }

    public <R,T> R postToUriInvalid(URI uri, T objectToPost) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        final HttpEntity<T> httpEntity = new HttpEntity<>(objectToPost, headers);
        try {
            final ResponseEntity<R> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<R>() {
            });
        }catch (HttpClientErrorException error){
            String errorString = error.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();

            ErrorDTO result = mapper.readValue(errorString, ErrorDTO.class);
                return (R) result;
        }
        return null;
    }

    public <T> void putToUri(URI uri, T objectToPut){
        final HttpEntity<T> httpEntity = new HttpEntity<>(objectToPut);
        final ResponseEntity<Void> exchange = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, Void.class);
        assertThat(exchange.getStatusCode()).as("Return code for void POST").isEqualTo(HttpStatus.OK);
    }

    public <R,T> R putToUri(URI uri, T objectToPut, ParameterizedTypeReference<R> returnType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        final HttpEntity<T> httpEntity = new HttpEntity<>(objectToPut, headers);
        final ResponseEntity<R> exchange = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, returnType);
        assertThat(exchange.getStatusCode()).as("Return code for " + objectToPut.getClass() + " PUT").isEqualTo(HttpStatus.OK);
        return exchange.getBody();
    }

    public void delete(URI uri){
        final ResponseEntity<Void> exchange = restTemplate.exchange(uri, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertThat(exchange.getStatusCode()).as("Return code for DELETE " + uri.getPath()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}

