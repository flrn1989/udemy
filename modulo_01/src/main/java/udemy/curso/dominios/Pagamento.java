package udemy.curso.dominios;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import udemy.curso.dominios.enums.EstadoDePagamento;
import udemy.curso.interfaces.Dominio;
import udemy.curso.util.ExtratoraDeEnum;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Data
public abstract class Pagamento implements Dominio {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name = "ESTADO")
	private Integer idDoEstadoPagamento;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PEDIDO_ID")
	@MapsId
	private Pedido pedido;

	public Pagamento(EstadoDePagamento estadoPagamento, Pedido pedido) {
		this.idDoEstadoPagamento = estadoPagamento.getId();
		this.pedido = pedido;
		if (Objects.nonNull(this.pedido)) {
			this.pedido.setPagamento(this);
		}
	}

	public EstadoDePagamento getIdDoEstadoPagamento() {
		return (EstadoDePagamento) ExtratoraDeEnum
				.extrairDe(EstadoDePagamento.values())
				.valorCom(idDoEstadoPagamento);
	}

	public void setIdDoEstadoPagamento(EstadoDePagamento estadoDePagamento) {
		this.idDoEstadoPagamento = estadoDePagamento.getId();
	}

}
