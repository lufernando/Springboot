package br.com.cast.castapiexemplo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.castapiexemplo.business.PessoaBusiness;
import br.com.cast.castapiexemplo.dto.PessoaDTO;

@RestController
@RequestMapping(path="/pessoa")
public class TestAPI {
	
	@Autowired
	private PessoaBusiness pessoaBusiness;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<PessoaDTO> buscarTodos() {
		return pessoaBusiness.buscarTodos();
	}
	
	@RequestMapping(path="/{cpf}", method=RequestMethod.GET)
	public PessoaDTO buscarPorCpf(@PathVariable("cpf") String cpf) {
		PessoaDTO pessoaDTO = pessoaBusiness.buscarPorCpf(cpf);
		System.out.println("cpf que veio por parametro: " + cpf);
		return pessoaDTO;
	}
	
	@RequestMapping(path="/{cpf}/and/{nome}", method=RequestMethod.GET)
	public String buscarPorCpfENome(
			@PathVariable("cpf") String cpf, 
			@PathVariable("nome") String nome) {
		System.out.println("parametros: " + cpf + ", " + nome);
		return "Essa busca retorna o CPF e o nome no console";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void inserir(@RequestBody PessoaDTO dto) {
		pessoaBusiness.inserir(dto);
		System.out.println(dto.getNome());
		//return "Pessoa inserida!!";
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public PessoaDTO alterar(@RequestBody PessoaDTO dto) {
		pessoaBusiness.alterar(dto);
		System.out.println(dto.getNome());
		return dto;
	}
	
	@RequestMapping(path="/deletar/{cpf}", method=RequestMethod.DELETE)
	public void deletar(@PathVariable("cpf") String cpf) {
		pessoaBusiness.excluir(cpf);
		System.out.println("Parametros passados para deletar: " + cpf);
	}
	
	
}
