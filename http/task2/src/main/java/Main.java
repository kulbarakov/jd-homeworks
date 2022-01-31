import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;

public class Main {
    public static String NASA_URL = "https://api.nasa.gov/planetary/apod?api_key=AQMT2eZ6R4fTu7cIgQqUZKbkJHLrvrC7QGGmR7eN";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        HttpGet request = new HttpGet(NASA_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            NasaContent content = mapper.readValue(response.getEntity()
                    .getContent(), new TypeReference<>(){});
            System.out.println(content.getUrl());

            CloseableHttpResponse contentResponse = httpClient.execute(new HttpGet(content.getUrl()));
            String[] buffer = content.getUrl().split("/");
            FileOutputStream fos = new FileOutputStream(buffer[buffer.length-1]);
            fos.write(contentResponse.getEntity().getContent().readAllBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
