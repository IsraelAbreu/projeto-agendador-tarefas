package com.javanauta.usuario.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecosDTO> enderecos;
    private List<TelefonesDTO> telefones;
}
