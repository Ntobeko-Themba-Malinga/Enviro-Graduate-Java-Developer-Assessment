package com.enviro.assessment.grad001.ntobekomalinga.controller;

import com.enviro.assessment.grad001.ntobekomalinga.model.WasteRecyclingTip;
import com.enviro.assessment.grad001.ntobekomalinga.service.WasteRecyclingTipService;
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
@RequestMapping(path = "/waste-recycling-tips")
public class WasteRecyclingTipController {

    @Autowired
    private WasteRecyclingTipService wasteRecyclingTipService;

    @GetMapping
    public ResponseEntity<List<WasteRecyclingTip>> getAllWasteRecyclingTips(@RequestParam(value = "wasteCategoryCode", required = false) String wasteCategoryCode) {
        List<WasteRecyclingTip> wasteRecyclingTips;
        if (wasteCategoryCode != null) {
            wasteRecyclingTips = this.wasteRecyclingTipService.getWasteRecyclingTipByCategory(wasteCategoryCode);
        } else {
            wasteRecyclingTips = this.wasteRecyclingTipService.getAllWasteRecyclingTips();
        }
        return new ResponseEntity<>(wasteRecyclingTips, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WasteRecyclingTip> getSingleWasteRecyclingTipById(@PathVariable long id) {
        try {
            WasteRecyclingTip wasteRecyclingTip = this.wasteRecyclingTipService.getWasteRecyclingTipById(id);
            return new ResponseEntity<>(wasteRecyclingTip, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        }
    }

    @PostMapping
    public ResponseEntity<WasteRecyclingTip> saveWasteRecyclingTip(@Valid @RequestBody WasteRecyclingTip wasteRecyclingTip, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                WasteRecyclingTip savedWasteRecyclingTip = this.wasteRecyclingTipService.saveWasteRecyclingTip(wasteRecyclingTip);
                return new ResponseEntity<>(savedWasteRecyclingTip, HttpStatus.CREATED);
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
    public ResponseEntity<WasteRecyclingTip> updateWasteRecyclingTip(@PathVariable long id, @RequestBody WasteRecyclingTip newWasteRecyclingTip) {
        try {
            WasteRecyclingTip updatedTip = this.wasteRecyclingTipService.updateWasteRecyclingTip(id, newWasteRecyclingTip);
            return new ResponseEntity<>(updatedTip, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteWasteRecyclingTipById(@PathVariable long id) {
        try {
            this.wasteRecyclingTipService.deleteWasteRecyclingTip(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }
}
