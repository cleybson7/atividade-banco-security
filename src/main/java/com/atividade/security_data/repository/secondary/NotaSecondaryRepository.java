package com.atividade.security_data.repository.secondary;

import com.atividade.security_data.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NotaSecondaryRepository extends JpaRepository<Nota, Long> {

    @Modifying
    @Transactional("secondaryTransactionManager")
    @Query(value = "INSERT INTO tb_nota (id, conteudo, prioridade) VALUES (:id, :conteudo, :prioridade)", nativeQuery = true)
    void insertWithId(@Param("id") Long id, @Param("conteudo") String conteudo, @Param("prioridade") String prioridade);
}

