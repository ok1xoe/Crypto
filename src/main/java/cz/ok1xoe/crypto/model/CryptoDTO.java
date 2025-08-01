package cz.ok1xoe.crypto.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CryptoDTO {
    private String id;
    private String name;
    private String symbol;
    private Double price;
    private Double quantity;
}


