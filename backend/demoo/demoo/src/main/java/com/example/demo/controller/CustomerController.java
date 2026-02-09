package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{code}")
    public CustomerDto getByCode(@PathVariable String code) {
        return service.findByCode(code);
    }

    @PostMapping
    public void create(@RequestBody CustomerDto dto) {
        service.create(dto);
    }

    @PutMapping("/{code}")
    //public void update(@PathVariable String code,
    //                   @RequestBody CustomerDto dto) {
    //    service.update(code, dto);
    //}
    public void update(@PathVariable String code,
                       @RequestBody CustomerDto dto) {
        try {
            service.update(code, dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        service.delete(code);
    }
}
