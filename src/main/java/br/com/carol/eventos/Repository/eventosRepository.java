package br.com.carol.eventos.Repository;;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface eventosRepository<eventosModel> extends CrudRepository<eventosModel, String> {

    Optional<eventosModel> findByNome(String nome);

}
