package com.javanauta.usuario.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefonesDTO {
    private Long id;
    private String numero;
    private String ddd;
}
