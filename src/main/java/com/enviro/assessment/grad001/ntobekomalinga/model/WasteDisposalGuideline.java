package com.enviro.assessment.grad001.ntobekomalinga.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "WasteDisposalGuidelines")
public class WasteDisposalGuideline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WasteDisposalGuidelineId")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private long id;
    @Column(name = "Guideline")
    @NotBlank(message = "guideline is required")
    private String guideline;
    @ManyToOne
    @JoinColumn(name = "WasteCategoryId", nullable = false)
    @NotNull(message = "WasteCategory id is required")
    @Schema(example = "{ \"id\": 1 }")
    private WasteCategory wasteCategory;

    public WasteDisposalGuideline() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public WasteCategory getWasteCategory() {
        return wasteCategory;
    }

    public void setWasteCategory(WasteCategory wasteCategory) {
        this.wasteCategory = wasteCategory;
    }

    @Hidden
    public void setContent(WasteDisposalGuideline wasteDisposalGuideline) {
        if (wasteDisposalGuideline.guideline != null && !wasteDisposalGuideline.guideline.isBlank()) {
            this.guideline = wasteDisposalGuideline.guideline;
        }
    }

    @Override
    public String toString() {
        return "WasteDisposalGuideline{" +
                "id=" + id +
                ", guideline='" + guideline + '\'' +
                ", wasteCategory=" + wasteCategory +
                '}';
    }
}
