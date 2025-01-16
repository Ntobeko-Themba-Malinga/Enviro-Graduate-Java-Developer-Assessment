package com.enviro.assessment.grad001.ntobekomalinga.service;

import com.enviro.assessment.grad001.ntobekomalinga.model.WasteCategory;
import com.enviro.assessment.grad001.ntobekomalinga.model.WasteRecyclingTip;
import com.enviro.assessment.grad001.ntobekomalinga.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.ntobekomalinga.repository.WasteRecyclingTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteRecyclingTipService {
    @Autowired
    private WasteRecyclingTipRepository wasteRecyclingTipRepository;
    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    public List<WasteRecyclingTip> getAllWasteRecyclingTips() {
        return this.wasteRecyclingTipRepository.findAll();
    }

    public List<WasteRecyclingTip> getWasteRecyclingTipByCategory(String wasteCategoryCode) {
        return this.wasteRecyclingTipRepository.findByWasteCategoryCode(wasteCategoryCode);
    }

    public WasteRecyclingTip getWasteRecyclingTipById(long id) {
        Optional<WasteRecyclingTip> wasteRecyclingTipOptional = this.wasteRecyclingTipRepository.findById(id);

        if (wasteRecyclingTipOptional.isPresent()) {
            return wasteRecyclingTipOptional.get();
        } else {
            throw new IllegalArgumentException(
                    String.format("Waste recycling tip with id '%d' was not found", id)
            );
        }
    }

    public WasteRecyclingTip saveWasteRecyclingTip(WasteRecyclingTip wasteRecyclingTip) {
        Optional<WasteCategory> wasteCategoryOptional = this.wasteCategoryRepository.findById(wasteRecyclingTip.getWasteCategory().getId());

        if (wasteCategoryOptional.isPresent()) {
            return this.wasteRecyclingTipRepository.save(wasteRecyclingTip);
        } else {
            throw new IllegalArgumentException(
                    String.format("Waste category with id '%d' doesn't exist", wasteRecyclingTip.getWasteCategory().getId())
            );
        }
    }

    public WasteRecyclingTip updateWasteRecyclingTip(long id, WasteRecyclingTip newWasteRecyclingTip) {
        Optional<WasteRecyclingTip> wasteRecyclingTipOptional = this.wasteRecyclingTipRepository.findById(id);

        if (wasteRecyclingTipOptional.isPresent()) {
            WasteRecyclingTip tip = wasteRecyclingTipOptional.get();
            tip.setContent(newWasteRecyclingTip);
            tip = this.wasteRecyclingTipRepository.save(tip);
            return tip;
        } else {
            throw new IllegalArgumentException(
                    String.format("Waste recycling tip with id '%d' doesn't exist", id)
            );
        }
    }

    public void deleteWasteRecyclingTip(long id) {
        Optional<WasteRecyclingTip> wasteRecyclingTip = this.wasteRecyclingTipRepository.findById(id);

        if (wasteRecyclingTip.isPresent()) {
            this.wasteRecyclingTipRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException(
                    String.format("Waste recycling tip with id '%d' doesn't exist", id)
            );
        }
    }
}
