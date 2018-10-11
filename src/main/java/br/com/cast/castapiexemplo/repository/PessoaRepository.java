package br.com.cast.castapiexemplo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cast.castapiexemplo.entidade.Pessoa;


@Repository
public class PessoaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> buscarTodos(){
		return entityManager.createQuery(
				"SELECT p "
			  + "FROM Pessoa p "
			  + "join fetch p.endereco").getResultList();
	}
	
	public Pessoa buscarPorCpf(String cpf) {
		return entityManager.find(Pessoa.class, cpf);
	}
	
	@Transactional
	public void inserir (Pessoa pessoa) {
		entityManager.persist(pessoa);
	}
	
	@Transactional
	public void alterar(Pessoa pessoa) {
		 entityManager.merge(pessoa);
	}
	
	@Transactional
	public void remover(Pessoa pessoa) {
		entityManager.remove(pessoa);
	}
	
	
	/*@SuppressWarnings("unchecked")
	public List<Pessoa> buscarTodos() {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select p ")
			.append("from ").append(Pessoa.class.getName()).append(" p ")
			.append("left join fetch p.endereco");
		
		Query query = em.createQuery(jpql.toString());
		return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PessoaDTO> buscarTodosDTO() {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select new br.com.cast.manterpessoa.dto.PessoaDTO( ")
		.append(" p.cpf, p.nome, p.email, p.enderecoDTO ")
		.append(")")
		.append("from ").append(Pessoa.class.getName()).append(" p ");
		
		Query query = em.createQuery(jpql.toString());
		return query.getResultList();
	}
	
	public Pessoa buscarPorCpf(String cpf) {
		return em.find(Pessoa.class, cpf);
	}

	public void inserir(Pessoa pessoa) {
		em.getTransaction().begin();
		try {
			em.persist(pessoa);
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.getTransaction().commit();
	}
	
	public void remover(Pessoa pessoa) {
		em.getTransaction().begin();
		try {
			em.remove(pessoa);
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.getTransaction().commit();
	}
	
	public void alterar(Pessoa pessoa) {
		em.getTransaction().begin();
		try {
			em.merge(pessoa);
		} catch(Exception e) {
			em.getTransaction().rollback();
		}
		em.getTransaction().commit();
	}*/
	
}
