package br.com.fiap.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	private static final String CATEGORIA_FOLDER = "categoria/";

	@Autowired
	public CategoriaRepository repository;
	
	@GetMapping("/form")
	public String open(@RequestParam String page, 
					   @RequestParam(required = false) Long id,
					   @ModelAttribute("categoriaModel") CategoriaModel categoriaModel, 
					   Model model) {
		
		if("categoria-editar".equals(page)) {
			model.addAttribute("categoriaModel", repository.findById(id).get());
		}
		
		return CATEGORIA_FOLDER + page;
	}
	
	@GetMapping()
	public String findAll(Model model) {

		model.addAttribute("categorias", repository.findAll());
		return CATEGORIA_FOLDER +  "categorias";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		
		model.addAttribute("categoria", repository.findById(id).get());
		return CATEGORIA_FOLDER +  "categoria-detalhe";
	}
	
	@PostMapping()
	public String save(@Valid CategoriaModel categoriaModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return CATEGORIA_FOLDER + "categoria-novo";
		}
		
		repository.save(categoriaModel);
		redirectAttributes.addFlashAttribute("messages", "Categoria cadastrada com sucesso!");
		
		return "redirect:/categoria";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") long id, @Valid CategoriaModel categoriaModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return CATEGORIA_FOLDER + "categoria-editar";
		}
		
		categoriaModel.setIdCategoria(id);
		repository.save(categoriaModel);
		redirectAttributes.addFlashAttribute("messages", "Categoria alterado com sucesso!");
		
		return "redirect:/categoria";
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		
		repository.deleteById(id);
		redirectAttributes.addFlashAttribute("messages", "Categoria excluída com sucesso!");

		return "redirect:/categoria";
	}
	
}
