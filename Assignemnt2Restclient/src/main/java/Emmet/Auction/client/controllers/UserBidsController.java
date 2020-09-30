package Emmet.Auction.client.controllers;

import Emmet.Auction.client.domain.Bid;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class UserBidsController {
    @GetMapping("/showbids/{id}")
    public String getBidsById(@PathVariable(name = "id") String emailToGetBidsBy, Model model){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = createHeaders("david@efm", "1");
        ResponseEntity<List<Bid>> returnedBids = template.exchange("http://localhost:8080/apis/bids/" + emailToGetBidsBy, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Bid>>() {} ) ;
        model.addAttribute("bids", returnedBids.getBody());

        return "showbids";
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
