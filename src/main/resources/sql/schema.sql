DROP TABLE files;
DROP TABLE comments;
DROP TABLE tags;
DROP TABLE documents;
DROP TABLE users;

create TABLE users (
userid NUMBER(14,0) PRIMARY KEY,
name VARCHAR2(256) NOT NULL,
email VARCHAR2(256) NOT NULL,
CONSTRAINT users_name_uk UNIQUE(name),
CONSTRAINT users_email_uk UNIQUE(email)
);

create table documents (
documentid NUMBER(14,0) PRIMARY KEY,
title VARCHAR2(256) NOT NULL,
createdOn TIMESTAMP NOT NULL,
creator NUMBER(14,0) NOT NULL,
CONSTRAINT documents_createdBy_fk FOREIGN KEY (creator) REFERENCES users(userid)
);

create table tags (
tagid NUMBER(14,0) PRIMARY KEY,
documentid  NUMBER(14,0) NOT NULL,
text VARCHAR2(256) NOT NULL,
CONSTRAINT tags_documentid_fk FOREIGN KEY (documentid) REFERENCES documents(documentid)
);

create table comments (
commentid NUMBER(14,0) PRIMARY KEY,
documentid NUMBER(14,0) NOT NULL,
userid NUMBER(14,0) NOT NULL,
text VARCHAR2(4000) NOT NULL,
CONSTRAINT comments_documentid_fk FOREIGN KEY (documentid) REFERENCES documents(documentid),
CONSTRAINT comments_userid_fk FOREIGN KEY (userid) REFERENCES users(userid)
);

create table files (
fileid NUMBER(14,0) PRIMARY KEY,
documentid NUMBER(14,0) NOT NULL,
path VARCHAR2(4000) NOT NULL,
CONSTRAINT files_path_uk UNIQUE(path),
CONSTRAINT files_documentid_fk FOREIGN KEY (documentid) REFERENCES documents(documentid)
);

insert into users values (1, 'Philipp Wolfbeisz', 'philipp@wolfbeisz.com');
commit;
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;