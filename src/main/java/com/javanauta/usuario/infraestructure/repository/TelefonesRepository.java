package com.javanauta.usuario.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonesRepository extends JpaRepository<com.javanauta.usuario.infraestructure.entity.Telefones, Long> {

}
