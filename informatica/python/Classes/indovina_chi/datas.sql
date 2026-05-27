CREATE TABLE IF NOT EXISTS people (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nome TEXT NOT NULL,
  epoca TEXT NOT NULL,
  professione TEXT NOT NULL,
  nazionalita TEXT NOT NULL,
  genere TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS questions (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  testo TEXT NOT NULL,
  attributo TEXT NOT NULL,
  valore_atteso TEXT NOT NULL
);

INSERT INTO people (nome, epoca, professione, nazionalita, genere) VALUES
('Leonardo da Vinci', 'Rinascimento', 'Artista', 'Italiana', 'Maschio'),
('Cleopatra', 'Antichità', 'Regina', 'Egiziana', 'Femmina'),
('Albert Einstein', 'Età moderna', 'Fisico', 'Tedesca', 'Maschio'),
('Frida Kahlo', 'XX secolo', 'Artista', 'Messicana', 'Femmina'),
('William Shakespeare', 'Rinascimento', 'Drammaturgo', 'Inglese', 'Maschio'),
('Marie Curie', 'Età moderna', 'Scienziata', 'Polacca', 'Femmina'),
('Gengis Khan', 'Medioevo', 'Condottiero', 'Mongola', 'Maschio'),
('Giovanna d''Arco', 'Medioevo', 'Leader militare', 'Francese', 'Femmina'),
('Jane Austen', 'XIX secolo', 'Scrittore', 'Inglese', 'Femmina'),
('Isaac Newton', 'Età moderna', 'Fisico', 'Inglese', 'Maschio'),
('Angelina Mango', 'Contemporanea', 'Cantante', 'Italiana', 'Femmina'),
('Michael Jackson', 'XX secolo', 'Cantante', 'Americana', 'Maschio'),
('Franz Kafka', 'XX secolo', 'Scrittore', 'Ceca', 'Maschio'),
('Ada Lovelace', 'XIX secolo', 'Matematica', 'Inglese', 'Femmina'),
('Dostoevskij', 'XIX secolo', 'Scrittore', 'Russa', 'Maschio');

INSERT INTO questions (testo, attributo, valore_atteso) VALUES
('Il personaggio è un artista?', 'professione', 'Artista'),
('Il personaggio è uno scienziato?', 'professione', 'Scienziata'),
('Il personaggio è una regina?', 'professione', 'Regina'),
('Il personaggio è maschio?', 'genere', 'Maschio'),
('Il personaggio è femmina?', 'genere', 'Femmina'),
('Il personaggio è del XX secolo?', 'epoca', 'XX secolo'),
('Il personaggio è uno scrittore?', 'professione', 'Scrittore'),
('Il personaggio è inglese?', 'nazionalita', 'Inglese'),
('Il personaggio è un cantante?', 'professione', 'Cantante'),
('Il personaggio è dell''età moderna?', 'epoca', 'Età moderna'),
('Il personaggio è un matematico?', 'professione', 'Matematica'),
('Il personaggio è russo?', 'nazionalita', 'Russa'),
('Il personaggio è un leader militare?', 'professione', 'Leader militare'),
('Il personaggio è medievale?', 'epoca', 'Medioevo'),
('Il personaggio è contemporaneo?', 'epoca', 'Contemporanea'),
('Il personaggio è italiano?', 'nazionalita', 'Italiana'),
('Il personaggio è messicano?', 'nazionalita', 'Messicana'),
('Il personaggio è egiziano?', 'nazionalita', 'Egiziana'),
('Il personaggio è mongolo?', 'nazionalita', 'Mongola'),
('Il personaggio è del rinascimento?', 'epoca', 'Rinascimento'),
('Il personaggio è del XIX secolo?', 'epoca', 'XIX secolo');