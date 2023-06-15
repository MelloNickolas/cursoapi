package br.com.etec.nickolas.cursoapi.repository.aluno;

import br.com.etec.nickolas.cursoapi.repository.filter.AlunoFilter;
import br.com.etec.nickolas.cursoapi.repository.projections.AlunoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlunoRepositoryQuery {
    public Page<AlunoDto> filtrar(AlunoFilter alunofilter, Pageable pageable);

}
