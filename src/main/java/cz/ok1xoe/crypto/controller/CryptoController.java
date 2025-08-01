package cz.ok1xoe.crypto.controller;


import cz.ok1xoe.crypto.model.CryptoDTO;
import cz.ok1xoe.crypto.service.CryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class CryptoController{


    private final CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/")
    public String getHealthCheck() {
        cryptoService.HealthCheck();
        return "OK";
    }


    @GetMapping("/cryptos")
    public ResponseEntity<List<CryptoDTO>> getPortfolio(@RequestParam(value = "sort", required = false) String sort) {

        List<CryptoDTO> cryptos;

        if ("price".equalsIgnoreCase(sort)) {
            cryptos = cryptoService.getCryptosSortedByPrice();
        } else if ("name".equalsIgnoreCase(sort)) {
            cryptos = cryptoService.getCryptosSortedByName();
        } else if ("quantity".equalsIgnoreCase(sort)) {
            cryptos = cryptoService.getCryptosSortedByQuantity();
        } else {
            cryptos = cryptoService.getAllCryptos();
        }

        return ResponseEntity.ok(cryptos);
    }

    @GetMapping("/cryptos/{id}")
    public ResponseEntity<CryptoDTO> getCryptoById(
            @PathVariable("id") String id) {

        log.info("Crypto requested: {}", id);
        CryptoDTO crypto = cryptoService.getCryptoById(id);
        log.info("Crypto returned: {}", crypto);

        if (crypto != null) {
            return ResponseEntity.ok(crypto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
