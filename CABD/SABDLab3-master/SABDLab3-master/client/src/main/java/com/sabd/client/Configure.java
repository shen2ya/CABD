package com.sabd.client;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.security.KeyStore;

@Configuration
public class Configure {

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate rstTemp = new RestTemplate();
        KeyStore ks;
        HttpComponentsClientHttpRequestFactory req = null;
        try {
            ks = KeyStore.getInstance("jks");
            ClassPathResource clsPath = new
                    ClassPathResource("gateway.jks");
            InputStream inputStream = clsPath.getInputStream();
            ks.load(inputStream, "123456".toCharArray());
            SSLConnectionSocketFactory socket = new
                    SSLConnectionSocketFactory(new SSLContextBuilder()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .loadKeyMaterial(ks, "123456".toCharArray()).build(),
                    NoopHostnameVerifier.INSTANCE);
            HttpClient httpClient =
                    HttpClients.custom().setSSLSocketFactory(socket)
                            .setMaxConnTotal(5)
                            .setMaxConnPerRoute(5)
                            .build();
            req = new HttpComponentsClientHttpRequestFactory(httpClient);
            req.setReadTimeout(10000);
            req.setConnectTimeout(10000);
            rstTemp.setRequestFactory(req);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return rstTemp;
    }

}
