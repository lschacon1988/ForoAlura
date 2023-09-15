ALTER TABLE topics
ADD COLUMN activo tinyint;

UPDATE topics
SET activo = 1;
