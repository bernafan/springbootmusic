package br.com.devmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* CLASSE RESPONSAVEL POR RODAR A APLICAOCAO WEB
* O SERVIDOR WEB ￿É INICIADO JUNTO COM A APLICACAO
* */

@SpringBootApplication
public class SpotMusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpotMusicApplication.class, args);
    }
}
