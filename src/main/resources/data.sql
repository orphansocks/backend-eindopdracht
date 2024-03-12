-- datasql dient om de database te prefillen
-- doe dit voor ALLE entiteiten
--
--

INSERT INTO users (username, password, email, enabled)
VALUES
('user', '2fec893c-99f8-4831-be79-8f69f9e67537','user@test.nl', TRUE),
('admin', '2fec893c-99f8-4831-be79-8f69f9e67537', 'admin@test.nl', TRUE),
('designer', '2fec893c-99f8-4831-be79-8f69f9e67537', 'designer@test.nl', TRUE),
('eva', 'password', 'eva@relatives.nl', TRUE);


INSERT INTO roles (username, role)
VALUES
('user', 'ROLE_USER'),
('admin', 'ROLE_ADMIN'),
('designer', 'ROLE_DESIGNER'),
('eva', 'ROLE_USER');



INSERT INTO relatives (id, first_name, last_name, nick_name, dob, social_status, name_of_partner, has_kids, amount_of_kids, names_of_kids, misc, relation)
VALUES
(1001, 'Eva', 'van Dongen', 'Eef', '1976-06-10', 'together', 'Freek', true, 2 , 'Fynn en Luz', 'hou het simpel', 'family' ),
(1002, 'Freek', 'Tromp', '', '1974-07-18', 'together', 'Eva', true, 2 , 'Fynn en Luz', 'geen sla geen vla', 'family' ),
(1003, 'Frouke', 'van Dongen', '', '1979-06-09', 'together', 'GeertJan', true, 1 , 'Jule', 'dat bedoel ik', 'family' ),
(1004, 'Nathan', 'Fielder', 'Nathan for You', '1983-05-12', 'divorced', '', false, 0 , '', 'An observer of human behavior. He mines the absurdities of how people relate to one another and how he relates to them, and it all makes you think and laugh', 'neighbours' ),
(1005, 'Kees', 'van Dongen', '', '1877-01-26', 'divorced', 'Augusta Preitinger', true, 2 , 'een dochter van Augusta en een zoon van Marie-Claire', 'Opnieuw getrouwd met Marie-Claire Huguen', 'colleagues' ),
(1006, 'Marie', 'Antoinette', '', '1976-10-16', 'single', '', true, 1 , 'Thérèse', 'Monsieur, je vous demande excuse, je ne l’ai pas fait exprès.', 'friends' ),
(1007, 'Immanuel', 'Kant', 'Filosoof', '1724-04-27', 'together', 'Maria', false, 0 , 'unknown', 'Look closely, the beautiful may be small', 'colleagues' ),
(1008, 'Jan', 'Janssen', 'JJ', '1976-03-03', 'married', 'Maria', true, 4 , 'Jannie, Kobus, Lieve, Moos', 'En wat schrijven we hier', 'friends' ),
(1009, 'James', 'Gosling', 'Java', '1955-05-19', 'divorced', '', true, 3 , 'Kate, Kelsey, Lorena', 'make something cool', 'fellow students' ),
(1010, 'Brendan', 'Eich', 'JavaScript', '1961-07-04', 'married', 'Eleanor', true, 2 , 'JavaScript and Mozilla', 'Hello, world!', 'fellow students' );


INSERT INTO groups (id, group_name, group_place)
VALUES
    (2001, 'De SchoonFam', ''),
    (2002, 'Best friends', ''),
    (2003, 'Huis', 'Utrecht'),
    (2004, 'In Da Hood', 'Haarlem'),
    (2005, 'Kookclub', 'Amsterdam');

INSERT INTO groups_relatives (groups_id, relatives_id)
VALUES
        (2001, 1001),
       (2001, 1003),
        (2002, 1002),
       (2002, 1007),
       (2002, 1010),
       (2003, 1003),
       (2003, 1008),
       (2004, 1004),
       (2004, 1009),
       (2005, 1005),
       (2005, 1010);


-- INSERT INTO cards (id, card_name, designer, category, amount_of_downloads)
-- VALUES
--     (3001, 'yvi', 4001, 'birthday', 21),
--     (3002, 'kepler', 4001, 'birth', 22),
--     (3003, 'haas', 4002, 'birthday', 15),
--     (3004, 'degular', 4002, 'birthday', 16),
--     (3005, 'roboto', 4002, 'getwell', 3);

INSERT INTO cards (id, card_name, designed_by, category, amount_of_downloads)
VALUES
    (3001, 'yvi', 'relatives original', 'birthday', 21),
    (3002, 'kepler','relatives original', 'birth', 22),
    (3003, 'haas', 'relatives original', 'birthday', 15),
    (3004, 'degular','relatives original', 'birthday', 16),
    (3005, 'roboto', 'relatives original', 'getwell', 3);

INSERT INTO designers ( id, company, firstname, lastname, address, url, phone, bank_account)
VALUES
    (4001, 'relatives original', 'Eva', 'van Dongen', 'companyAddress', 'relatives.nl', '0612345678', 'IBAN12345678' ),
    (4002, 'ACompany', 'AFirstName', 'ALastName', 'companyAddress', 'companyUrl.nl', '0612345678', 'IBAN12345678' );






