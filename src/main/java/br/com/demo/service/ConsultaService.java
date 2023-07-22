package br.com.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsultaService {
    public String consultaAltorizacao() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String json = response.getBody();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(json);
                String message = jsonNode.get("message").asText();
                return message;
            } catch (Exception e) {
                throw new HttpClientErrorException(json, null, json, null, null, null);
            }
        } else {
            throw new Exception("Não foi possível obter o JSON da URL. ");
        }
    }


    
    public String consultaNotify(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://o4d9z.mocklab.io/notify", String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String json = response.getBody();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(json);
                String message = jsonNode.get("message").asText();
                return message;
            } catch (Exception e) {
                throw new HttpClientErrorException(json, null, json, null, null, null);
            }
        } else {
            return "Não foi possível obter o JSON da URL.";
        }
    }
}
