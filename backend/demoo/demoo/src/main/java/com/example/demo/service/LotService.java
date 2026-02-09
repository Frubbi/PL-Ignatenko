package com.example.demo.service;

import com.example.demo.dto.LotDto;
import com.example.demo.repository.LotRepository;
import jooqdata.tables.records.LotRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotService {

    private final LotRepository repository;

    public LotService(LotRepository repository) {
        this.repository = repository;
    }

    public List<LotDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public void create(LotDto dto) {
        repository.insert(toRecord(dto));
    }

    public void update(String lotName, String customerCode, LotDto dto) {
        LotRecord record = toRecord(dto);
        record.setLotName(lotName);
        record.setCustomerCode(customerCode);
        repository.update(record);
    }

    public void delete(String lotName, String customerCode) {
        repository.delete(lotName, customerCode);
    }

    /* ===== Маппинг ===== */

    private LotDto toDto(LotRecord r) {
        return new LotDto(
                r.getLotName(),
                r.getCustomerCode(),
                r.getPrice(),
                r.getCurrencyCode(),
                r.getNdsRate(),
                r.getPlaceDelivery(),
                r.getDateDelivery()
        );
    }

    private LotRecord toRecord(LotDto dto) {
        LotRecord r = new LotRecord();
        r.setLotName(dto.lotName());
        r.setCustomerCode(dto.customerCode());
        r.setPrice(dto.price());
        r.setCurrencyCode(dto.currencyCode());
        r.setNdsRate(dto.ndsRate());
        r.setPlaceDelivery(dto.placeDelivery());
        r.setDateDelivery(dto.dateDelivery());
        return r;
    }
}
