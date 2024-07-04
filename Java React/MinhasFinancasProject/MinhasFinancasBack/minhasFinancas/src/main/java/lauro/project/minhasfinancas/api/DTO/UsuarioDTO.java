package lauro.project.minhasfinancas.api.DTO;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private  String email;
    private String nome;
    private String senha;

}
