ALTER TABLE cursos
ADD COLUMN activo tinyint;

UPDATE cursos
SET activo = 1;
