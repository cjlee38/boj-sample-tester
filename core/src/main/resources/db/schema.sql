CREATE TABLE IF NOT EXISTS problem
(
    id      INTEGER PRIMARY KEY AUTOINCREMENT,
    number  TEXT NOT NULL UNIQUE,
    timeout INT  NOT NULL
);

CREATE TABLE IF NOT EXISTS sample
(
    id             INTEGER PRIMARY KEY AUTOINCREMENT,
    problem_number INTEGER NOT NULL,
    input          TEXT    NOT NULL,
    output         TEXT    NOT NULL
);
