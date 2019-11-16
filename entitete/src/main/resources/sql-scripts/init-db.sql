INSERT INTO "user" (name, surname, email, password, joined, last_login) VALUES ('Petra', 'Kos', 'petra.kos@hotmail.com', 'geslo', CURRENT_DATE , CURRENT_DATE ), ('Miha', 'Novak', 'miha.novak@hotmail.com', 'novak', CURRENT_DATE , CURRENT_DATE ), ('Dejan', 'Lavbič', 'dj.lavbi@hotmail.com', 'lavbi', CURRENT_DATE , CURRENT_DATE ), ('Franci', 'Klavž', 'franci.klavz@hotmail.com', 'klavz', CURRENT_DATE , CURRENT_DATE ), ('Janez', 'Kranjski', 'janez@hotmail.com', 'kranjski', CURRENT_DATE , CURRENT_DATE );

INSERT INTO list (user_id, name, created, modified) VALUES (1, 'Moj prvi list', CURRENT_DATE , CURRENT_DATE ), (1, 'Moj drugi list', CURRENT_DATE , CURRENT_DATE ), (2, 'Za hišo', CURRENT_DATE , CURRENT_DATE ), (3, 'Kupi jutri', CURRENT_DATE , CURRENT_DATE );

INSERT INTO item (name, description, checked, created, list_id) VALUES ('Izdelek 1', 'Kupi to', FALSE, CURRENT_DATE, 1 ), ('Izdelek 2', 'shiny', FALSE, CURRENT_DATE, 2 ), ('Izdelek 3', 'niBBa', FALSE, CURRENT_DATE, 3 );
