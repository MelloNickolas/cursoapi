package br.com.etec.nickolas.cursoapi.repository;

import br.com.etec.nickolas.cursoapi.model.Aluno;
import br.com.etec.nickolas.cursoapi.repository.aluno.AlunoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>, AlunoRepositoryQuery {
}
