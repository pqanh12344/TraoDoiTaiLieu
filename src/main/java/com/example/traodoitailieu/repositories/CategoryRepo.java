package com.example.traodoitailieu.repositories;

import com.example.traodoitailieu.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository <Category, Integer> {

}
