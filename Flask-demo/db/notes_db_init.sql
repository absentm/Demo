/*
Sqlite3 for NOTES.
*/
-- --------------------------------
-- Table structure for note
-- --------------------------------
DROP TABLE IF EXISTS note;
CREATE TABLE "note" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "title" TEXT,
  "content" TEXT,
  "create_time" TEXT,
  "update_time" TEXT,
  "username" TEXT
);

-- --------------------------------
-- Table structure for user
-- --------------------------------
DROP TABLE IF EXISTS user;
CREATE TABLE "user" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "username" TEXT,
  "password" TEXT,
  "nickname" TEXT,
  "email" TEXT,
  "phone" TEXT,
  "remark" TEXT
);
