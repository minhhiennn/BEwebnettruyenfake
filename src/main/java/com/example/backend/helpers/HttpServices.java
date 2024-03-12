package com.example.backend.helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HttpServices {

    static String link = "https://www.nettruyen4.com/";

    static HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10))
            .build();

    private static String urlWeb(String url) {
        if (url.contains("?page=1")) {
            url = url.replace("?page=1", "");
        }
        return "https://webcache.googleusercontent.com/search?q=cache:" + url;
//        return url;
    }

    public static String getResponseString(String url, String referer) throws IOException, InterruptedException {
        HttpResponse response = null;
        System.out.println(urlWeb(url));
        URI uri = URI.create(urlWeb(url));
        HttpRequest request = HttpRequest.newBuilder()
                .header("Referer", referer == null ? link : referer)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
//                .header("cookie","_ga=GA1.2.1060682002.1662527420; _gid=GA1.2.1724377404.1664337415; popup_window=1; _gat_gtag_UA_57670566_6=1")
                .uri(uri).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.headers().toString());
        return response.body().toString();
    }

    public static byte[] getResponseByte(String url) throws IOException, InterruptedException {
        HttpResponse response = null;
        URI uri = URI.create(urlWeb(url));
        HttpRequest request = HttpRequest.newBuilder()
                .header("Referer", link)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
//                .header("cookie","_ga=GA1.2.1060682002.1662527420; _gid=GA1.2.1724377404.1664337415; popup_window=1; _gat_gtag_UA_57670566_6=1")
                .uri(uri).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        return (byte[]) response.body();
    }

    public static List<String> getListSrcImg(String url) throws IOException, InterruptedException {
        List<String> result = new ArrayList<>();
        HttpResponse response = null;
        URI uri = URI.create(urlWeb(url));
        HttpRequest request = HttpRequest.newBuilder()
                .header("Referer", link)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
//                .header("cookie","_ga=GA1.2.1060682002.1662527420; _gid=GA1.2.1724377404.1664337415; popup_window=1; _gat_gtag_UA_57670566_6=1")
                .uri(uri).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Document doc = Jsoup.parse(response.body().toString());
        Elements ele1 = doc.getElementsByClass("reading-detail").select("img");
        for (Element element : ele1) {
            result.add("http:" + element.attr("data-original"));
        }
        return result;
    }
}
