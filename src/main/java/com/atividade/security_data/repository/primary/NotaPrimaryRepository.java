package com.atividade.security_data.repository.primary;

import com.atividade.security_data.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaPrimaryRepository extends JpaRepository<Nota, Long> {
}
