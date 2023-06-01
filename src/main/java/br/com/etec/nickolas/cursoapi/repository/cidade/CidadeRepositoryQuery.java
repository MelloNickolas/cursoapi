package br.com.etec.nickolas.cursoapi.repository.cidade;

import br.com.etec.nickolas.cursoapi.model.Cidade;
import br.com.etec.nickolas.cursoapi.repository.filter.CidadeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CidadeRepositoryQuery {

    public Page<Cidade> Filtrar(CidadeFilter cidadefilter, Pageable pageable);

}
