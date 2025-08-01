package cz.ok1xoe.crypto.exception;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CryptoAlreadyExistsException extends Exception {
    public CryptoAlreadyExistsException(String message) {
        super(message);
        log.error(message);
    }
}
