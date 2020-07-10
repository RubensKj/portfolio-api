package com.rubenskj.portfolio.util;

import com.rubenskj.portfolio.dto.ProjectByUrlDTO;
import com.rubenskj.portfolio.util.GitUrls.GitProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

public class HttpUtil {

    private static RestTemplate createRestTemplate() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(20);
        connectionManager.setDefaultMaxPerRoute(20);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).build();
        CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).setDefaultRequestConfig(config).build();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplateAdd = new RestTemplate(httpRequestFactory);
        restTemplateAdd.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplateAdd;
    }

    public static RestTemplate getRestTemplate() {
        return createRestTemplate();
    }


    public static UriComponents createUriComponents(String url) {
        return UriComponentsBuilder.fromUriString(url)
                .build();
    }

    public static HttpHeaders createHeadersWithAuthorization(String uuid) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(ParamsKey.AUTHORIZATION, uuid);

        return headers;
    }

    public static HttpEntity<String> setHeadersInEntity(HttpHeaders headers) {
        return new HttpEntity<>(headers);
    }

    public static <T> HttpEntity setHeadersAndBodyInEntity(HttpHeaders headers, T object) {
        return new HttpEntity(object, headers);
    }

    public static String getUrlFormattedByProvider(GitProvider provider, ProjectByUrlDTO projectByUrlDTO) {
        return String.format(provider.getUrlToBeFormatted(), projectByUrlDTO.getUser(), projectByUrlDTO.getRepoName());
    }
}
