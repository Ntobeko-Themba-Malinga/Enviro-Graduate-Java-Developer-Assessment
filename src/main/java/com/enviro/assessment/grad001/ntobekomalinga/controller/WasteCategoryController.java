package com.enviro.assessment.grad001.ntobekomalinga.controller;

import com.enviro.assessment.grad001.ntobekomalinga.model.WasteCategory;
import com.enviro.assessment.grad001.ntobekomalinga.repository.WasteCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/waste-category")
public class WasteCategoryController {

    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    @GetMapping
    public ResponseEntity<List<WasteCategory>> getAllWasteCategories() {
        List<WasteCategory> wasteCategories = this.wasteCategoryRepository.findAll();
        return new ResponseEntity<>(wasteCategories, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WasteCategory> getSingleWasteCategoryById(@PathVariable long id) {
        Optional<WasteCategory> wasteCategoryOptional = this.wasteCategoryRepository.findById(id);

        if (wasteCategoryOptional.isPresent()) {
            return new ResponseEntity<>(wasteCategoryOptional.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Waste category with id '%d' was not found", id)
            );
        }
    }

    @PostMapping
    public ResponseEntity<WasteCategory> saveWasteCategory(@Valid @RequestBody WasteCategory wasteCategory, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            WasteCategory savedWasteCategory = wasteCategoryRepository.save(wasteCategory);
            return new ResponseEntity<>(savedWasteCategory, HttpStatus.CREATED);
        } else {
            String errorMessage = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<WasteCategory> updateWasteCategory(@PathVariable long id, @RequestBody WasteCategory newWasteCategory) {
        Optional<WasteCategory> wasteCategoryOptional = this.wasteCategoryRepository.findById(id);

        if (wasteCategoryOptional.isPresent()) {
            WasteCategory wasteCategory = wasteCategoryOptional.get();
            wasteCategory.setContent(newWasteCategory);
            wasteCategory = this.wasteCategoryRepository.save(wasteCategory);
            return new ResponseEntity<>(wasteCategory, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Waste category with id '%d' doesn't exist", id)
            );
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteWasteCategoryById(@PathVariable long id) {
        this.wasteCategoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
