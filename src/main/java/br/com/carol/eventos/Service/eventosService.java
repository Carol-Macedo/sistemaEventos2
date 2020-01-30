package br.com.carol.eventos.Service;

import br.com.carol.eventos.Model.eventosModel;
import br.com.carol.eventos.Repository.eventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class eventosService {

    @Autowired
    private eventosRepository repository;
    private Object eventosModel;

    public eventosModel cadastrarEvento(eventosModel evento) {
        evento.setAtivo(Boolean.TRUE);
        evento.setDataCriacao(LocalDate.now());
        return (br.com.carol.eventos.Model.eventosModel) repository.save(evento);
    }

    public Iterable<eventosModel> listarEventos() {
        Iterable<eventosModel> eventos = repository.findAll();
        return eventos;
    }

    public eventosModel listarEvento(String nome) {
        Optional<eventosModel> evento = repository.findByNome(nome);
        if(evento.isPresent()){
            return evento.get();
        }
        return null;
    }
}

