package com.sujalgangarde.journalApp.repository;

import com.sujalgangarde.journalApp.entity.JournalEntry;
import com.sujalgangarde.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);

    void deleteByUsername(String username);

}
