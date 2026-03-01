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
                .id(enderecos.getId())
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
                .id(telefones.getId())
                .numero(telefones.getNumero())
                .ddd(telefones.getDdd())
                .build();
    }

    public Usuario updateUsuarioDTO(UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .id(entity.getId())
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .telefones(entity.getTelefones())
                .enderecos(entity.getEnderecos())
                .build();
    }

    public Enderecos updateEndereco(EnderecosDTO dto, Enderecos entity){
        return Enderecos.builder()
                .id(entity.getId())
                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : entity.getComplemento())
                .build();
    }

    public Telefones updateTelefone(TelefonesDTO dto, Telefones entity){
        return Telefones.builder()
                .id(entity.getId())
                .ddd(dto.getDdd() !=  null ? dto.getDdd() : entity.getDdd())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .build();
    }

    public Enderecos paraEnderecoEntity(EnderecosDTO dto, Long idUsuario){
        return Enderecos.builder()
                .rua(dto.getRua())
                .cidade(dto.getCidade())
                .cep(dto.getCep())
                .complemento(dto.getComplemento())
                .estado(dto.getEstado())
                .numero(dto.getNumero())
                .usuario_id(idUsuario)
                .build();
    }

    public Telefones paraTelefonesEntity(TelefonesDTO dto, Long idUsuario){
        return Telefones.builder()
                .ddd(dto.getDdd())
                .numero(dto.getNumero())
                .usuario_id(idUsuario)
                .build();
    }
}
