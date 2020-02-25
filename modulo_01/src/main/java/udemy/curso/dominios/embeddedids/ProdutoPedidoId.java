package udemy.curso.dominios.embeddedids;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import udemy.curso.dominios.Pedido;
import udemy.curso.dominios.Produto;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProdutoPedidoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "PRODUTO_ID")
	private Produto produto;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "PEDIDO_ID")
	private Pedido pedido;

}
