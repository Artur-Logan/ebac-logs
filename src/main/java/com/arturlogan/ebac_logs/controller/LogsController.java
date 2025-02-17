package com.arturlogan.ebac_logs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/logs")
public class LogsController {

    private final Logger LOGGER = LoggerFactory.getLogger(LogsController.class);

    @Value("${servico.url2:}")
    private String urlSistema2;


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String mostrarMensagem(){
        final double mensagem = Math.random();

        LOGGER.info("Gerei uma mensagem {}", mensagem);

        if (urlSistema2.length() > 0){
            final String mensagemDeles = restTemplate.exchange(urlSistema2, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
            }).getBody();

            return("Eu disse isso: " + mensagem + " e eles disseram isso: (" + mensagemDeles + ")");
        }

        return "E eu deveria parar aqui";
    }

}