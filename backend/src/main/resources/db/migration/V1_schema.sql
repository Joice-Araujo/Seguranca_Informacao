CREATE TABLE user (
    id varchar(36) primary key unique not null,
    idUser varchar(36) unique not null,
    privateKey varchar(255) unique,
    algoritmo varchar(3),
    keySize int
);