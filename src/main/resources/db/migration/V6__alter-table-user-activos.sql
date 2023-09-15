ALTER TABLE users
ADD COLUMN activo tinyint;

UPDATE users
SET activo = 1;
