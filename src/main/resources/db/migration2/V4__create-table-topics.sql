CREATE TABLE topics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT,
    date DATETIME NOT NULL,
    status VARCHAR(50),
    autor_id INT,
    curso_id INT,
    FOREIGN KEY (autor_id) REFERENCES users (id),
    FOREIGN KEY (curso_id) REFERENCES cursos (id)
);

CREATE TABLE topic_response (

       id INT AUTO_INCREMENT PRIMARY KEY,
       message TEXT,
       fechaCreacion DATETIME NOT NULL,
       topic_id INT,
       autor_id INT,
       solucion BOOLEAN DEFAULT FALSE,
       FOREIGN KEY (topic_id) REFERENCES topics (id),
       FOREIGN KEY (autor_id) REFERENCES users (id)

);