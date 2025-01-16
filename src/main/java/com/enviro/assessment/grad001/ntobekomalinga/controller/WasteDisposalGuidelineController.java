package com.enviro.assessment.grad001.ntobekomalinga.controller;

import com.enviro.assessment.grad001.ntobekomalinga.model.WasteDisposalGuideline;
import com.enviro.assessment.grad001.ntobekomalinga.model.WasteRecyclingTip;
import com.enviro.assessment.grad001.ntobekomalinga.service.WasteDisposalGuidelineService;
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
@RequestMapping(path = "/waste-disposal-guideline")
public class WasteDisposalGuidelineController {

    @Autowired
    private WasteDisposalGuidelineService wasteDisposalGuidelineService;

    @GetMapping
    public ResponseEntity<List<WasteDisposalGuideline>> getAllWasteDisposalGuidelines(@RequestParam(value = "wasteCategoryCode", required = false) String wasteCategoryCode) {
        List<WasteDisposalGuideline> wasteDisposalGuidelines;
        if (wasteCategoryCode != null) {
            wasteDisposalGuidelines = this.wasteDisposalGuidelineService.getWasteDisposalGuidelineByCategory(wasteCategoryCode);
        } else {
            wasteDisposalGuidelines = this.wasteDisposalGuidelineService.getAllWasteDisposalGuidelines();
        }
        return new ResponseEntity<>(wasteDisposalGuidelines, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WasteDisposalGuideline> getSingleWasteDisposalGuideline(@PathVariable long id) {
        try {
            WasteDisposalGuideline wasteDisposalGuideline = this.wasteDisposalGuidelineService.getWasteDisposalGuidelineById(id);
            return new ResponseEntity<>(wasteDisposalGuideline, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        }
    }

    @PostMapping
    public ResponseEntity<WasteDisposalGuideline> saveWasteDisposalGuideline(@Valid @RequestBody WasteDisposalGuideline wasteDisposalGuideline, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                WasteDisposalGuideline savedGuideline = this.wasteDisposalGuidelineService.saveWasteDisposalGuideline(wasteDisposalGuideline);
                return new ResponseEntity<>(savedGuideline, HttpStatus.CREATED);
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        e.getMessage()
                );
            }
        } else {
            String errorMessage = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<WasteDisposalGuideline> updateWasteDisposalGUideline(@PathVariable long id, @RequestBody WasteDisposalGuideline newGuideline) {
        try {
            WasteDisposalGuideline updatedGuideline = this.wasteDisposalGuidelineService.updateWasteDisposalGuideline(id, newGuideline);
            return new ResponseEntity<>(updatedGuideline, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteWasteDisposalGuideline(@PathVariable long id) {
        try {
            this.wasteDisposalGuidelineService.deleteWasteDisposalGuideline(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }
}
