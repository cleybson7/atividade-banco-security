package com.atividade.security_data.service;

import com.atividade.security_data.model.Nota;
import com.atividade.security_data.repository.primary.NotaPrimaryRepository;
import com.atividade.security_data.repository.secondary.NotaSecondaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaPrimaryRepository primaryRepository;

    @Autowired
    private NotaSecondaryRepository secondaryRepository;

    @Transactional("primaryTransactionManager")
    public Nota create(Nota nota) {
        Nota savedPrimary = primaryRepository.save(nota);
        secondaryRepository.insertWithId(savedPrimary.getId(), savedPrimary.getConteudo(), savedPrimary.getPrioridade().name());
        return savedPrimary;
    }

    public List<Nota> listAll() {
        return primaryRepository.findAll();
    }

    public Optional<Nota> findById(Long id) {
        return primaryRepository.findById(id);
    }

    @Transactional("primaryTransactionManager")
    public boolean delete(Long id) {
        if (primaryRepository.existsById(id)) {
            primaryRepository.deleteById(id);
            try {
                secondaryRepository.deleteById(id);
            } catch (Exception e) {
                System.err.println("Erro ao deletar no banco secundário: " + e.getMessage());
            }
            return true;
        }
        return false;
    }
}
