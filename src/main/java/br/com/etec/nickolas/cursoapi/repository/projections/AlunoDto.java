package br.com.etec.nickolas.cursoapi.repository.projections;

public class AlunoDto {
    private Long id;
    private String nomealuno;
    private String nomecidade;
    private String uf;
    private String nomecurso;

    public AlunoDto(Long id, String nomealuno, String nomecidade, String uf, String nomecurso) {
        this.id = id;
        this.nomealuno = nomealuno;
        this.nomecidade = nomecidade;
        this.uf = uf;
        this.nomecurso = nomecurso;
    }

    
}
