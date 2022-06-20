package com.petfriendbackend.web;

import com.petfriendbackend.model.Pet;
import com.petfriendbackend.model.dto.PetDto;
import com.petfriendbackend.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/pets")
public class PetRestController {

    private final PetService petService;

    public PetRestController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<Pet> listPets(){
        return this.petService.listPets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> findById(@PathVariable Long id) {
        return this.petService.findById(id)
                .map(pet -> ResponseEntity.ok().body(pet))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Pet> save(@RequestBody PetDto petDto) {
        return this.petService.save(petDto)
                .map(pet -> ResponseEntity.ok().body(pet))
                .orElseGet(() ->  ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Pet> edit(@PathVariable Long id, @RequestBody PetDto petDto) {
        return this.petService.edit(id, petDto)
                .map(pet -> ResponseEntity.ok().body(pet))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.petService.deleteById(id);
        if(this.petService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
