CREATE TABLE BOOKS (
    ID              NVARCHAR2(21)        NOT NULL,
    NAME            NVARCHAR2(255)       NOT NULL,
    AUTHOR          NVARCHAR2(255),
    CATEGORY        NVARCHAR2(100),
    PUBLISHED_YEAR  NUMBER(4),
    IS_AVAILABLE    NUMBER(1) DEFAULT 0,
    CONSTRAINT BOOKS_PK PRIMARY KEY(ID)
);

CREATE TABLE BORROWINGS (
    ID              NVARCHAR2(21)        NOT NULL,
    BOOK_ID         NVARCHAR2(21)        NOT NULL,
    USER_ID         NVARCHAR2(21)        NOT NULL,
    STATUS          NVARCHAR2(21),
    BORROWING_DATE  TIMESTAMP           DEFAULT CURRENT_TIMESTAMP,
    DUE_DATE        TIMESTAMP           NOT NULL,
    RETURNED_DATE   TIMESTAMP           NULL,
    CONSTRAINT BORROWINGS_PK PRIMARY KEY(ID),
    CONSTRAINT FK_BORROWINGS_BOOK FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(ID),
    CONSTRAINT FK_BORROWINGS_USER FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);

INSERT ALL
    INTO BOOKS (ID, NAME, AUTHOR, CATEGORY, PUBLISHED_YEAR, IS_AVAILABLE)
    VALUES ('B001', 'The Java Handbook', 'Patrick Naughton', 'Programming', 1996, 1)
    INTO BOOKS (ID, NAME, AUTHOR, CATEGORY, PUBLISHED_YEAR, IS_AVAILABLE)
    VALUES ('B002', 'Effective Java', 'Joshua Bloch', 'Programming', 2008, 1)
    INTO BOOKS (ID, NAME, AUTHOR, CATEGORY, PUBLISHED_YEAR, IS_AVAILABLE)
    VALUES ('B003', 'Clean Code', 'Robert C. Martin', 'Programming', 2008, 1)
    INTO BOOKS (ID, NAME, AUTHOR, CATEGORY, PUBLISHED_YEAR, IS_AVAILABLE)
    VALUES ('B004', 'The Pragmatic Programmer', 'Andrew Hunt', 'Programming', 1999, 1)
    INTO BOOKS (ID, NAME, AUTHOR, CATEGORY, PUBLISHED_YEAR, IS_AVAILABLE)
    VALUES ('B005', 'Design Patterns', 'Erich Gamma', 'Software Design', 1994, 1)
    INTO BOOKS (ID, NAME, AUTHOR, CATEGORY, PUBLISHED_YEAR, IS_AVAILABLE)
    VALUES ('B006', 'The Mythical Man-Month', 'Frederick P. Brooks Jr.', 'Software Engineering', 1975, 1)
    INTO BOOKS (ID, NAME, AUTHOR, CATEGORY, PUBLISHED_YEAR, IS_AVAILABLE)
    VALUES ('B007', 'Introduction to Algorithms', 'Thomas H. Cormen', 'Computer Science', 1990, 1)
    INTO BOOKS (ID, NAME, AUTHOR, CATEGORY, PUBLISHED_YEAR, IS_AVAILABLE)
    VALUES ('B008', 'Database System Concepts', 'Abraham Silberschatz', 'Database', 2001, 0)
    INTO BOOKS (ID, NAME, AUTHOR, CATEGORY, PUBLISHED_YEAR, IS_AVAILABLE)
    VALUES ('B009', 'Refactoring', 'Martin Fowler', 'Software Engineering', 1999, 0)
    INTO BOOKS (ID, NAME, AUTHOR, CATEGORY, PUBLISHED_YEAR, IS_AVAILABLE)
    VALUES ('B010', 'Head First Design Patterns', 'Eric Freeman', 'Software Design', 2004, 0)
SELECT 1 FROM DUAL;

INSERT ALL
    INTO BORROWINGS (ID, BOOK_ID, USER_ID, STATUS, BORROWING_DATE, DUE_DATE, RETURNED_DATE)
    VALUES ('BR001', 'B001', 'U001', 'RETURNED', CURRENT_TIMESTAMP - INTERVAL '10' DAY, CURRENT_TIMESTAMP - INTERVAL '3' DAY, CURRENT_TIMESTAMP - INTERVAL '2' DAY)
    INTO BORROWINGS (ID, BOOK_ID, USER_ID, STATUS, BORROWING_DATE, DUE_DATE, RETURNED_DATE)
    VALUES ('BR002', 'B002', 'U002', 'RETURNED', CURRENT_TIMESTAMP - INTERVAL '10' DAY, CURRENT_TIMESTAMP - INTERVAL '3' DAY, CURRENT_TIMESTAMP - INTERVAL '2' DAY)
    INTO BORROWINGS (ID, BOOK_ID, USER_ID, STATUS, BORROWING_DATE, DUE_DATE, RETURNED_DATE)
    VALUES ('BR003', 'B003', 'U003', 'RETURNED', CURRENT_TIMESTAMP - INTERVAL '10' DAY, CURRENT_TIMESTAMP - INTERVAL '3' DAY, CURRENT_TIMESTAMP - INTERVAL '2' DAY)
    INTO BORROWINGS (ID, BOOK_ID, USER_ID, STATUS, BORROWING_DATE, DUE_DATE, RETURNED_DATE)
    VALUES ('BR004', 'B004', 'U001', 'RETURNED', CURRENT_TIMESTAMP - INTERVAL '15' DAY, CURRENT_TIMESTAMP - INTERVAL '7' DAY, CURRENT_TIMESTAMP - INTERVAL '5' DAY)
    INTO BORROWINGS (ID, BOOK_ID, USER_ID, STATUS, BORROWING_DATE, DUE_DATE, RETURNED_DATE)
    VALUES ('BR005', 'B005', 'U002', 'RETURNED', CURRENT_TIMESTAMP - INTERVAL '15' DAY, CURRENT_TIMESTAMP - INTERVAL '7' DAY, CURRENT_TIMESTAMP - INTERVAL '5' DAY)
    INTO BORROWINGS (ID, BOOK_ID, USER_ID, STATUS, BORROWING_DATE, DUE_DATE, RETURNED_DATE)
    VALUES ('BR006', 'B006', 'U003', 'RETURNED', CURRENT_TIMESTAMP - INTERVAL '10' DAY, CURRENT_TIMESTAMP - INTERVAL '3' DAY, CURRENT_TIMESTAMP - INTERVAL '2' DAY)
    INTO BORROWINGS (ID, BOOK_ID, USER_ID, STATUS, BORROWING_DATE, DUE_DATE, RETURNED_DATE)
    VALUES ('BR007', 'B007', 'U001', 'RETURNED', CURRENT_TIMESTAMP - INTERVAL '15' DAY, CURRENT_TIMESTAMP - INTERVAL '7' DAY, CURRENT_TIMESTAMP - INTERVAL '5' DAY)
    INTO BORROWINGS (ID, BOOK_ID, USER_ID, STATUS, BORROWING_DATE, DUE_DATE, RETURNED_DATE)
    VALUES ('BR008', 'B008', 'U002', 'BORROWED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '7' DAY, NULL)
    INTO BORROWINGS (ID, BOOK_ID, USER_ID, STATUS, BORROWING_DATE, DUE_DATE, RETURNED_DATE)
    VALUES ('BR009', 'B009', 'U003', 'BORROWED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '7' DAY, NULL)
    INTO BORROWINGS (ID, BOOK_ID, USER_ID, STATUS, BORROWING_DATE, DUE_DATE, RETURNED_DATE)
    VALUES ('BR010', 'B010', 'U001', 'BORROWED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '7' DAY, NULL)
SELECT 1 FROM DUAL;


