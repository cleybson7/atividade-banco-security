package com.atividade.security_data.service;

import com.atividade.security_data.model.Nota;
import com.atividade.security_data.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository repository;

    public Nota create(Nota nota) {
        return repository.save(nota);
    }

    public List<Nota> listAll() {
        return repository.findAll();
    }

    public Optional<Nota> findById(Long id) {
        return repository.findById(id);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
