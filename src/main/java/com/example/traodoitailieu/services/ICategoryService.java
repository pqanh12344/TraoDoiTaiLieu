package com.example.traodoitailieu.services;

import com.example.traodoitailieu.entities.Category;

import java.util.ArrayList;

public interface ICategoryService {
    ArrayList<Category> getAll();
    void saveCategory(Category category);
    Category getById(int id);
    void deleteById(int id);
}
