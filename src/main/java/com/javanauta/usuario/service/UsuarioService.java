package com.javanauta.usuario.service;

import com.javanauta.usuario.converter.UsuarioConverter;
import com.javanauta.usuario.dto.EnderecosDTO;
import com.javanauta.usuario.dto.TelefonesDTO;
import com.javanauta.usuario.dto.UsuarioDTO;
import com.javanauta.usuario.infraestructure.entity.Enderecos;
import com.javanauta.usuario.infraestructure.entity.Telefones;
import com.javanauta.usuario.infraestructure.entity.Usuario;
import com.javanauta.usuario.infraestructure.exceptions.ConflictException;
import com.javanauta.usuario.infraestructure.exceptions.ResourceNotFoundException;
import com.javanauta.usuario.infraestructure.repository.EnderecosRepository;
import com.javanauta.usuario.infraestructure.repository.TelefonesRepository;
import com.javanauta.usuario.infraestructure.repository.UsuarioRepository;
import com.javanauta.usuario.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    //injeções de depenência.
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecosRepository enderecosRepository;
    private final TelefonesRepository telefonesRepository;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        //convertendo de usuárioDTO para entity
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        //salvando o usuário
        usuario = usuarioRepository.save(usuario);
        //convertendo novamente da Entity para DTO
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificaSeEmailExiste(email);
            if (existe) {
                throw new ConflictException("E-mail já cadastrado");
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado" + e.getCause());
        }
    }

    public boolean verificaSeEmailExiste(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email){
        try{
            return usuarioConverter.paraUsuarioDTO(usuarioRepository.findByEmail(email)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Email não encontrado" + email)));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email não encontrado" + email);
        }
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));//extraindo e-mail do token

        dto.setSenha(dto.getSenha() !=  null ? passwordEncoder.encode(dto.getSenha()) : null);

        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(()->
            new ResourceNotFoundException("E-mail não localizado"));

        Usuario usuario = usuarioConverter.updateUsuarioDTO(dto, usuarioEntity);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public EnderecosDTO atualizaEndereco(Long idEndereco, EnderecosDTO enderecosDTO){
        Enderecos entity = enderecosRepository.findById(idEndereco).orElseThrow(() ->
                new ResourceNotFoundException("Endereço não encontrado" + idEndereco));

        Enderecos endereco =  usuarioConverter.updateEndereco(enderecosDTO, entity);

        return usuarioConverter.paraUnicoEnderecoDTO(enderecosRepository.save(endereco));
    }

    public TelefonesDTO atualizaTelefone(Long idTelefone, TelefonesDTO telefonesDTO){
        Telefones entity = telefonesRepository.findById(idTelefone).orElseThrow(() ->
                new ResourceNotFoundException("Telelefone não encontrado" + idTelefone));

        Telefones telefones = usuarioConverter.updateTelefone(telefonesDTO, entity);

        return usuarioConverter.paraUnicoTelefoneDTO(telefonesRepository.save(telefones));
    }

    public EnderecosDTO  cadastroEndereco(String token, EnderecosDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));//extraindo e-mail do token
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
            () -> new ResourceNotFoundException("Email não localizado" + email)
        );

        Enderecos endereco = usuarioConverter.paraEnderecoEntity(dto, usuario.getId());

        Enderecos enderecoEntity = enderecosRepository.save(endereco);

        return usuarioConverter.paraUnicoEnderecoDTO(enderecoEntity);
    }

    public TelefonesDTO cadastroTelefones(String token, TelefonesDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));//extraindo e-mail do token
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
            () -> new ResourceNotFoundException("Email não localizado" + email)
        );

        Telefones telefone = usuarioConverter.paraTelefonesEntity(dto, usuario.getId());

        Telefones telefoneEntity = telefonesRepository.save(telefone);

        return usuarioConverter.paraUnicoTelefoneDTO(telefoneEntity);
    }
}
