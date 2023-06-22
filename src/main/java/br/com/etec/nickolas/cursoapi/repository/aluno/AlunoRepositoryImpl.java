package br.com.etec.nickolas.cursoapi.repository.aluno;

import br.com.etec.nickolas.cursoapi.model.Aluno;
import br.com.etec.nickolas.cursoapi.model.Curso;
import br.com.etec.nickolas.cursoapi.repository.filter.AlunoFilter;
import br.com.etec.nickolas.cursoapi.repository.filter.CursoFilter;
import br.com.etec.nickolas.cursoapi.repository.projections.AlunoDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    private Long total(AlunoFilter alunofilter){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Aluno> root = criteria.from(Aluno.class);

        Predicate[] predicates = criarRestricoes(alunofilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomealuno")));

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }


    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroPágina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroPágina);
        query.setMaxResults(totalRegistrosPorPagina);
    }


    private Predicate[] criarRestricoes(AlunoFilter alunofilter, CriteriaBuilder builder, Root<Aluno> root) {
        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(alunofilter.getNomealuno())){
            predicates.add(builder.like(builder.lower(root.get("nomealuno")),
                    "%" + alunofilter.getNomealuno().toLowerCase() + "%"));
        }

        if(!StringUtils.isEmpty(alunofilter.getNomecidade())){
            predicates.add(builder.like(builder.lower(root.get("cidade").get("nomecidade")),
                    "%" + alunofilter.getNomecidade().toLowerCase() + "%"));
        }

        if(!StringUtils.isEmpty(alunofilter.getNomecurso())){
            predicates.add(builder.like(builder.lower(root.get("curso").get("nomecurso")),
                    "%" + alunofilter.getNomecurso().toLowerCase() + "%"));
        }

        if(!StringUtils.isEmpty(alunofilter.getUf())){
            predicates.add(builder.equal(builder.lower(root.get("cidade").get("uf")),
                    alunofilter.getUf().toLowerCase()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}