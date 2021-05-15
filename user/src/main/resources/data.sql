/*
Mot de passe 'test' pour chaque utilisateur (encodé)
*/

INSERT INTO user (pseudo, password, surname, firstname, email, mob_number)
VALUES ('dj', '$2a$10$sGbftNDNaFqHoTa.DE6xRu6qRlcYApHnN/h50OskZSdCdOelLX622', 'De Almeida', 'Johann', 'johann.de-almeida@lacatholille.fr', '0766896607');

INSERT INTO user (pseudo, password, surname, firstname, email, mob_number)
VALUES ('gg', '$2a$10$sGbftNDNaFqHoTa.DE6xRu6qRlcYApHnN/h50OskZSdCdOelLX622', 'Giudice', 'Gianni', 'gianni.giudice@lacatholille.fr', null);

INSERT INTO user (pseudo, password, surname, firstname, email, mob_number)
VALUES ('gs', '$2a$10$sGbftNDNaFqHoTa.DE6xRu6qRlcYApHnN/h50OskZSdCdOelLX622', 'Guidez', 'Sébastien', 'sebastien.guidez@lacatholille.fr', null);

INSERT INTO friends (user_id, friend_id) VALUES (1, 3);
INSERT INTO friends(user_id, friend_id) VALUES (3, 1);