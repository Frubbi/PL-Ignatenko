package com.example.demo.dto;

public record CustomerDto(
        String customerCode,
        String customerName,
        String customerInn,
        String customerKpp,
        String customerLegalAddress,
        String customerPostalAddress,
        String customerEmail,
        String customerCodeMain,
        Boolean isOrganization,
        Boolean isPerson
) {}
