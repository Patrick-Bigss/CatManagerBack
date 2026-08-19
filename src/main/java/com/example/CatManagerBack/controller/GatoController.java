package com.example.CatManagerBack.controller;

import com.example.CatManagerBack.entity.GatoEntity;
import com.example.CatManagerBack.repository.GatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gatinhos")
@CrossOrigin(origins = "*")
public class GatoController {

    @Autowired
    private GatoRepository comandos;


    @PostMapping
    public GatoEntity salvarGatos(
            @RequestBody GatoEntity gato) {

        return comandos.save(gato);
    }


    @GetMapping
    public List<GatoEntity> listarGatos() {

        return comandos.findAll();
    }


    @GetMapping("/{id}")
    public GatoEntity buscarGatoPorId(
            @PathVariable Long id) {

        return comandos.findById(id).orElseThrow();
    }


    @PutMapping("/{id}")
    public GatoEntity atualizarGato(
            @PathVariable Long id,
            @RequestBody GatoEntity gato) {

        gato.setId(id);

        return comandos.save(gato);
    }


    @DeleteMapping("/{id}")
    public String apagarGato(
            @PathVariable Long id) {

        GatoEntity gato =
                comandos.findById(id).orElseThrow();

        String name = gato.getName();

        comandos.deleteById(id);

        return "Gatinho " + name +
                " deletado com sucesso!";
    }

    @GetMapping("/aleatorio")
    public String gatoAleatorio() {

        String url =
                "https://api.thecatapi.com/v1/images/search";

        RestTemplate restTemplate =
                new RestTemplate();

        return restTemplate.getForObject(
                url,
                String.class
        );
    }
}