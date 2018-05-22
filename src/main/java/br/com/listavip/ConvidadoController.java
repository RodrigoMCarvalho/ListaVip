package br.com.listavip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.listavip.model.Convidado;
import br.com.listavip.repository.ConvidadoRepository;

@Controller
public class ConvidadoController {
	
	@Autowired
	ConvidadoRepository repository;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/listaconvidados")
	public String listaConvidados(Model model) {
		
		Iterable<Convidado> convidados = repository.findAll(); //obt�m os dados do BD
		
		model.addAttribute("convidados", convidados); //No Spring MVC, o envio de vari�veis para a view
													  //� realizada pelo Model - OBS: "convidados" � referente a view
		return "listaconvidados";
	}
}
