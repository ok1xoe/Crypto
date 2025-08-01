package cz.ok1xoe.crypto;

import cz.ok1xoe.crypto.exception.InvalidCryptoDataException;
import cz.ok1xoe.crypto.model.CryptoDTO;
import org.springframework.stereotype.Component;

@Component
public class CryptoValidator {
    public void validateCrypto(CryptoDTO crypto) throws InvalidCryptoDataException {
        if (crypto.getId() == null || crypto.getId().isEmpty()) {
            throw new InvalidCryptoDataException("Crypto ID cannot be null or empty!");
        }
        if (crypto.getName() == null || crypto.getName().isEmpty()) {
            throw new InvalidCryptoDataException("Crypto name cannot be null or empty!");
        }
        if (crypto.getSymbol() == null || crypto.getSymbol().isEmpty()) {
            throw new InvalidCryptoDataException("Crypto symbol cannot be null or empty!");
        }
        if (crypto.getPrice() <= 0) {
            throw new InvalidCryptoDataException("Crypto price must be greater than 0!");
        }
        if (crypto.getQuantity() < 0) {
            throw new InvalidCryptoDataException("Crypto quantity cannot be negative!");
        }

    }
}