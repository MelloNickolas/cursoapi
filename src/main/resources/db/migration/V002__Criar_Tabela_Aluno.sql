create table aluno(
    id bigint not null primary key auto_increment,
    nomealuno varchar(100),
    idcurso int not null,
    idcidade int not null
);
