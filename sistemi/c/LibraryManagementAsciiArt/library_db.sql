-- =================================================+
-- LIBRARY DATABASE - SQLite Version for C Programs |
-- =================================================+

-- =================================================+
--  1. AUTHORS TABLE                                |
-- =================================================+
-- stores info about AUTHORS

IF OBJECT_ID(N'dbo.authors', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.authors (
        id INT PRIMARY KEY IDENTITY(1,1),       -- Auto-incrementing ID
        name NVARCHAR(255) NOT NULL,
        surname NVARCHAR(255) NOT NULL,
        birth_year INT,
        country NVARCHAR(255)
    );
END

-- =================================================+
--  2. BOOKS TABLE                                  |
-- =================================================+
-- stores info about BOOKS

IF OBJECT(N'dbo.books', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.books (
        title NVARCHAR(255) NOT NULL,
        publication_year INT,
        genre NVARCHAR(255),
        isbn NVARCHAR(20) UNIQUE,
        author_id INT,
        FOREIGN KEY (author_id) REFERENCES dbo.authors(id)      -- Link to AUTHORS table
    );
END

-- =================================================+
-- 3. MEMBERS TABLE                                |
-- =================================================+
-- stores info about MEMBERS

IF OBJECT_ID(N'dbo.members', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.members (
        id INT PRIMARY KEY IDENTITY(1,1),       -- Auto-incrementing ID
        name NVARCHAR(255) NOT NULL,
        surname NVARCHAR(255) NOT NULL,
        USER_NAME NVARCHAR(255) UNIQUE,         -- Unique username for login
        PASSWORD NVARCHAR(255) NOT NULL,        -- Password for login
        membership_date DATE
    );
END

-- =================================================+
-- 4. LOANS TABLE                                  |
-- =================================================+
-- stores info about LOANS

IF OBJECT_ID(N'dbo.loans', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.loans (
        id INT PRIMARY KEY IDENTITY(1,1),                       -- Auto-incrementing ID
        book_id INT,
        member_id INT,
        loan_date DATE,                                         -- borrow date
        return_date DATE,                                       -- return date
        FOREIGN KEY (book_id) REFERENCES dbo.books(id),         -- Link to BOOKS table
        FOREIGN KEY (member_id) REFERENCES dbo.members(id)      -- Link to MEMBERS table
    );
END


-- =================================================+
-- SAMPLE DATA INSERTION                            |
-- =================================================+
-- for testing purposes

INSERT INTO dbo.authors(name, surname, birth_year, country)
    VALUES
    ('J.K.', 'Rowling', 1965, 'United Kingdom'),
    ('George', 'Orwell', 1903, 'United Kingdom'),
    ('Jane', 'Austen', 1775, 'United Kingdom'),
    ('Fedor', 'Dostoevsky', 1821, 'Russia'),
    ('Franz', 'Kafka', 1883, 'Austria-Hungary');

INSERT INTO dbo.books(title, publication_year, genre, isbn, author_id)
    VALUES
    ('Harry Potter and the Sorcerer''s Stone', 1997, 'Fantasy', '978-0439708180', 1),
    ('1984', 1949, 'Dystopian', '978-0451524935', 2),
    ('Pride and Prejudice', 1813, 'Romance', '978-1503290563', 3),
    ('Crime and Punishment', 1866, 'Psychological Fiction', '978-0486415871', 4),
    ('The Metamorphosis', 1915, 'Absurdist Fiction', '978-0553213690', 5),
    ('Lettera al padre', 1919, 'Epistolary Novel', '978-0553213706', 5);

INSERT INTO dbo.members(name, surname, membership_date, USER_NAME, PASSWORD)
    VALUES
    ('Giorgia', 'Folloni', '2026-02-08', 'girlBoss', 'admin123');           -- I am the only member until now, i'm my own boss yay