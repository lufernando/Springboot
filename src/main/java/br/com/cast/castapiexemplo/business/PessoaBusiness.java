package br.com.cast.castapiexemplo.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.castapiexemplo.dto.EnderecoDTO;
import br.com.cast.castapiexemplo.dto.PessoaDTO;
import br.com.cast.castapiexemplo.entidade.Endereco;
import br.com.cast.castapiexemplo.entidade.Pessoa;
import br.com.cast.castapiexemplo.repository.EnderecoRepository;
import br.com.cast.castapiexemplo.repository.PessoaRepository;

@Service
public class PessoaBusiness {
	
	@Autowired
	private PessoaRepository pessoaRepositorio;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<PessoaDTO> buscarTodos(){
		List<Pessoa> pessoas = pessoaRepositorio.buscarTodos();
		List<PessoaDTO> dtos = new ArrayList<>();
		for (Pessoa pessoa : pessoas) {
			PessoaDTO pessoaDTO = new PessoaDTO();
			pessoaDTO.setCpf(pessoa.getCpf());
			pessoaDTO.setEmail(pessoa.getEmail());
			pessoaDTO.setNome(pessoa.getNome());
			
			Endereco endereco = pessoa.getEndereco();
			EnderecoDTO enderecoDTO = new EnderecoDTO();
			if(endereco != null) {
				enderecoDTO.setId(endereco.getId());
				enderecoDTO.setBairro(endereco.getBairro());
				enderecoDTO.setCep(endereco.getCep());
				enderecoDTO.setCidade(endereco.getCidade());
				enderecoDTO.setComplemento(endereco.getComplemento());
				enderecoDTO.setLogradouro(endereco.getLogradouro());
				enderecoDTO.setNumero(endereco.getNumero());
				enderecoDTO.setUf(endereco.getUf());
				
			}
			
			pessoaDTO.setEnderecoDTO(enderecoDTO);
			dtos.add(pessoaDTO);
		}
		return dtos;
		
	}
	
	public PessoaDTO buscarPorCpf(String cpf) {
		Pessoa pessoa = pessoaRepositorio.buscarPorCpf(cpf);
		PessoaDTO pessoaDTO = new PessoaDTO();
		
		pessoaDTO.setCpf(pessoa.getCpf());
		pessoaDTO.setEmail(pessoa.getEmail());
		pessoaDTO.setNome(pessoa.getNome());
		
		Endereco endereco = pessoa.getEndereco();
		EnderecoDTO enderecoDTO = new EnderecoDTO();
			endereco.setId(endereco.getId());
			enderecoDTO.setBairro(endereco.getBairro());
			enderecoDTO.setCep(endereco.getCep());
			enderecoDTO.setCidade(endereco.getCidade());
			enderecoDTO.setComplemento(endereco.getComplemento());
			enderecoDTO.setLogradouro(endereco.getLogradouro());
			enderecoDTO.setNumero(endereco.getNumero());
			enderecoDTO.setUf(endereco.getUf());
			
			pessoaDTO.setEnderecoDTO(enderecoDTO);
		
		return pessoaDTO;
}
	public void inserir(PessoaDTO dto) {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(dto.getCpf());
		pessoa.setEmail(dto.getEmail());
		pessoa.setNome(dto.getNome());
		
		Endereco endereco = enderecoRepository.buscarPorCep(dto.getEnderecoDTO().getCep());
		
		//pessoa.setEndereco(pessoa.fromDTO(dto.getEnderecoDTO()));
		pessoa.setEndereco(endereco);
		
		pessoaRepositorio.inserir(pessoa);
		
	}
	
	public void alterar(PessoaDTO pessoaDTO) {

		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaDTO.getCpf());
		pessoa.setEmail(pessoaDTO.getEmail());
		pessoa.setNome(pessoaDTO.getNome());

		Endereco endereco = enderecoRepository.buscarPorId(pessoaDTO.getEnderecoDTO().getId());
		pessoa.setEndereco(endereco);

		pessoaRepositorio.alterar(pessoa);
	}
	
	public void excluir(String cpf) {
		Pessoa pessoa = pessoaRepositorio.buscarPorCpf(cpf);
		pessoaRepositorio.remover(pessoa);
	}
	
	/*private PessoaRepository pessoaDAO;
	private EnderecoDAO enderecoDAO;
	
	public PessoaBusiness() {
		this.pessoaDAO = new PessoaRepository();
		this.enderecoDAO = new EnderecoDAO();
	}
	
	public List<PessoaDTO> buscarLista(){
		List<Pessoa> pessoas = pessoaDAO.buscarTodos();
		List<PessoaDTO> pessoasDTO = new ArrayList<>();
		
		for (Pessoa pessoa : pessoas) {
			PessoaDTO pessoaDTO = new PessoaDTO();
			pessoaDTO.setCpf(pessoa.getCpf());
			pessoaDTO.setEmail(pessoa.getEmail());
			pessoaDTO.setNome(pessoa.getNome());
			
			Endereco endereco = pessoa.getEndereco();
			EnderecoDTO enderecoDTO = new EnderecoDTO();
			if(endereco != null) {
				enderecoDTO.setBairro(endereco.getBairro());
				enderecoDTO.setCep(endereco.getCep());
				enderecoDTO.setCidade(endereco.getCidade());
				enderecoDTO.setComplemento(endereco.getComplemento());
				enderecoDTO.setLogradouro(endereco.getLogradouro());
				enderecoDTO.setNumero(endereco.getNumero());
				enderecoDTO.setUf(endereco.getUf());
				
			}
			
			pessoaDTO.setEnderecoDTO(enderecoDTO);
			pessoasDTO.add(pessoaDTO);
		}
		return pessoasDTO;
	}
	
	public void salvar(PessoaDTO pessoaDTO) {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaDTO.getCpf());
		pessoa.setEmail(pessoaDTO.getEmail());
		pessoa.setNome(pessoaDTO.getNome());
		
		
		//EnderecoDTO enderecoDTO = new EnderecoDTO();
		//endereco.setBairro(enderecoDTO.getBairro());
		Endereco endereco = enderecoDAO.buscarPorCep(pessoaDTO.getEnderecoDTO().getCep());
		
		endereco.setBairro(pessoaDTO.getEnderecoDTO().getBairro());
		endereco.setCep(pessoaDTO.getEnderecoDTO().getCep());
		endereco.setCidade(pessoaDTO.getEnderecoDTO().getCidade());
		endereco.setComplemento(pessoaDTO.getEnderecoDTO().getComplemento());
		endereco.setLogradouro(pessoaDTO.getEnderecoDTO().getLogradouro());
		endereco.setNumero(pessoaDTO.getEnderecoDTO().getNumero());
		endereco.setUf(pessoaDTO.getEnderecoDTO().getUf());
		
		pessoa.setEndereco(endereco);
		
		pessoaDAO.inserir(pessoa);
	}
	
	public void alterar(PessoaDTO pessoaDTO) {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaDTO.getCpf());
		pessoa.setEmail(pessoaDTO.getEmail());
		pessoa.setNome(pessoaDTO.getNome());
		
		Endereco endereco = enderecoDAO.buscarPorCep(pessoaDTO.getEnderecoDTO().getCep());
		pessoa.setEndereco(endereco);
		
		pessoaDAO.alterar(pessoa);
	}
	
	public void excluir(String cpf) {
		
		Pessoa pessoa = pessoaDAO.buscarPorCpf(cpf);
		pessoaDAO.remover(pessoa);
		
	}*/

}
