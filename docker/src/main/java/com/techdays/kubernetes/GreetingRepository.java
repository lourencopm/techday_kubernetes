package com.techdays.kubernetes;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface GreetingRepository extends CrudRepository<Greeting, Long>  {
    
}
