package lauro.project.minhasfinancas.api.DTO;

import java.math.BigDecimal;

import lauro.project.minhasfinancas.model.enums.StatusLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoDTO {

    private Long id;
    private String descricao;
    private  Integer mes;
    private Integer ano;
    private BigDecimal valor;
    private Long usuario;
    private String tipo;
    private StatusLancamento status;


}
