CREATE TABLE WasteCategory (
    WasteCategoryId INT AUTO_INCREMENT PRIMARY KEY,
    Code VARCHAR NOT NULL,
    Description VARCHAR NOT NULL
);


CREATE TABLE WasteRecyclingTips (
    WasteRecyclingTipId INT AUTO_INCREMENT PRIMARY KEY,
    WasteCategoryId INT NOT NULL,
    Tip VARCHAR NOT NULL,
    CONSTRAINT FK_WasteRecyclingTips_WasteCategory
        FOREIGN KEY (WasteCategoryId) REFERENCES WasteCategory(WasteCategoryId)
        ON DELETE CASCADE
);


CREATE TABLE WasteDisposalGuidelines (
    WasteDisposalGuidelineId INT AUTO_INCREMENT PRIMARY KEY,
    WasteCategoryId INT NOT NULL,
    Guideline VARCHAR NOT NULL,
    CONSTRAINT FK_WasteDisposalGuidelines_WasteCategory
        FOREIGN KEY (WasteCategoryId) REFERENCES WasteCategory(WasteCategoryId)
        ON DELETE CASCADE
);
