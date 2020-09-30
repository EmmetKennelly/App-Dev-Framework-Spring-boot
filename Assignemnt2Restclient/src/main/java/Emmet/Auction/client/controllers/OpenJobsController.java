package Emmet.Auction.client.controllers;

import Emmet.Auction.client.domain.Job;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class OpenJobsController {
    @GetMapping("showopenjobs")
    public String showOpenJobs(Model model) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = createHeaders("david@efm", "1");
        ResponseEntity<List<Job>> returnedJobs = template.exchange("http://localhost:8080/apis/jobs/open", HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Job>>() {} ) ;
        model.addAttribute("openjobs", returnedJobs.getBody());
        return "showopenjobs";
    }

    HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
                String authHeader = "Basic " + new String(encodedAuth);

                set(HttpHeaders.AUTHORIZATION, authHeader);
            }
        };

    }

}
