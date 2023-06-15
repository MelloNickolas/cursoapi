package br.com.etec.nickolas.cursoapi.repository.aluno;

import br.com.etec.nickolas.cursoapi.model.Aluno;
import br.com.etec.nickolas.cursoapi.model.Curso;
import br.com.etec.nickolas.cursoapi.repository.filter.AlunoFilter;
import br.com.etec.nickolas.cursoapi.repository.projections.AlunoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AlunoRepositoryImpl implements AlunoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<AlunoDto> filtrar(AlunoFilter alunofilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<AlunoDto> criteria = builder.createQuery(AlunoDto.class);
        Root<Aluno> root = criteria.from(Aluno.class);

        criteria.select(builder.construct(AlunoDto.class,
                root.get("id"),
                root.get("nomealuno"),
                root.get("cidade").get("nomecidade"),
                root.get("cidade").get("uf"),
                root.get("curso").get("nomecurso")
                ));

        Predicate[] predicates = criarRestricoes(alunofilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomealuno")));

            TypedQuery<AlunoDto> query = manager.createQuery(criteria);
            adicionarRestricoesDePaginacao(query, pageable);

            return null;
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<AlunoDto> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroPágina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroPágina);
        query.setMaxResults(totalRegistrosPorPagina);
    }
}
