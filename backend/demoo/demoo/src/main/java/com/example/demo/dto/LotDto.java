package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LotDto(
        String lotName,
        String customerCode,
        BigDecimal price,
        String currencyCode,
        String ndsRate,
        String placeDelivery,
        LocalDateTime dateDelivery
) {}
