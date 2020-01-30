package br.com.carol.eventos.Controller;

import br.com.carol.eventos.Model.eventosModel;
import br.com.carol.eventos.Service.eventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class eventosController {

    @Autowired
    private eventosService service;

    @GetMapping
    public  String mostrarHome(){return "index";}

    @PostMapping("/cadastrar")
    public String cadastrarEventos (eventosModel eventos){
        service.cadastrarEvento(eventos);
        return "index";
    }

    @GetMapping("/eventos")
    public ModelAndView listarEventos(){
        ModelAndView pagina = new ModelAndView("listarEventos");
        Iterable<eventosModel> eventos = service.listarEventos();
        pagina.addObject("eventos", eventos);
        return pagina;
    }

    @GetMapping("/buscar/evento")
    public String buscarEvento(){
        return "buscarEvento";
    }

    @GetMapping("/evento")
    public String listarEvento(@RequestParam("nome") String nome, Model model){
        eventosModel evento = service.listarEvento(nome);
        if(evento != null) {
            model.addAttribute("evento", evento);
            return "listarEvento";
        }
        else {
            model.addAttribute("msg", "O evento " + nome + " não foi encontrado!! procure novamente");
            return  "buscarEvento";
        }
    }

    @GetMapping("/evento/{nome}")
    public String listarEventoPorNome(@PathVariable("nome") String nome, Model model){
        eventosModel evento = service.listarEvento(nome);
        if(evento != null) {
            model.addAttribute("evento", evento);
            return "listarEvento";
        }
        else {
            model.addAttribute("msg", "O evento " + nome + " não foi encontrado!! procure novamente");
            return  "buscarEvento";
        }
    }
}