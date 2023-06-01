package br.com.etec.nickolas.cursoapi.repository;

import br.com.etec.nickolas.cursoapi.model.Cidade;
import br.com.etec.nickolas.cursoapi.repository.cidade.CidadeRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>, CidadeRepositoryQuery {
}
