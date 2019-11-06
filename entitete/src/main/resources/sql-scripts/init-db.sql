DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS list;
DROP TABLE IF EXISTS user;

CREATE TABLE "user" (
                        "user_id" serial PRIMARY KEY ,
                        "name" VARCHAR (255) NOT NULL,
                        "surname" VARCHAR (255) NOT NULL,
                        "email" VARCHAR (255) NOT NULL,
                        "password" VARCHAR (255) NOT NULL,
                        "join_date" DATE,
                        "last_login" DATE
);

CREATE TABLE "list" (
                        "list_id" SERIAL NOT NULL PRIMARY KEY ,
                        "user_id" integer NOT NULL,
                        "name" VARCHAR (50) NOT NULL,
                        "created_date" DATE,
                        "last_modified" DATE,
                        CONSTRAINT list_user_fkey FOREIGN KEY (user_id)
                            REFERENCES "user" (user_id) MATCH SIMPLE
                            ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE "item" (
                        "item_id" serial PRIMARY KEY ,
                        "name" VARCHAR (255) NOT NULL,
                        "description" VARCHAR (255) NOT NULL,
                        "checked" BOOLEAN DEFAULT FALSE,
                        "created_date" DATE,
                        "list_id" integer NOT NULL,
                        CONSTRAINT list_item_fkey FOREIGN KEY (list_id)
                            REFERENCES list (list_id) MATCH SIMPLE
                            ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO "user" (name, surname, email, password, join_date, last_login) VALUES
    ('Petra', 'Kos', 'petra.kos@hotmail.com', 'geslo',  CURRENT_DATE , CURRENT_DATE ),
    ('Miha', 'Novak', 'miha.novak@hotmail.com', 'novak',  CURRENT_DATE , CURRENT_DATE ),
    ('Dejan', 'Lavbič', 'dj.lavbi@hotmail.com', 'lavbi',  CURRENT_DATE , CURRENT_DATE ),
    ('Franci', 'Klavž', 'franci.klavz@hotmail.com', 'klavz',  CURRENT_DATE , CURRENT_DATE ),
    ('Janez', 'Kranjski', 'janez@hotmail.com', 'kranjski',  CURRENT_DATE , CURRENT_DATE );

INSERT INTO list (user_id, name, created_date, last_modified) VALUES
    (1, 'Moj prvi list', CURRENT_DATE , CURRENT_DATE ),
    (1, 'Moj drugi list', CURRENT_DATE , CURRENT_DATE ),
    (2, 'Za hišo', CURRENT_DATE , CURRENT_DATE ),
    (3, 'Kupi jutri', CURRENT_DATE , CURRENT_DATE );

INSERT INTO item (name, description, checked, created_date, list_id) VALUES
    ('Izdelek 1', 'Kupi to', FALSE, CURRENT_DATE, CURRENT_DATE );