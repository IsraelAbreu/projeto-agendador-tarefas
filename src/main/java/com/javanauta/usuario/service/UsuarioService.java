package com.javanauta.usuario.service;

import com.javanauta.usuario.converter.UsuarioConverter;
import com.javanauta.usuario.dto.UsuarioDTO;
import com.javanauta.usuario.infraestructure.entity.Usuario;
import com.javanauta.usuario.infraestructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        //convertendo de usuárioDTO para entity
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        //salvando o usuário
        usuario = usuarioRepository.save(usuario);
        //convertendo novamente da Entity para DTO
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
