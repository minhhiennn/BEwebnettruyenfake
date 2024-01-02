package com.example.backend.controller;

import com.example.backend.helpers.HttpServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.backend.bean.Test;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping("/getHtmlByPage")
    public ResponseEntity<?> getHtmlByPage(@RequestParam String url, @RequestParam(required = false) String referer) throws IOException, InterruptedException {

        return ResponseEntity.ok(HttpServices.getResponseString(url, referer));
    }

    @GetMapping("/getImagesByPage")
    public ResponseEntity<?> getImagesByPage(@RequestParam String imgUrl) throws IOException, InterruptedException {
        byte[] b = HttpServices.getResponseByte(imgUrl);
        return ResponseEntity.ok(Base64.getEncoder().encodeToString(b));
    }


    @GetMapping("/getListSrcImg")
    public ResponseEntity<?> getListSrcImg(@RequestParam String url) throws IOException, InterruptedException {
        return ResponseEntity.ok(HttpServices.getListSrcImg(url));
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody Test test) throws IOException {
        System.out.println(test.name + '-' + test.name1);
        return ResponseEntity.ok("oke");
    }
}
