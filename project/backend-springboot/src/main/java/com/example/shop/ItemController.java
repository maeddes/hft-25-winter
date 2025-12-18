package com.example.shop;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemController {

    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/hostname")
    public String getHostname() throws UnknownHostException {
        String hostname = System.getenv("HOSTNAME");
        if (hostname == null) {
            hostname = InetAddress.getLocalHost().getHostName();
        }
        return hostname;
    }

    @GetMapping("/debug")
    public String debug() throws UnknownHostException{

        return this.getHostname() + " contains elements: " + this.list().toString();
    }
    

    // POST: create or add quantity
    @PostMapping
    public ResponseEntity<Item> createOrAdd(@RequestBody Item input) {
        Item existing = repository.findById(input.getName()).orElse(null);
        if (existing == null) {
            Item saved = repository.save(input);
            return ResponseEntity.status(201).body(saved);
        } else {
            existing.setQuantity(existing.getQuantity() + input.getQuantity());
            repository.save(existing);
            return ResponseEntity.ok(existing);
        }
    }

    // PUT: replace quantity
    @PutMapping("/{name}")
    public ResponseEntity<Item> update(@PathVariable String name, @RequestBody Item input) {
        return repository.findById(name)
                .map(item -> {
                    item.setQuantity(input.getQuantity());
                    repository.save(item);
                    return ResponseEntity.ok(item);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // GET single
    @GetMapping("/{name}")
    public ResponseEntity<Item> getOne(@PathVariable String name) {
        return repository.findById(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET all
    @GetMapping
    public List<Item> list() {
        return repository.findAll();
    }

    // DELETE
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        return repository.findById(name)
                .map(item -> {
                    repository.delete(item);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
