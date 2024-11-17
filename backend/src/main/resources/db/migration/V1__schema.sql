CREATE TABLE user (
    id varchar(36) primary key unique not null,
    id_user varchar(36) unique not null,
    private_key text,
    algoritmo varchar(3),
    key_size int
);