package com.techdays.kubernetes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Greeting {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    
}
