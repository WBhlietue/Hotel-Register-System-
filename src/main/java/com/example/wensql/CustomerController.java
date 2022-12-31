package com.example.wensql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerController {

    @Autowired
    private CustomerRepo repo;

    public void Add(Customer cus) {
        repo.save(cus);
    }
    public Customer Get(String code){
        try{
        return repo.findById(code).get();
        }catch(Exception e){
            return null;
        }
    }

    public List<Customer> GetAll(){
        return repo.findAll();
    }
    public void Delete(String code){
        repo.deleteById(code);
    }
    public void Update(String code, Customer cus){
        // cus.setCusCode(code);
        repo.save(cus);
    }
}
