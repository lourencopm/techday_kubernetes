package com.techdays.kubernetes;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@AllArgsConstructor
public class KubeAppController {

    private final AppConfig appConfig;
    private final GreetingRepository greetingRepository;
    
    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok().body("Hello From " + appConfig.greeting());
    }

    @GetMapping("/all")
    public ResponseEntity<String> getDatabaseStatus() {
        Iterable<Greeting> greetings = greetingRepository.findAll();
        StringBuilder greetingStrings = new StringBuilder();
        greetings.forEach(greeting -> {
            greetingStrings.append(greeting.getText() + "\n");
        });

        return ResponseEntity.ok().body(greetingStrings.toString());
    }

    @PostMapping("/")
    public ResponseEntity<String> addGreeting(@RequestBody String text) {
        var greeting = new Greeting();
        greeting.setText(text);
        try {
            greetingRepository.save(greeting);
        } catch (IllegalArgumentException ex ) {
            return ResponseEntity.badRequest().body("Invalid Text");

        } catch(OptimisticLockingFailureException ex) {
            return ResponseEntity.internalServerError().body("Unexpected Error");
        }
        return ResponseEntity.ok().body("OK");
    }
    
}
