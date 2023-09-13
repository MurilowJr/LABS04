package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Anotacao;

public interface AnotacaoRepository  extends JpaRepository<Anotacao, Long>{
    
}
