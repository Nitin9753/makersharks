package com.example.supplier.controller;


import com.example.supplier.entity.Supplier;
import com.example.supplier.service.SupplierService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @GetMapping
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("Server running", HttpStatus.OK);
    }
    @PostMapping("/query")
    public ResponseEntity<?> findSupplier(@RequestParam("location") String location,
                                          @RequestParam("nature_of_business") String nature_of_business,
                                          @RequestParam("manufacturing_process") String manufacturing_process,
                                          @RequestParam("page") int page,
                                          @RequestParam("size") int size){
        try {
            Pageable pageable= PageRequest.of(page, size);
            return new ResponseEntity<>(supplierService.findSupplier(location, nature_of_business, manufacturing_process, pageable), HttpStatus.OK);
        } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createSupplier(@RequestBody Supplier supplier){
        try{
            supplierService.save(supplier);
            return new ResponseEntity<>("resource created", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
