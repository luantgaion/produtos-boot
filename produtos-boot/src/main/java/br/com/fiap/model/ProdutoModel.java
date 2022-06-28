package br.com.fiap.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoModel {

	private long id;
	private String nome;
	private String sku;
	private String descricao;
	private BigDecimal preco;
	private String caracteristicas;
	private CategoriaModel categoria;
	private MarcaModel marca;

	public ProdutoModel() {
	}

	public ProdutoModel(long id, String nome, String sku, String descricao, BigDecimal preco, String caracteristicas,
			CategoriaModel categoria, MarcaModel marca) {
		super();
		this.id = id;
		this.nome = nome;
		this.sku = sku;
		this.descricao = descricao;
		this.preco = preco;
		this.caracteristicas = caracteristicas;
		this.categoria = categoria;
		this.marca = marca;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUTO_SEQ")
	@SequenceGenerator(name = "PRODUTO_SEQ", sequenceName = "PRODUTO_SEQ", allocationSize = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NOME")
	@NotNull(message = "Nome obrigatório")
	@Size(min = 2, max = 50, message = "NOME deve ser entre 2 e 50 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "SKU")
	@NotNull(message = "Sku obrigatório")
	@Size(min = 2, max = 40, message = "SKU deve ser entre 2 e 50 caracteres")
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Column(name = "DESCRICAO")
	@NotNull(message = "Descrição obrigatório")
	@Size(min = 10, max = 400, message = "DESCRIÇÃO deve ser entre 10 e 400 caracteres")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "PRECO")
	@DecimalMin(value = "0.01", message = "PRECO deve ser acima de 0.01")
	@NumberFormat(style = Style.CURRENCY)
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Column(name = "CARACTERISTICAS")
	@Size(min = 10, max = 400, message = "CARACTERÍSTICAS deve ser entre 10 e 400 caracteres")
	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA", nullable = false)
	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	@ManyToOne
	@JoinColumn(name = "ID_MARCA", nullable = false)
	public MarcaModel getMarca() {
		return marca;
	}

	public void setMarca(MarcaModel marca) {
		this.marca = marca;
	}

}
