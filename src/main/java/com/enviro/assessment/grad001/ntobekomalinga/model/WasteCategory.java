package com.enviro.assessment.grad001.ntobekomalinga.model;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "WasteCategory")
public class WasteCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WasteCategoryId")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private long id;
    @Column(name = "Code")
    @NotBlank(message = "code is required.")
    private String code;
    @Column(name = "Description")
    @NotBlank(message = "description is required.")
    private String description;

    public WasteCategory() {
    }

    public WasteCategory(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Hidden
    public void setContent(WasteCategory wasteCategory) {
        if (wasteCategory.code != null && !wasteCategory.code.isBlank()) {
            this.code = wasteCategory.code;
        }

        if (wasteCategory.description != null && !wasteCategory.description.isBlank()) {
            this.description = wasteCategory.description;
        }
    }

    @Override
    public String toString() {
        return "WasteCategory{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
