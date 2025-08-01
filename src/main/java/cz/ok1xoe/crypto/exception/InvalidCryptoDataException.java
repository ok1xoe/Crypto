package cz.ok1xoe.crypto.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidCryptoDataException extends RuntimeException {
    public InvalidCryptoDataException(String message) {
        log.error(message);
    }
}
