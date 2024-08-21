package com.example.supplier.repository;

import com.example.supplier.entity.Supplier;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SupplierRespositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;
    public void save(Supplier supplier){
        try {
            mongoTemplate.save(supplier);
        } catch (Exception e) {
            throw new RuntimeException("Exception in supplier respository"+e);
        }
    }
    public void delete(String Id){
        try {
            Query query=new Query();
            query.addCriteria(Criteria.where("id").is(new ObjectId(Id)));
            mongoTemplate.findAndRemove(query, Supplier.class);
        } catch (Exception e) {
            throw new RuntimeException("Exception in supplier respository"+e);
        }
    }
    public Page<Supplier> get(String location, String nature_of_business, String manufacturing_processes, Pageable pageable){
        Query query= null;
        try {
            query = new Query();
            query.addCriteria(Criteria.where("location").is(location));
            query.addCriteria(Criteria.where("nature_of_business").is(nature_of_business));
            query.addCriteria(Criteria.where("manufacturing_processes").exists(true).in(manufacturing_processes));
            long total=mongoTemplate.count(query, Supplier.class);
            query.with(pageable);
            List<Supplier> suppliers=mongoTemplate.find(query, Supplier.class);
            return new PageImpl<>(suppliers, pageable, total);
        } catch (Exception e) {
            throw new RuntimeException("Exception in supplier respository"+e);
        }
    }

}
