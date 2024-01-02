package com.example.backend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;


@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10))
                .build();
        HttpResponse response = null;
        URI uri = URI.create("http://www.nettruyenme.com/Comic/Services/ComicService.asmx/ProcessChapterList?comicId=61789");
        HttpRequest request = HttpRequest.newBuilder()
                .header("Referer", "http://www.nettruyenme.com/")
                .uri(uri).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().toString());

    }

    @Test
    void JsoupTest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10))
                .build();
        HttpResponse response = null;
        URI uri = URI.create("http://www.nettruyenme.com/truyen-tranh/kingdom-vuong-gia-thien-ha/chap-731/904118");
        HttpRequest request = HttpRequest.newBuilder()
                .header("Referer", "http://www.nettruyenme.com/")
                .uri(uri).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Document doc = Jsoup.parse(response.body().toString());
        Elements ele1 = doc.getElementsByClass("reading-detail").select("img");
        for (Element element : ele1) {
            System.out.println(element.attr("data-original"));
        }
    }
}
