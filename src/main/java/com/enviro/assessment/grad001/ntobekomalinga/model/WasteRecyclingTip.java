package com.enviro.assessment.grad001.ntobekomalinga.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "WasteRecyclingTips")
public class WasteRecyclingTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WasteRecyclingTipId")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private long id;
    @Column(name = "Tip")
    @NotBlank(message = "tip is required")
    private String tip;
    @ManyToOne
    @JoinColumn(name = "WasteCategoryId", nullable = false)
    @NotNull(message = "WasteCategory id is required")
    @Schema(example = "{ \"id\": 1 }")
    private WasteCategory wasteCategory;

    public WasteRecyclingTip() {
    }

    public WasteRecyclingTip(String tip, WasteCategory wasteCategory) {
        this.tip = tip;
        this.wasteCategory = wasteCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public WasteCategory getWasteCategory() {
        return wasteCategory;
    }

    public void setWasteCategory(WasteCategory wasteCategory) {
        this.wasteCategory = wasteCategory;
    }

    @Hidden
    public void setContent(WasteRecyclingTip wasteRecyclingTip) {
        if (wasteRecyclingTip.tip != null && !wasteRecyclingTip.tip.isBlank()) {
            this.tip = wasteRecyclingTip.tip;
        }
    }

    @Override
    public String toString() {
        return "WasteRecyclingTip{" +
                "id=" + id +
                ", tip='" + tip + '\'' +
                ", wasteCategory=" + wasteCategory +
                '}';
    }
}
