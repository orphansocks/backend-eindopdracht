-- datasql dient om de database te prefillen
-- doe dit voor ALLE entiteiten


INSERT INTO relatives (id, first_name, last_name, nick_name, dob, social_status, has_kids, amount_of_kids, names_of_kids, misc, relation)
VALUES
(1001, 'Eva', 'van Dongen', 'Eef', '1976-06-10', 'together', true, 2 , 'Fynn en Luz', 'geen sla geen vla', 'family' ),
(1002, 'Marie', 'Machielsen', 'Marietje', '1980-01-02', 'divorced', true, 2 , 'Klaasje en Pietje', 'gescheiden van Piet', 'neighbour' )

