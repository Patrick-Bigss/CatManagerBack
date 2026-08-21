package com.example.CatManagerBack.controller;

import com.example.CatManagerBack.entity.GatoEntity;
import com.example.CatManagerBack.repository.GatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/gatinhos")
@CrossOrigin(origins = "*")
public class GatoController {

    @Autowired
    private GatoRepository comandos;

    @Value("${thecatapi.key}")
    private String catApiKey;


    @GetMapping
    public List<GatoEntity> listarGatos() {

        return comandos.findAll();
    }


    @GetMapping("/id/{id}")
    public GatoEntity buscarGatoPorId(
            @PathVariable Long id) {

        return comandos.findById(id)
                .orElseThrow();
    }

    @PostMapping
    public GatoEntity salvarGatos(
            @RequestBody GatoEntity gato) {

        return comandos.save(gato);
    }



    @PutMapping("/api/{catApiId}")
    public GatoEntity atualizarPorCatApiId(
            @PathVariable String catApiId,
            @RequestBody GatoEntity gato) {

        GatoEntity gatoExistente =
                comandos.findByCatApiId(catApiId)
                        .orElseThrow();

        gatoExistente.setCatApiId(catApiId);

        gatoExistente.setName(gato.getName());
        gatoExistente.setSpeciesId(gato.getSpeciesId());
        gatoExistente.setLifeSpan(gato.getLifeSpan());
        gatoExistente.setTemperament(gato.getTemperament());
        gatoExistente.setOrigin(gato.getOrigin());
        gatoExistente.setCountryCodes(gato.getCountryCodes());
        gatoExistente.setCountryCode(gato.getCountryCode());
        gatoExistente.setDescription(gato.getDescription());
        gatoExistente.setBredFor(gato.getBredFor());
        gatoExistente.setPerfectFor(gato.getPerfectFor());
        gatoExistente.setBreedGroup(gato.getBreedGroup());
        gatoExistente.setHistory(gato.getHistory());
        gatoExistente.setReferenceImageId(
                gato.getReferenceImageId()
        );
        gatoExistente.setWeight(gato.getWeight());
        gatoExistente.setHeight(gato.getHeight());
        gatoExistente.setImage(gato.getImage());

        return comandos.save(gatoExistente);
    }

    @DeleteMapping("/api/{catApiId}")
    public String apagarPorCatApiId(
            @PathVariable String catApiId) {

        GatoEntity gato =
                comandos.findByCatApiId(catApiId)
                        .orElseThrow();

        String name =
                gato.getName();

        comandos.delete(gato);

        return "Gatinho " + name +
                " deletado com sucesso!";
    }


    @GetMapping("/racas")
    public String testarRacas() {

        String url =
                "https://api.thecatapi.com/v1/breeds";

        RestTemplate restTemplate =
                new RestTemplate();

        restTemplate.getInterceptors().add(
                (request, body, execution) -> {

                    request.getHeaders().set(
                            "x-api-key",
                            catApiKey
                    );

                    return execution.execute(
                            request,
                            body
                    );
                }
        );

        return restTemplate.getForObject(
                url,
                String.class
        );
    }

}