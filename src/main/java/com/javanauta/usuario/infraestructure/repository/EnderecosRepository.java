package com.javanauta.usuario.infraestructure.repository;

import com.javanauta.usuario.infraestructure.entity.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecosRepository extends JpaRepository<Enderecos, Long>{

}
