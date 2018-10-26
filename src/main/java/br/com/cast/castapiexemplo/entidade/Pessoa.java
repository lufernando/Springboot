package br.com.cast.castapiexemplo.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cast.castapiexemplo.dto.EnderecoDTO;

@Entity
@Table(name = "pessoa", schema = "manterpessoa")
public class Pessoa {
	
	@Id
	private String cpf;
	private String nome;
	private String email;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco enderecoDTO) {
		this.endereco = enderecoDTO;
	}

	public Endereco fromDTO(EnderecoDTO dto) {
		Endereco e = new Endereco();
		e.setBairro(dto.getBairro());
		e.setCep(dto.getCep());
		e.setCidade(dto.getCidade());
		e.setComplemento(dto.getComplemento());
		e.setLogradouro(dto.getLogradouro());
		e.setNumero(dto.getNumero());
		e.setUf(dto.getUf());
		
		return e;
	}
	
	
	
}