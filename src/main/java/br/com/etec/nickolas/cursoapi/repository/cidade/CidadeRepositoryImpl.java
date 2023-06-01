package br.com.etec.nickolas.cursoapi.repository.cidade;

import br.com.etec.nickolas.cursoapi.model.Cidade;
import br.com.etec.nickolas.cursoapi.repository.filter.CidadeFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

public class CidadeRepositoryImpl implements  CidadeRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Cidade> Filtrar(CidadeFilter cidadefilter, Pageable pageable){

        CriteriaBuilder builder = manager.getCriteriaBuilder();;
        CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
        Root<Cidade> root = criteria.from(Cidade.class);

        Predicate[] predicates = criarrestricoes(cidadefilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomecidade")));

        TypedQuery<Cidade> query = manager.createQuery(criteria);
        adicionarRestricoesDePagianacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(cidadefilter));

    }

    private Long total(CidadeFilter cidadefilter){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Cidade> root = criteria.from(Cidade.class);

        Predicate[] predicates = criarrestricoes(cidadefilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomecurso")));

        criteria.select(builder.count(root));

        return  manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePagianacao(TypedQuery<Cidade> query, Pageable pageable){
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroPágina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroPágina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Predicate[] criarrestricoes(CidadeFilter cidadefilter, CriteriaBuilder builder, Root<Cidade> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(cidadefilter.getNomecidade())){
            predicates.add(builder.like(builder.lower(root.get("nomecidade")),
                    "%" + cidadefilter.getNomecidade().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
