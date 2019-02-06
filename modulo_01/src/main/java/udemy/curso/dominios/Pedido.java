/**
 * 
 */
package udemy.curso.dominios;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/** Domínio para Pedido */
@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDateTime instante;

	@OneToOne
	private Pagamento pagamento;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "ENDERECO_DE_ENTREGA_ID")
	private Endereco enderecoDeEntrega;

	/** Construtor padrão. */
	public Pedido() {
	}

	/** @param instante
	 * @param cliente
	 * @param enderecoDeEntrega */
	public Pedido(LocalDateTime instante, Cliente cliente, Endereco enderecoDeEntrega) {
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;

		if (this.cliente != null) {
			this.cliente.getPedidos().add(this);
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
