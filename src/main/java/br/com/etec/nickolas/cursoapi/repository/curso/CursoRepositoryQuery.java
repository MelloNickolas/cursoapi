package br.com.etec.nickolas.cursoapi.repository.curso;

import br.com.etec.nickolas.cursoapi.model.Curso;
import br.com.etec.nickolas.cursoapi.repository.filter.CursoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CursoRepositoryQuery {

    public Page<Curso> Filtrar(CursoFilter cursoFilter, Pageable pageable);

}
