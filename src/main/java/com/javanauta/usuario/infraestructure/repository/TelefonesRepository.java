package com.javanauta.usuario.infraestructure.repository;

import com.javanauta.aprendendo_spring.infraestructure.entity.Telefones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonesRepository extends JpaRepository<Telefones, Long> {

}
