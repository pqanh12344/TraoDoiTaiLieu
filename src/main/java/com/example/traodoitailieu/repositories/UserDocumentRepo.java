package com.example.traodoitailieu.repositories;

import com.example.traodoitailieu.entities.UserDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentRepo extends CrudRepository<UserDocument, Integer> {

}
