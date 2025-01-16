package com.enviro.assessment.grad001.ntobekomalinga.service;

import com.enviro.assessment.grad001.ntobekomalinga.model.WasteCategory;
import com.enviro.assessment.grad001.ntobekomalinga.model.WasteDisposalGuideline;
import com.enviro.assessment.grad001.ntobekomalinga.model.WasteRecyclingTip;
import com.enviro.assessment.grad001.ntobekomalinga.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.ntobekomalinga.repository.WasteDisposalGuidelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteDisposalGuidelineService {

    @Autowired
    private WasteDisposalGuidelineRepository wasteDisposalGuidelineRepository;
    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    public List<WasteDisposalGuideline> getAllWasteDisposalGuidelines() {
        return this.wasteDisposalGuidelineRepository.findAll();
    }

    public List<WasteDisposalGuideline> getWasteDisposalGuidelineByCategory(String wasteCategoryCode) {
        return this.wasteDisposalGuidelineRepository.findByWasteCategoryCode(wasteCategoryCode);
    }

    public WasteDisposalGuideline getWasteDisposalGuidelineById(long id) {
        Optional<WasteDisposalGuideline> wasteDisposalGuideline = this.wasteDisposalGuidelineRepository.findById(id);

        if (wasteDisposalGuideline.isPresent()) {
            return  wasteDisposalGuideline.get();
        } else {
            throw new IllegalArgumentException(String.format("Waste recycling tip with id '%d' was not found", id));
        }
    }

    public WasteDisposalGuideline saveWasteDisposalGuideline(WasteDisposalGuideline wasteDisposalGuideline) {
        Optional<WasteCategory> wasteCategoryOptional = this.wasteCategoryRepository.findById(wasteDisposalGuideline.getWasteCategory().getId());

        if (wasteCategoryOptional.isPresent()) {
            return this.wasteDisposalGuidelineRepository.save(wasteDisposalGuideline);
        } else {
            throw new IllegalArgumentException(
                    String.format("Waste category with id '%d' doesn't exist", wasteDisposalGuideline.getWasteCategory().getId())
            );
        }
    }

    public WasteDisposalGuideline updateWasteDisposalGuideline(long id, WasteDisposalGuideline newGuideline) {
        Optional<WasteDisposalGuideline> wasteDisposalGuidelineOptional = this.wasteDisposalGuidelineRepository.findById(id);

        if (wasteDisposalGuidelineOptional.isPresent()) {
            WasteDisposalGuideline guideline = wasteDisposalGuidelineOptional.get();
            guideline.setContent(newGuideline);
            guideline = this.wasteDisposalGuidelineRepository.save(guideline);
            return guideline;
        } else {
            throw new IllegalArgumentException(
                    String.format("Waste disposal guideline with id '%d' doesn't exist", id)
            );
        }
    }

    public void deleteWasteDisposalGuideline(long id) {
        Optional<WasteDisposalGuideline> wasteDisposalGuideline = this.wasteDisposalGuidelineRepository.findById(id);

        if (wasteDisposalGuideline.isPresent()) {
            this.wasteDisposalGuidelineRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException(
                    String.format("Waste disposal guideline with id '%d' doesn't exist", id)
            );
        }
    }
}
