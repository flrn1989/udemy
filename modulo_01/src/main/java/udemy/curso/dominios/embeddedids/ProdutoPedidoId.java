/**
 * 
 */
package udemy.curso.dominios.embeddedids;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import udemy.curso.dominios.Pedido;
import udemy.curso.dominios.Produto;

/** Id para a ProdutoPedido. */
@Embeddable
public final class ProdutoPedidoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "PRODUTO_ID")
	private Produto produto;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "PEDIDO_ID")
	private Pedido pedido;

	/** Construtor padrão. */
	public ProdutoPedidoId() {
	}

	/** @param produto
	 * @param pedido */
	public ProdutoPedidoId(Produto produto, Pedido pedido) {
		this.produto = produto;
		this.pedido = pedido;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		ProdutoPedidoId other = (ProdutoPedidoId) obj;
		if (pedido == null) {
			if (other.pedido != null) {
				return false;
			}
		} else if (!pedido.equals(other.pedido)) {
			return false;
		}
		if (produto == null) {
			if (other.produto != null) {
				return false;
			}
		} else if (!produto.equals(other.produto)) {
			return false;
		}
		return true;
	}

	/** @return the produto */
	public Produto getProduto() {
		return produto;
	}

	/** @param produto the produto to set */
	public void setProduto(Produto produto) {
		this.produto = produto;
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
