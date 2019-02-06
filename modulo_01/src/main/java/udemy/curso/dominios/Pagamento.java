/**
 * 
 */
package udemy.curso.dominios;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import udemy.curso.dominios.enums.EstadoDePagamento;
import udemy.curso.recursos.ExtratoraDeEnumIdentificavel;

/** Domínio de Pagamento. */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name = "ESTADO")
	private Integer idDoEstadoPagamento;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PEDIDO_ID")
	@MapsId
	private Pedido pedido;

	/** Construtor padrão. */
	public Pagamento() {
	}

	/** @param estadoPagamento
	 * @param pedido */
	public Pagamento(EstadoDePagamento estadoPagamento, Pedido pedido) {
		this.idDoEstadoPagamento = estadoPagamento.getId();
		this.pedido = pedido;

		if (this.pedido != null) {
			this.pedido.setPagamento(this);
		}
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/** @return the id */
	public Integer getId() {
		return id;
	}

	/** @param id the id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return the EstadoDePagamento */
	public EstadoDePagamento getIdDoEstadoPagamento() {
		return (EstadoDePagamento) new ExtratoraDeEnumIdentificavel(
				EstadoDePagamento.values())
						.de(idDoEstadoPagamento);
	}

	/** @param estadoDePagamento the EstadoDePagamento with the id to set */
	public void setIdDoEstadoPagamento(EstadoDePagamento estadoDePagamento) {
		this.idDoEstadoPagamento = estadoDePagamento.getId();
	}

	/** @return the pedido */
	public Pedido getPedido() {
		return pedido;
	}

	/** @param pedido the pedido to set */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
