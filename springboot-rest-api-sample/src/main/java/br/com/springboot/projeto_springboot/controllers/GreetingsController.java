package br.com.springboot.projeto_springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.projeto_springboot.model.Usuario;
import br.com.springboot.projeto_springboot.repository.UsuarioRepository;

@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
        
    @GetMapping(value = "listatodos")
    @ResponseBody 
    public ResponseEntity<List<Usuario>> listUsuario(){
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long id){
    	 usuarioRepository.deleteById(id);
    	
    	return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    }
    
    @GetMapping(value = "buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(value="id") Long id){
    	Usuario user = usuarioRepository.findById(id).get();
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id do usuário não foi informado", HttpStatus.OK);
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
}
