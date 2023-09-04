package services;



import java.util.List;

import entity.Usuario;

public interface IUsuarioService {
    public Usuario buscarPorId(Long id);

    public Usuario novoUsuario (Usuario usuario);

    public List<Usuario> buscarTodos();
}
