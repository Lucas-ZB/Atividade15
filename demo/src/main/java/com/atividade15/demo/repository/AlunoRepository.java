package com.atividade15.demo.repository;

import com.atividade15.demo.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {}
