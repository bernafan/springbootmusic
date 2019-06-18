package br.com.devmedia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.devmedia.domain.Playlist;

//Anotacao do springboot
@Repository 	// indica que é um repositorio para o framework, no caso um BD 


/*Quando nao utilizados nao um framework de injecao de dependencias, em cada funcao será necessarios instanciar 
 * EntityManager par anos comunicarmos com o BF */

public class PlaylistDaoImpl implements PlaylistDao {
	
	@PersistenceContext		//O conteiner do spring gerencia a dependencia do Ententity manager.
	private EntityManager em;
	
	@Override
	public void salvar(Playlist playlist) {
		em.persist(playlist);     // Nao precisa iniciar e commitar uma transacao.
		
	}

	@Override
	public List<Playlist> recuperar() {
		return em.createQuery("select p from Playlist p", Playlist.class).getResultList();
	}

	@Override
	public Playlist recuperarPorID(long id) {
		return em.find(Playlist.class, id);
	}

	@Override
	public void atualizar(Playlist playlist) {
		em.merge(playlist);
		
	}

	@Override
	public void excluir(long id) {
		em.remove(em.getReference(Playlist.class, id));
		
	}
	
}
