/**
 * 
 */
package udemy.curso.dominios;

import java.io.Serializable;
import java.time.LocalDateTime;

/** Domínio para Pedido */
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private LocalDateTime instante;

	private Pagamento pagamento;

	private Cliente cliente;

	private Endereco enderecoDeEntrega;

	/** Construtor padrão. */
	public Pedido() {
	}

	/** @param instante
	 * @param pagamento
	 * @param cliente
	 * @param enderecoDeEntrega */
	public Pedido(LocalDateTime instante, Pagamento pagamento, Cliente cliente, Endereco enderecoDeEntrega) {
		this.instante = instante;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
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
		Pedido other = (Pedido) obj;
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

	/** @return the instante */
	public LocalDateTime getInstante() {
		return instante;
	}

	/** @param instante the instante to set */
	public void setInstante(LocalDateTime instante) {
		this.instante = instante;
	}

	/** @return the pagamento */
	public Pagamento getPagamento() {
		return pagamento;
	}

	/** @param pagamento the pagamento to set */
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	/** @return the cliente */
	public Cliente getCliente() {
		return cliente;
	}

	/** @param cliente the cliente to set */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/** @return the enderecoDeEntrega */
	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	/** @param enderecoDeEntrega the enderecoDeEntrega to set */
	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

}
