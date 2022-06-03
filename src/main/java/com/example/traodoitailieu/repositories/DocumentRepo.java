package com.example.traodoitailieu.repositories;

import com.example.traodoitailieu.entities.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends CrudRepository<Document,Integer> {

}
