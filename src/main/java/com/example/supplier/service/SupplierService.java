package com.example.supplier.service;

import com.example.supplier.entity.Supplier;
import com.example.supplier.repository.SupplierRespositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRespositoryImpl supplierRespository;
    public Page<Supplier> findSupplier(String location, String nature_of_business, String manufacturing_processes, Pageable pageable){
        try {
            return supplierRespository.get(location, nature_of_business, manufacturing_processes, pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void save(Supplier supplier){
        try {
            supplierRespository.save(supplier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteById(String Id){
        try {
            supplierRespository.delete(Id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
