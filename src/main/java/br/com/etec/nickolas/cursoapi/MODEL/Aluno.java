package br.com.etec.nickolas.cursoapi.MODEL;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeAluno;

    @ManyToOne
    @JoinColumn(name="idcurso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name="idcidade")
    private Cidade cidade;
}
