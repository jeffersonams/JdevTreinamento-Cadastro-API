package br.com.springboot.curso_jdevtreinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdevtreinamento.model.Usuario;
import br.com.springboot.curso_jdevtreinamento.repository.UsuarioRepository;


@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
 
	
    @RequestMapping(value = "/mostranome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
       
    /*METODO PARA INSERIR*/
    @RequestMapping(value = "inserir/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String Retorno(@PathVariable String nome)
    {
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario);
    	
    	return "Dados gravador com sucesso! " + nome;
    }
    
    /*METODO PARA LISTAR*/
    @GetMapping(value = "listartodos")
    @ResponseBody /*retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> ListaUsuario()
    {
    	List<Usuario> usuarios = usuarioRepository.findAll();/*executa a consulta no banco de dados*/
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*vai retornar no corpo da respsta uma lista em json*/
    	
    }

    /*METODO PARA SALVAR*/
    @PostMapping(value = "salvar")
    @ResponseBody/*Descricao da reposta*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario)/*recebe os dados para salvar*/
    {
    	Usuario user = usuarioRepository.save(usuario); /*executa no banco de dados*/
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	    	
    }
    
    /*METODO PARA DELETAR*/
    @DeleteMapping(value = "delete")
    public ResponseEntity<String> delete(@RequestParam Long iduser)
    {
    	usuarioRepository.deleteById(iduser);
    	return new  ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
    	
    }
    
    /*METODO PARA BUSCAR POR ID*/
    @GetMapping(value = "buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") long iduser)/*name = é o nome do parametro*/
    {
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    			
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    
    /*METODO ATUALIZAR*/
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario)
    {
    	
    	if(usuario.getId() == null)
    	{
    		return new ResponseEntity<String>("Id nao informado para atualização", HttpStatus.OK);
    		
    	}
    	    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name)
    {
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name);
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
       
    
}
