alter table aluno add constraint FK_curso_aluno foreign key(idcurso) references curso(id);
alter table aluno add constraint FK_cidade_aluno foreign key(idcidade) references cidade(id);