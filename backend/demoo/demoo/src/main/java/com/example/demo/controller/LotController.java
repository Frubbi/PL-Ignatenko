package com.example.demo.controller;

import com.example.demo.dto.LotDto;
import com.example.demo.service.LotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/lots")
public class LotController {

    private final LotService service;

    public LotController(LotService service) {
        this.service = service;
    }

    @GetMapping
    public List<LotDto> getAll() {
        return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody LotDto dto) {
        service.create(dto);
    }

    @PutMapping("/{lotName}/{customerCode}")
    public void update(@PathVariable String lotName,
                       @PathVariable String customerCode,
                       @RequestBody LotDto dto) {
        service.update(lotName, customerCode, dto);
    }

    @DeleteMapping("/{lotName}/{customerCode}")
    public void delete(@PathVariable String lotName,
                       @PathVariable String customerCode) {
        service.delete(lotName, customerCode);
    }
}
