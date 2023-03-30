alter table aluno add constraint FK_curso_aluno foreign key(idcurso) references curso(id);
alter table aluno add constraint FK_cidade_aluno foreign key(idcidade) references cidade(id);

insert into curso(nomecurso) values('Desenvolvimento de sistemas');
insert into curso(nomecurso) values('Química');

insert into cidade(nomecidade, uf) values ('Macatuba', 'SP');
insert into cidade(nomecidade, uf) values ('Lençóis Paulista', 'SP');
insert into cidade(nomecidade, uf) values ('Bauru', 'SP');
insert into cidade(nomecidade, uf) values ('Manaus', 'AM');
insert into cidade(nomecidade, uf) values ('Maceió', 'AL');
insert into cidade(nomecidade, uf) values ('Porto Alegre', 'RS');

insert into aluno(nomealuno, idcurso, idcidade) values ('Nickolas', 1, 1);
insert into aluno(nomealuno, idcurso, idcidade) values ('Gabriel', 2, 2);
insert into aluno(nomealuno, idcurso, idcidade) values ('Marcela', 1, 3);
insert into aluno(nomealuno, idcurso, idcidade) values ('Duda', 2, 2);