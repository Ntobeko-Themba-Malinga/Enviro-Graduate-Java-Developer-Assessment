package com.enviro.assessment.grad001.ntobekomalinga.controller;

import com.enviro.assessment.grad001.ntobekomalinga.model.WasteCategory;
import com.enviro.assessment.grad001.ntobekomalinga.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/waste-category")
public class WasteCategoryController {

    @Autowired
    private WasteCategoryService wasteCategoryService;

    @GetMapping
    public ResponseEntity<List<WasteCategory>> getAllWasteCategories() {
        List<WasteCategory> wasteCategories = this.wasteCategoryService.getAllWasteCategories();
        return new ResponseEntity<>(wasteCategories, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WasteCategory> getSingleWasteCategoryById(@PathVariable long id) {
        try {
        WasteCategory wasteCategory = this.wasteCategoryService.getWasteCategoryById(id);
            return new ResponseEntity<>(wasteCategory, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        }
    }

    @PostMapping
    public ResponseEntity<WasteCategory> saveWasteCategory(@Valid @RequestBody WasteCategory wasteCategory, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            WasteCategory savedWasteCategory = this.wasteCategoryService.saveWasteCategory(wasteCategory);
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
        try {
            WasteCategory wasteCategory = this.wasteCategoryService.updateWasteCategory(id, newWasteCategory);
            return new ResponseEntity<>(wasteCategory, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteWasteCategoryById(@PathVariable long id) {
        try {
            this.wasteCategoryService.deleteWasteCategoryById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }
}
