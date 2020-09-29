package swm11.jdk.jobtreaming.back.app.images.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/images")
@Log4j2
public class ImageController {

    @GetMapping(value = "/category/{fileName}")
    public HttpEntity<byte[]> getStoreImage(@PathVariable("fileName") String fileName) throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/category/" + fileName);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(fileName.contains("png") ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG);
        byte[] image = org.apache.commons.io.IOUtils.toByteArray(in);
        return new HttpEntity<>(image, headers);
    }

}