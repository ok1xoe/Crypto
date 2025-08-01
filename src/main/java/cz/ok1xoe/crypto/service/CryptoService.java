package cz.ok1xoe.crypto.service;

import cz.ok1xoe.crypto.CryptoValidator;
import cz.ok1xoe.crypto.exception.CryptoAlreadyExistsException;
import cz.ok1xoe.crypto.model.CryptoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
@Slf4j
public class CryptoService {

    private final List<CryptoDTO> cryptos = new ArrayList<>();
    @Autowired
    private CryptoValidator cryptoValidator; // Automatická injekce validátoru


    public String HealthCheck() {
        log.info("Health check on " + LocalDateTime.now());
        return "OK";
    }

    public CryptoService(CryptoValidator cryptoValidator) {
        this.cryptoValidator = cryptoValidator;
        // cryptos.add(new CryptoDTO("42610ba4-3e0d-49c6-a67b-9173f7bbbd6c\n", "Bitcoin", "BTC", 30025.15, 0.084));
        // cryptos.add(new CryptoDTO("ca59d0d3-6a62-4eaf-89f5-3a3a2585de24\n", "Litecoin", "LTC", 1000.12, 0.157));
    }

    public List<CryptoDTO> getAllCryptos() {
        return cryptos;
    }

    public void addCrypto(CryptoDTO crypto) throws CryptoAlreadyExistsException {
        cryptoValidator.validateCrypto(crypto); // Volání validátoru

        if (crypto == null) {
            throw new IllegalArgumentException("Crypto cannot be null!");
        }
        boolean idExists = cryptos.stream()
                .anyMatch(existingCrypto -> existingCrypto.getId().equals(crypto.getId()));

        if (idExists) {
            throw new CryptoAlreadyExistsException("Crypto with the given ID already exists!");
        }
        cryptos.add(crypto);
    }

    public List<CryptoDTO> getCryptosSortedByName() {
        List<CryptoDTO> sortedCryptos = new ArrayList<>(cryptos);
        sortedCryptos.sort(Comparator.comparing(CryptoDTO::getName));
        return sortedCryptos;
    }

    public List<CryptoDTO> getCryptosSortedByPrice() {
        List<CryptoDTO> sortedCryptos = new ArrayList<>(cryptos);
        sortedCryptos.sort(Comparator.comparing(CryptoDTO::getPrice));
        return sortedCryptos;
    }

    public List<CryptoDTO> getCryptosSortedByQuantity() {
        List<CryptoDTO> sortedCryptos = new ArrayList<>(cryptos);
        sortedCryptos.sort(Comparator.comparing(CryptoDTO::getQuantity));
        return sortedCryptos;
    }

    public CryptoDTO getCryptoById(String id) {
        return cryptos.stream()
                .filter(crypto -> crypto.getId().equalsIgnoreCase(id)) // Porovnání podle ID
                .findFirst()
                .orElse(null);
    }
}
