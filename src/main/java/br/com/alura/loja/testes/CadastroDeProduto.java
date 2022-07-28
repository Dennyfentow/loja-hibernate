package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");

		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		em.persist(celulares); // -> MANAGED INSERT
		celulares.setNome("XPTO"); // MANAGED UPDATE
		em.flush(); // -> DETACHED
		em.clear(); // -> DETACHED

		celulares = em.merge(celulares); // -> MANAGED SELECT
		celulares.setNome("1234"); // MANAGED UPDATE
		em.flush(); // -> DETACHED

//		em.getTransaction().commit(); // -> DETACHED
		em.clear();
		celulares = em.merge(celulares); // NECESSÁRIO ESTAR EM MANAGED PARA SER CHAMADO o remove() e flush()
		em.remove(celulares); // -> REMOVED
		em.flush();
		em.close();
	}
}
