package br.com.etec.nickolas.cursoapi.resources;

import br.com.etec.nickolas.cursoapi.model.Aluno;
import br.com.etec.nickolas.cursoapi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}