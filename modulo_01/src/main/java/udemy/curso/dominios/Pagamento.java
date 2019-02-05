/**
 * 
 */
package udemy.curso.dominios;

import java.io.Serializable;

import udemy.curso.dominios.enums.EstadoDePagamento;

/** Domínio de Pagamento. */
public abstract class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer idDoEstadoPagamento;

	private Pedido pedido;

	/** Construtor padrão. */
	public Pagamento() {
	}

	/** @param estadoPagamento
	 * @param pedido */
	public Pagamento(EstadoDePagamento estadoPagamento, Pedido pedido) {
		this.idDoEstadoPagamento = estadoPagamento.getId();
		this.pedido = pedido;
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

	/** @return the idDoEstadoPagamento */
	public Integer getIdDoEstadoPagamento() {
		return idDoEstadoPagamento;
	}

	/** @param idDoEstadoPagamento the idDoEstadoPagamento to set */
	public void setIdDoEstadoPagamento(Integer idDoEstadoPagamento) {
		this.idDoEstadoPagamento = idDoEstadoPagamento;
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
