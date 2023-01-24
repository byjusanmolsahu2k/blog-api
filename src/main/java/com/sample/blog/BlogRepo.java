package com.sample.blog;

import org.springframework.data.mongodb.repository.MongoRepository;


// @RepositoryRestResource
public interface BlogRepo extends MongoRepository<Blog,String> {
    
}
