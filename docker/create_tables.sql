CREATE SCHEMA "restful-web-services" AUTHORIZATION pg_database_owner;

-- create user table
CREATE TABLE "restful-web-services"."user" (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NOT NULL,
	birth_date date NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);

-- create post table
CREATE TABLE "restful-web-services".post (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	description varchar NOT NULL,
	user_id int8 NOT NULL
);

-- add foreign key for user table to post table
ALTER TABLE "restful-web-services".post
    ADD CONSTRAINT fk_post_user
    FOREIGN KEY (user_id)
    REFERENCES "restful-web-services"."user" (id);

-- populate users
INSERT INTO "restful-web-services"."user" ("name", birth_date)
    VALUES
        ('John', '2007-03-18'),
        ('Jack', '1993-11-26'),
        ('Jill', '2012-12-01');

-- populate posts
INSERT INTO "restful-web-services".post (description, user_id)
    VALUES
        ('I want to learn AWS', 1),
        ('I want to learn Java', 2),
        ('I do not want to learn', 3),
        ('I want to learn Python', 1);