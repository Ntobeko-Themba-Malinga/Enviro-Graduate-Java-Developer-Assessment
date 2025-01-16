package com.enviro.assessment.grad001.ntobekomalinga.service;

import com.enviro.assessment.grad001.ntobekomalinga.model.WasteCategory;
import com.enviro.assessment.grad001.ntobekomalinga.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {

    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    public List<WasteCategory> getAllWasteCategories() {
        return this.wasteCategoryRepository.findAll();
    }

    public WasteCategory getWasteCategoryById(long id) {
        Optional<WasteCategory> wasteCategoryOptional = this.wasteCategoryRepository.findById(id);

        if (wasteCategoryOptional.isPresent()) {
            return wasteCategoryOptional.get();
        } else {
            throw new IllegalArgumentException(
                    String.format("Waste category with id '%d' was not found", id)
            );
        }
    }

    public WasteCategory saveWasteCategory(WasteCategory wasteCategory) {
        return this.wasteCategoryRepository.save(wasteCategory);
    }

    public WasteCategory updateWasteCategory(long id, WasteCategory newWasteCategory) {
        Optional<WasteCategory> wasteCategoryOptional = this.wasteCategoryRepository.findById(id);

        if (wasteCategoryOptional.isPresent()) {
            WasteCategory wasteCategory = wasteCategoryOptional.get();
            wasteCategory.setContent(newWasteCategory);
            wasteCategory = this.wasteCategoryRepository.save(wasteCategory);
            return wasteCategory;
        } else {
            throw new IllegalArgumentException(
                    String.format("Waste category with id '%d' doesn't exist", id)
            );
        }
    }

    public void deleteWasteCategoryById(long id) {
        Optional<WasteCategory> wasteCategoryOptional = this.wasteCategoryRepository.findById(id);

        if (wasteCategoryOptional.isPresent()) {
            this.wasteCategoryRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException(
                    String.format("Waste category with id '%d' doesn't exist", id)
            );
        }
    }
}
