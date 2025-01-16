package com.enviro.assessment.grad001.ntobekomalinga.repository;

import com.enviro.assessment.grad001.ntobekomalinga.model.WasteRecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteRecyclingTipRepository extends JpaRepository<WasteRecyclingTip, Long> {

    public List<WasteRecyclingTip> findByWasteCategoryCode(String code);
}
