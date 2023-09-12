package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Autorizacao;

public interface AutorizacaoRepository  extends JpaRepository<Autorizacao, Long>{
    
}
