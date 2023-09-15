ALTER TABLE topic_response
ADD COLUMN activo tinyint;

UPDATE topic_response
SET activo = 1;
