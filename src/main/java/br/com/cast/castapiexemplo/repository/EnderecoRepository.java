package br.com.cast.castapiexemplo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.cast.castapiexemplo.entidade.Endereco;

@Repository
public class EnderecoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	public List<Endereco> buscarTodos() {
		return entityManager.createQuery(
				"SELECT e "
			  + "FROM Endereco e").getResultList();
	}
	
	public Endereco buscarPorCep(String cep) {
		return entityManager.find(Endereco.class, cep);
	}
	
	/*@SuppressWarnings("unchecked")
	public List<EnderecoDTO> buscarTodosDTO() {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select new br.com.cast.manterpessoa.dto.EnderecoDTO( ")
		.append(" e.cep, e.logradouro, e.numero, e.complemento, e.bairro, e.cidade, e.uf ")
		.append(")")
		.append("from ").append(Endereco.class.getName()).append(" e ");
		
		Query query = em.createQuery(jpql.toString());
		return query.getResultList();
	}

	public Endereco buscarPorCep(String cep) {
		return em.find(Endereco.class, cep);
	}
	
	public void inserir(Endereco endereco) {
		em.getTransaction().begin();
		try {
			em.persist(endereco);
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.getTransaction().commit();
	}*/

}
