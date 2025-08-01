package cz.ok1xoe.crypto.controller;

import cz.ok1xoe.crypto.exception.CryptoAlreadyExistsException;
import cz.ok1xoe.crypto.exception.InvalidCryptoDataException;
import cz.ok1xoe.crypto.model.CryptoDTO;
import cz.ok1xoe.crypto.service.CryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/manager")
@Slf4j
public class CryptoManagerController {

//    List<CryptoDTO> crypto = new ArrayList<>();
//    CryptoDTO bitcoin = new CryptoDTO("Bitcoin", "BTC", 105000.12,0.084);
//    CryptoDTO ethereum = new CryptoDTO("Ethereum", "ETH", 1000.12,0.084);
//    CryptoDTO litecoin = new CryptoDTO("Litecoin", "LTC", 1000.12,0.084);

    private final CryptoService cryptoService;

    // Konstruktor pro injektování služby
    @Autowired
    public CryptoManagerController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/")
    public String getHealthCheck() {
        cryptoService.HealthCheck();
        return "OK";
    }

    @PostMapping("/cryptos")
    public ResponseEntity<String> addCrypto(@RequestBody CryptoDTO crypto) throws CryptoAlreadyExistsException {

        try {
            cryptoService.addCrypto(crypto);
            log.info("Crypto added: {}", crypto);
            log.debug("Crypto added: {}", crypto);
            log.trace("Crypto added: {}", crypto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Crypto added successfully!");
        } catch (InvalidCryptoDataException e) {
            // InvalidCryptoDataException: Vrátí stav 400 Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CryptoAlreadyExistsException e) {
            // CryptoAlreadyExistsException: Vrátí stav 409 Conflict
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            // General exception (fallback): Vrátí stav 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred!");
        }

    }

}
