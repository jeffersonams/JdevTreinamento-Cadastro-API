package br.com.springboot.curso_jdevtreinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_jdevtreinamento.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	/*COMO VAI RETORNAR MAIS DE UM ITEM, ENTAO VAI SER UMA LISTA*/

	@Query(value = "Select u from Usuario u where u.nome like %?1% ") /*CRIANDO METODO PARA FAZER A BUSCA NO BANCO DE DADOS*/
	List<Usuario> buscarPorNome(String nome);
	
	
	
}
