package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.repository.CustomerRepository;
import jooqdata.tables.records.CustomerRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public CustomerDto findByCode(String code) {
        CustomerRecord record = repository.findByCode(code);
        return record == null ? null : toDto(record);
    }

    public void create(CustomerDto dto) {
        CustomerRecord record = toRecord(dto);
        repository.insert(record);
    }

    //public void update(String code, CustomerDto dto) {
    //    CustomerRecord record = toRecord(dto);
    //    record.setCustomerCode(code);
    //    repository.update(record);
    //}
    public void update(String code, CustomerDto dto) {
        CustomerRecord existing = repository.findByCode(code);

        if (existing == null) {
            throw new RuntimeException("Customer not found: " + code);
        }

        // обновляем ТОЛЬКО то, что пришло
        if (dto.customerName() != null) {
            existing.setCustomerName(dto.customerName());
        }

        if (dto.customerInn() != null) {
            existing.setCustomerInn(dto.customerInn());
        }

        if (dto.customerKpp() != null) {
            existing.setCustomerKpp(dto.customerKpp());
        }

        if (dto.customerLegalAddress() != null) {
            existing.setCustomerLegalAddress(dto.customerLegalAddress());
        }

        if (dto.customerPostalAddress() != null) {
            existing.setCustomerPostalAddress(dto.customerPostalAddress());
        }

        if (dto.customerEmail() != null) {
            existing.setCustomerEmail(dto.customerEmail());
        }

        if (dto.customerCodeMain() != null) {
            existing.setCustomerCodeMain(dto.customerCodeMain());
        }

        if (dto.isOrganization() != null) {
            existing.setIsOrganization(dto.isOrganization());
        }

        if (dto.isPerson() != null) {
            existing.setIsPerson(dto.isPerson());
        }

        repository.update(existing);
    }


    public void delete(String code) {
        repository.delete(code);
    }

    /* ===== Маппинг ===== */

    private CustomerDto toDto(CustomerRecord r) {
        return new CustomerDto(
                r.getCustomerCode(),
                r.getCustomerName(),
                r.getCustomerInn(),
                r.getCustomerKpp(),
                r.getCustomerLegalAddress(),
                r.getCustomerPostalAddress(),
                r.getCustomerEmail(),
                r.getCustomerCodeMain(),
                r.getIsOrganization(),
                r.getIsPerson()
        );
    }

    private CustomerRecord toRecord(CustomerDto dto) {
        CustomerRecord r = new CustomerRecord();
        r.setCustomerCode(dto.customerCode());
        r.setCustomerName(dto.customerName());
        r.setCustomerInn(dto.customerInn());
        r.setCustomerKpp(dto.customerKpp());
        r.setCustomerLegalAddress(dto.customerLegalAddress());
        r.setCustomerPostalAddress(dto.customerPostalAddress());
        r.setCustomerEmail(dto.customerEmail());
        r.setCustomerCodeMain(dto.customerCodeMain());
        r.setIsOrganization(dto.isOrganization());
        r.setIsPerson(dto.isPerson());
        return r;
    }
}
