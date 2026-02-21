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
    rented_books BOOKS[],
    FOREIGN KEY (rented_books) REFERENCES books(id),
    my_books BOOKS[],
    FOREIGN KEY (my_books) REFERENCES books(id)
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
    ('Franz', 'Kafka', 1883, 'Austria-Hungary');

INSERT INTO books(title, publication_year, genre, isbn, author_id)
    VALUES
    ('Harry Potter and the Sorcerer''s Stone', 1997, 'Fantasy', '978-0439708180', 1),
    ('1984', 1949, 'Dystopian', '978-0451524935', 2),
    ('Pride and Prejudice', 1813, 'Romance', '978-1503290563', 3),
    ('Crime and Punishment', 1866, 'Psychological Fiction', '978-0486415871', 4),
    ('The Metamorphosis', 1915, 'Absurdist Fiction', '978-0553213690', 5),
    ('Lettera al padre', 1919, 'Epistolary Novel', '978-0553213706', 5);

INSERT INTO members(name, surname, membership_date, USER_NAME, PASSWORD)
    VALUES
    ('Giorgia', 'Folloni', '2026-02-08', 'girlBoss', 'admin123');