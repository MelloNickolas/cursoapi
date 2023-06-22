package br.com.etec.nickolas.cursoapi.resources;

import br.com.etec.nickolas.cursoapi.model.Aluno;
import br.com.etec.nickolas.cursoapi.repository.AlunoRepository;
import br.com.etec.nickolas.cursoapi.repository.filter.AlunoFilter;
import br.com.etec.nickolas.cursoapi.repository.projections.AlunoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/todos")
    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    @GetMapping()
    public Page<AlunoDto> pesquisar(AlunoFilter alunofilter, Pageable pageable){
        return alunoRepository.filtrar(alunofilter, pageable);
    }

}
