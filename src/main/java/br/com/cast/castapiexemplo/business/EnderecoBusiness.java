package br.com.cast.castapiexemplo.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.castapiexemplo.dto.EnderecoDTO;
import br.com.cast.castapiexemplo.entidade.Endereco;
import br.com.cast.castapiexemplo.repository.EnderecoRepository;

@Service
public class EnderecoBusiness {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public EnderecoBusiness() {
		this.enderecoRepository = new EnderecoRepository();
	}
	
	public List<EnderecoDTO> buscarTodos() {
		//com pojo
		//List<EnderecoDTO> enderecosDTO = enderecoDAO.buscarTodosComPojo();
		
		//sem pojo
		List<Endereco> enderecos = enderecoRepository.buscarTodos();
		List<EnderecoDTO> enderecosDTO = new ArrayList<>();
		
		for (Endereco endereco : enderecos) {
			EnderecoDTO dto = new EnderecoDTO();
			dto.setBairro(endereco.getBairro());
			dto.setCep(endereco.getCep());
			dto.setCidade(endereco.getCidade());
			dto.setComplemento(endereco.getComplemento());
			dto.setLogradouro(endereco.getLogradouro());
			dto.setNumero(endereco.getNumero());
			dto.setUf(endereco.getUf());
			
			enderecosDTO.add(dto);
		}
		return enderecosDTO;
	}
	
	public Endereco buscarPorCep(String cep) {
		return enderecoRepository.buscarPorCep(cep);
	}
	
	/*public void salvar(EnderecoDTO enderecoDTO) {
		
		Endereco endereco = new Endereco();
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setCep(enderecoDTO.getCep());
		endereco.setCidade(enderecoDTO.getCidade());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setLogradouro(enderecoDTO.getLogradouro());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setUf(enderecoDTO.getUf());
		
		enderecoRepository.inserir(endereco);
	}*/
}
