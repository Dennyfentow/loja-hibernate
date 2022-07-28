package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = this.em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() { 
		String jpql = "SELECT p FROM Produto p "; // JPQL
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) { 
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome"; // JPQL - com filtro, pode passar ?1 e passar o indice no setParameter
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	} 
	
	public List<Produto> buscarPorNomeDaCategoria(String nome) { 
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome"; // JPQL - join feito de maneira automática
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	} 
	
}
