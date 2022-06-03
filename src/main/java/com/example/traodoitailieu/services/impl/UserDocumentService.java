package com.example.traodoitailieu.services.impl;

import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.entities.UserDocument;
import com.example.traodoitailieu.repositories.UserDocumentRepo;
import com.example.traodoitailieu.services.IUserDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDocumentService implements IUserDocumentService {

    @Autowired
    UserDocumentRepo userDocumentRepo;

    @Override
    public ArrayList<UserDocument> getAll() {
        ArrayList<UserDocument> list = new ArrayList<>();
        Iterable it = userDocumentRepo.findAll();
        for(Object userDocument: it){
            list.add((UserDocument) userDocument);
        }
        return list;
    }

    @Override
    public void saveUserDocument(UserDocument userDocument) {
            UserDocument saveUserDocument = new UserDocument();
            saveUserDocument.setDocument_id(userDocument.getDocument_id());
            saveUserDocument.setUser_id(userDocument.getUser_id());
            saveUserDocument.setCategory_id(userDocument.getCategory_id());
            userDocumentRepo.save(saveUserDocument);
    }

    @Override
    public UserDocument getById(int id) {
        return userDocumentRepo.findById(id).get();
    }
}
