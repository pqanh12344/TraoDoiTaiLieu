package com.example.traodoitailieu.services;

import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.entities.UserDocument;

import java.util.ArrayList;

public interface IUserDocumentService {
    ArrayList<UserDocument> getAll();
    void saveUserDocument(UserDocument userDocument);
    UserDocument getById(int id);
}
