INSERT INTO WasteCategory (Code, Description) VALUES
('PAPER', 'Paper and Cardboard'),
('PLASTIC', 'Plastic Materials'),
('GLASS', 'Glass Materials'),
('METAL', 'Metal and Aluminum'),
('ORGANIC', 'Organic Waste');

INSERT INTO WasteRecyclingTips (WasteCategoryId, Tip) VALUES
(1, 'Remove staples and paper clips before recycling paper.'),
(1, 'Flatten cardboard boxes to save space.'),
(2, 'Rinse plastic containers to avoid contamination.'),
(2, 'Check the recycling number on plastics before disposal.'),
(3, 'Separate glass by color if required by local guidelines.'),
(3, 'Avoid recycling broken glass in household bins.'),
(4, 'Clean metal cans to remove food residue.'),
(4, 'Avoid mixing metals with other materials.'),
(5, 'Compost organic waste for use as fertilizer.'),
(5, 'Avoid placing non-compostable items in organic bins.');


INSERT INTO WasteDisposalGuidelines (WasteCategoryId, Guideline) VALUES
(1, 'Do not recycle wax-coated or greasy paper products.'),
(1, 'Recycle newspapers, magazines, and office paper.'),
(2, 'Avoid recycling plastic bags in household bins.'),
(2, 'Recycle plastic bottles with caps removed.'),
(3, 'Recycle glass jars and bottles only.'),
(3, 'Do not recycle ceramics or mirrors with glass.'),
(4, 'Recycle aluminum cans and tin containers.'),
(4, 'Avoid recycling heavily rusted metal items.'),
(5, 'Use a compost bin for organic waste.'),
(5, 'Do not mix meat or dairy products with compost.');
