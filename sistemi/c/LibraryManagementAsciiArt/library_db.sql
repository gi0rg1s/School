-- =================================================+
-- LIBRARY DATABASE - SQLite Version for C Programs |
-- =================================================+

-- =================================================+
--  1. AUTHORS TABLE                                |
-- =================================================+
-- stores info about AUTHORS

CREATE TABLE IF NOT EXISTS authors (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    birth_year INTEGER,
    country TEXT
);


-- =================================================+
--  2. BOOKS TABLE                                  |
-- =================================================+
-- stores info about BOOKS

CREATE TABLE IF NOT EXISTS books (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    publication_year INTEGER,
    genre TEXT,
    isbn TEXT UNIQUE,
    author_id INTEGER,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

-- =================================================+
-- 3. MEMBERS TABLE                                |
-- =================================================+
-- stores info about MEMBERS

CREATE TABLE IF NOT EXISTS members (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    USER_NAME TEXT UNIQUE,
    PASSWORD TEXT NOT NULL,
    membership_date DATE
);

-- =================================================+
-- 4. LOANS TABLE                                  |
-- =================================================+
-- stores info about LOANS

CREATE TABLE IF NOT EXISTS loans (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    book_id INTEGER,
    member_id INTEGER,
    loan_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members(id)
);


-- =================================================+
-- SAMPLE DATA INSERTION                            |
-- =================================================+
-- for testing purposes

INSERT INTO authors(name, surname, birth_year, country)
    VALUES
    ('J.K.', 'Rowling', 1965, 'United Kingdom'),
    ('George', 'Orwell', 1903, 'United Kingdom'),
    ('Jane', 'Austen', 1775, 'United Kingdom'),
    ('Fedor', 'Dostoevsky', 1821, 'Russia'),
    ('Franz', 'Kafka', 1883, 'Austria-Hungary'),
    ('Gabriel', 'García Márquez', 1927, 'Colombia'),
    ('Leo', 'Tolstoy', 1828, 'Russia'),
    ('Virginia', 'Woolf', 1882, 'United Kingdom'),
    ('Ernest', 'Hemingway', 1899, 'United States'),
    ('Haruki', 'Murakami', 1949, 'Japan');

INSERT INTO books(title, publication_year, genre, isbn, author_id)
    VALUES
    ('Harry Potter and the Sorcerer''s Stone', 1997, 'Fantasy', '978-0439708180', 1),
    ('Harry Potter and the Chamber of Secrets', 1998, 'Fantasy', '978-0439064873', 1),
    ('Harry Potter and the Prisoner of Azkaban', 1999, 'Fantasy', '978-0439136365', 1),
    ('1984', 1949, 'Dystopian', '978-0451524935', 2),
    ('Animal Farm', 1945, 'Political Satire', '978-0451526342', 2),
    ('Pride and Prejudice', 1813, 'Romance', '978-1503290563', 3),
    ('Sense and Sensibility', 1811, 'Romance', '978-0141439662', 3),
    ('Crime and Punishment', 1866, 'Psychological Fiction', '978-0486415871', 4),
    ('The Brothers Karamazov', 1880, 'Philosophical Fiction', '978-0374528379', 4),
    ('The Metamorphosis', 1915, 'Absurdist Fiction', '978-0553213690', 5),
    ('Lettera al padre', 1919, 'Epistolary Novel', '978-0553213706', 5),
    ('The Trial', 1925, 'Absurdist Fiction', '978-0805209990', 5),
    ('One Hundred Years of Solitude', 1967, 'Magical Realism', '978-0060883287', 6),
    ('Love in the Time of Cholera', 1985, 'Romance', '978-0307389732', 6),
    ('War and Peace', 1869, 'Historical Fiction', '978-0199232765', 7),
    ('Anna Karenina', 1878, 'Realist Fiction', '978-0143035008', 7),
    ('Mrs Dalloway', 1925, 'Modernist Fiction', '978-0156628709', 8),
    ('To the Lighthouse', 1927, 'Modernist Fiction', '978-0156907392', 8),
    ('The Old Man and the Sea', 1952, 'Literary Fiction', '978-0684801223', 9),
    ('A Farewell to Arms', 1929, 'War Fiction', '978-0684801469', 9),
    ('Norwegian Wood', 1987, 'Romance', '978-0375704024', 10),
    ('Kafka on the Shore', 2002, 'Magical Realism', '978-1400079278', 10);

INSERT INTO members(name, surname, membership_date, USER_NAME, PASSWORD)
    VALUES
    ('Giorgia', 'Folloni', '2026-02-08', 'girlBoss', 'admin123');

-- =================================================+
-- 5. LOANS SAMPLE DATA                             |
-- =================================================+
-- Assign some books to members (borrowed books)

INSERT INTO loans(book_id, member_id, loan_date, return_date)
    VALUES
    (1, 1, '2026-02-10', '2026-03-10'),  -- Giorgia borrowed Harry Potter 1
    (4, 1, '2026-02-12', '2026-03-12'),  -- Giorgia borrowed 1984
    (8, 1, '2026-02-15', NULL);          -- Giorgia borrowed Crime and Punishment (no return date yet)
