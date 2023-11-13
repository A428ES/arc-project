package com.arcproject.arcproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arcproject.arcproject.interfaces.MongoInterface;

@Service
public class MongoService {
    private final MongoInterface mongoInterface;

    @Autowired
    public MongoService(MongoInterface mongoInterface){
        this.mongoInterface = mongoInterface;
    }
}
