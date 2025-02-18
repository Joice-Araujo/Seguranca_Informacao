CREATE TABLE user (
    id varchar(36) primary key unique not null,
    id_user varchar(36) unique not null,
    private_key BLOB,
    algoritmo varchar(3)
);