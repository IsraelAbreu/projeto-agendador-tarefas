package com.javanauta.usuario.converter;

import com.javanauta.usuario.dto.EnderecosDTO;
import com.javanauta.usuario.dto.TelefonesDTO;
import com.javanauta.usuario.dto.UsuarioDTO;
import com.javanauta.usuario.infraestructure.entity.Enderecos;
import com.javanauta.usuario.infraestructure.entity.Telefones;
import com.javanauta.usuario.infraestructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    //Convertendo UsuarioDTO para Usuário(Entity)
    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecos(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefones(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Enderecos> paraListaEnderecos(List<EnderecosDTO> enderecosDTOS){
        return enderecosDTOS.stream().map(this::paraUnicoEndereco).toList();
    }

    public Enderecos paraUnicoEndereco(EnderecosDTO enderecosDTO){
        return Enderecos.builder()
                .rua(enderecosDTO.getRua())
                .numero(enderecosDTO.getNumero())
                .cep(enderecosDTO.getCep())
                .cidade(enderecosDTO.getCidade())
                .estado(enderecosDTO.getEstado())
                .complemento(enderecosDTO.getComplemento())
                .build();
    }

    public List<Telefones> paraListaTelefones(List<TelefonesDTO> telefonesDTOS){
        return telefonesDTOS.stream().map(this::paraUnicoTelefone).toList();
    }

    public Telefones paraUnicoTelefone(TelefonesDTO telefonesDTO){
        return Telefones.builder()
                .numero(telefonesDTO.getNumero())
                .ddd(telefonesDTO.getDdd())
                .build();
    }

    //Convertendo Usuario(Entity) para UsuarioDTO

    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecosDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefonesDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecosDTO> paraListaEnderecosDTO(List<Enderecos> enderecosDTO){
        return enderecosDTO.stream().map(this::paraUnicoEnderecoDTO).toList();
    }

    public EnderecosDTO paraUnicoEnderecoDTO(Enderecos enderecos){
        return EnderecosDTO.builder()
                .rua(enderecos.getRua())
                .numero(enderecos.getNumero())
                .cep(enderecos.getCep())
                .cidade(enderecos.getCidade())
                .estado(enderecos.getEstado())
                .complemento(enderecos.getComplemento())
                .build();
    }

    public List<TelefonesDTO> paraListaTelefonesDTO(List<Telefones> telefonesDTOS){
        return telefonesDTOS.stream().map(this::paraUnicoTelefoneDTO).toList();
    }

    public TelefonesDTO paraUnicoTelefoneDTO(Telefones telefones){
        return TelefonesDTO.builder()
                .numero(telefones.getNumero())
                .ddd(telefones.getDdd())
                .build();
    }
}
