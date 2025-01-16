package com.enviro.assessment.grad001.ntobekomalinga.repository;

import com.enviro.assessment.grad001.ntobekomalinga.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
}
