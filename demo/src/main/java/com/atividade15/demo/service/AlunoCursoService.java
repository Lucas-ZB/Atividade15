package com.atividade15.demo.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.atividade15.demo.model.Aluno;
import com.atividade15.demo.model.Curso;
import com.atividade15.demo.repository.AlunoRepository;
import com.atividade15.demo.repository.CursoRepository;

@Service
public class AlunoCursoService {
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoCursoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    public Aluno criarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Curso criarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void matricularAlunoEmCurso(Long alunoId, Long cursoId) {
        Optional<Aluno> aluno = alunoRepository.findById(alunoId);
        Optional<Curso> curso = cursoRepository.findById(cursoId);

        if (aluno.isPresent() && curso.isPresent()) {
            aluno.get().getCursos().add(curso.get());
            alunoRepository.save(aluno.get());
        }
    }

    public void removerAlunoDeCurso(Long alunoId, Long cursoId) {
        Optional<Aluno> aluno = alunoRepository.findById(alunoId);
        Optional<Curso> curso = cursoRepository.findById(cursoId);

        if (aluno.isPresent() && curso.isPresent()) {
            aluno.get().getCursos().remove(curso.get());
            alunoRepository.save(aluno.get());
        }
    }

    public Set<Curso> listarCursosDeAluno(Long alunoId) {
        return alunoRepository.findById(alunoId)
                .map(Aluno::getCursos)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public Set<Aluno> listarAlunosDeCurso(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .map(Curso::getAlunos)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }
}
