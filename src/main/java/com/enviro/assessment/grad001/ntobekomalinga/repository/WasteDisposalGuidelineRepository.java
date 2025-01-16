package com.enviro.assessment.grad001.ntobekomalinga.repository;

import com.enviro.assessment.grad001.ntobekomalinga.model.WasteDisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteDisposalGuidelineRepository extends JpaRepository<WasteDisposalGuideline, Long> {

    public List<WasteDisposalGuideline> findByWasteCategoryCode(String code);
}
