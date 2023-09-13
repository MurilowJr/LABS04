package services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Anotacao;
import entity.Autorizacao;
import entity.Usuario;
import repository.AnotacaoRepository;
import repository.AutorizacaoRepository;
import repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AutorizacaoRepository autorizacaoRepo;

    @Autowired
    private AnotacaoRepository anotacaoRepo;

    @Transactional
    public Usuario novoUsuario(Usuario usuario) {
        if(usuario == null ||
                usuario.getNome() == null ||
                usuario.getNome().isBlank() || 
                usuario.getSenha() == null ||
                usuario.getSenha().isBlank()) {
            throw new IllegalArgumentException("Usuário com atributos inválidos!");
        }
        if(!usuario.getAutorizacoes().isEmpty()) {
            Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();
            for(Autorizacao autorizacao: usuario.getAutorizacoes()) {
                Autorizacao autorizacaoBd = buscarAutorizacaoPorId(autorizacao.getId());
                autorizacoes.add(autorizacaoBd);
            }
            usuario.setAutorizacoes(autorizacoes);
        }
        Set<Anotacao> anotacoes = usuario.getAnotacoes();
        usuario.setAnotacoes(new HashSet<Anotacao>());
        usuario = usuarioRepo.save(usuario);
        for(Anotacao anotacao: anotacoes) {
            anotacao.setUsuario(usuario);
            anotacao = anotacaoRepo.save(anotacao);
            usuario.getAnotacoes().add(anotacao);
        }
        
        return usuario;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepo.findAll();
    }

    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
        if(usuarioOp.isEmpty()) {
            throw new IllegalArgumentException("Usuario nao encontrado!");
        }
        return usuarioOp.get();
    }

    public Autorizacao buscarAutorizacaoPorId(Long id) {
        Optional<Autorizacao> autorizacaoOp = autorizacaoRepo.findById(id);
        if(autorizacaoOp.isEmpty()) {
            throw new IllegalArgumentException("Autorizacao nao encontrada!");
        }
        return autorizacaoOp.get();
    }


}