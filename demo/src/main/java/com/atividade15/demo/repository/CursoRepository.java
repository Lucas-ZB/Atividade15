package com.atividade15.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atividade15.demo.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {}
