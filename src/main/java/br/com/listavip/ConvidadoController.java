package br.com.listavip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

		Iterable<Convidado> convidados = repository.findAll(); // obtém os dados do BD

		model.addAttribute("convidados", convidados); // No Spring MVC, o envio de variáveis para a view
														// é realizada pelo Model - OBS: "convidados" é referente a view
		return "listaconvidados";
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
			@RequestParam("telefone") String telefone) {
		
		Convidado convidado = new Convidado(nome, email, telefone);
		
		repository.save(convidado);

		return "redirect:/listaconvidados";

	}

}
