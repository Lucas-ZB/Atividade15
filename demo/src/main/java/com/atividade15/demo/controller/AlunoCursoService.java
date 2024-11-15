package com.atividade15.demo.controller;

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
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno not found"));
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso not found"));
        aluno.getCursos().add(curso);
        alunoRepository.save(aluno);
    }

    public void removerAlunoDeCurso(Long alunoId, Long cursoId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno not found"));
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso not found"));
        aluno.getCursos().remove(curso);
        alunoRepository.save(aluno);
    }

    public Set<Curso> listarCursosDeAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno not found"));
        return aluno.getCursos();
    }

    public Set<Aluno> listarAlunosDeCurso(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso not found"));
        return curso.getAlunos();
    }
}