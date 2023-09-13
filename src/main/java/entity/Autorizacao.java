package entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "aut_autorizacao")
public class Autorizacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aut_id")
    private Long id;

    @Column(name = "aut_nome")
    private String nome;

    @ManyToMany(mappedBy = "autorizacoes", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Usuario> usuarios;

    public Autorizacao(){}


public Autorizacao(String nome) {
    this.nome = nome;
}


public Long getId() {
    return id;
}


public void setId(Long id) {
    this.id = id;
}


public String getNome() {
    return nome;
}


public void setNome(String nome) {
    this.nome = nome;
}


public Set<Usuario> getUsuarios() {
    return usuarios;
}


public void setUsuarios(Set<Usuario> usuarios) {
    this.usuarios = usuarios;
}
    
}
