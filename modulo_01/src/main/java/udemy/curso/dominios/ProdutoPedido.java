/**
 * 
 */
package udemy.curso.dominios;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import udemy.curso.dominios.embeddedids.ProdutoPedidoId;

/** Relacional entre Produto e Pedido. */
@Entity
public class ProdutoPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProdutoPedidoId id;

	private Double preco;

	private Double desconto;

	private Integer quantidade;

	/** Construtor padrão. */
	public ProdutoPedido() {
	}

	/** @param produto
	 * @param pedido
	 * @param preco
	 * @param desconto
	 * @param quantidade */
	public ProdutoPedido(Produto produto, Pedido pedido,
			Double preco, Double desconto, Integer quantidade) {
		this.id = new ProdutoPedidoId(produto, pedido);
		this.preco = preco;
		this.desconto = desconto;
		this.quantidade = quantidade;

		if (produto != null) {
			produto.getProdutosPedidos().add(this);
		}

		if (pedido != null) {
			pedido.getProdutosPedidos().add(this);
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
		ProdutoPedido other = (ProdutoPedido) obj;
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
	public ProdutoPedidoId getId() {
		return id;
	}

	/** @param id the id to set */
	public void setId(ProdutoPedidoId id) {
		this.id = id;
	}

	/** @return the preco */
	public Double getPreco() {
		return preco;
	}

	/** @param preco the preco to set */
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/** @return the desconto */
	public Double getDesconto() {
		return desconto;
	}

	/** @param desconto the desconto to set */
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	/** @return the quantidade */
	public Integer getQuantidade() {
		return quantidade;
	}

	/** @param quantidade the quantidade to set */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
