package com.example.traodoitailieu.services.impl;

import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.repositories.CategoryRepo;
import com.example.traodoitailieu.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public ArrayList<Category> getAll(){
        ArrayList<Category> list = new ArrayList<>();
        Iterable it = categoryRepo.findAll();
        for(Object category: it){
            list.add((Category) category);
        }
        return list;
    }

    @Override
    public void saveCategory(Category category){
        Category saveCategory = new Category();
        saveCategory.setCategory_id(category.getCategory_id());
        saveCategory.setCategory_name(category.getCategory_name());
        categoryRepo.save(saveCategory);
    }

    @Override
    public Category getById(int id){
        return categoryRepo.findById(id).get();
    }

    @Override
    public void deleteById(int id){
        categoryRepo.deleteById(id);
    }
}
