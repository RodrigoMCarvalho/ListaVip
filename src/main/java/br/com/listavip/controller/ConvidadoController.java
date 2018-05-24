package br.com.listavip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.enviadorEmail.EmailService;
import br.com.listavip.model.Convidado;
import br.com.listavip.service.ConvidadoService;

@Controller
public class ConvidadoController {
	
	@Autowired
	private ConvidadoService service;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/listaconvidados")
	public String listaConvidados(Model model) {

		Iterable<Convidado> convidados = service.ObterConvidados();

		model.addAttribute("convidados", convidados); // No Spring MVC, o envio de variáveis para a view
													  // é realizada pelo Model - OBS: "convidados" é referente a view
		return "listaconvidados";
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
			@RequestParam("telefone") String telefone) {
		
		Convidado convidado = new Convidado(nome, email, telefone);
		service.salvar(convidado);
		
		new EmailService().enviar(nome, email); //Jar criado para o envio de email (enviadorEmail)

		return "redirect:/listaconvidados"; //após persistir os dados, redireciona para a página
	}
	
	@RequestMapping("/deletar{id}")
	public String excluir(@PathVariable Long id) {
		service.excluirPorId(id);
		
		return "redirect:/listaconvidados";
	}
	
	@RequestMapping(value = "/editar{id}", method = RequestMethod.PUT)
	public String editar(@PathVariable Long idUsuario, @RequestBody Convidado convidado) {

//		service.editar(idUsuario, convidado);
	
		return "/editar";

	}
	
	

}
