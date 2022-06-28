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

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.CategoriaRepository;
import br.com.fiap.repository.MarcaRepository;
import br.com.fiap.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final String PRODUTO_FOLDER = "produto/";
	
	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	@Autowired
	public MarcaRepository marcaRepository;
	
	@GetMapping("/form")
	public String open(@RequestParam String page, 
					   @RequestParam(required = false) Long id,
					   @ModelAttribute("produtoModel") ProdutoModel produtoModel, 
					   Model model) {
		
		if("produto-editar".equals(page)) {
			model.addAttribute("produtoModel", produtoRepository.findById(id).get());
		}
		
		model.addAttribute("categorias", categoriaRepository.findAll());
		model.addAttribute("marcas", marcaRepository.findAll());
		
		return PRODUTO_FOLDER + page;
	}

	@GetMapping()
	public String findAll(Model model) {

		model.addAttribute("produtos", produtoRepository.findAll());
		return PRODUTO_FOLDER +  "produtos";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		
		model.addAttribute("produto", produtoRepository.findById(id).get());
		return PRODUTO_FOLDER +  "produto-detalhe";
	}
	
	@PostMapping()
	public String save(@Valid ProdutoModel produtoModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("categorias", categoriaRepository.findAll());
			return PRODUTO_FOLDER + "produto-novo";
		}
		
		produtoRepository.save(produtoModel);
		redirectAttributes.addFlashAttribute("messages", "Produto cadastrado com sucesso!");
		
		return "redirect:/produto";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") long id, @Valid ProdutoModel produtoModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("categorias", categoriaRepository.findAll());
			return PRODUTO_FOLDER + "produto-editar";
		}
		
		produtoModel.setId(id);
		produtoRepository.save(produtoModel);
		redirectAttributes.addFlashAttribute("messages", "Produto alterado com sucesso!");
		
		return "redirect:/produto";
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		
		produtoRepository.deleteById(id);
		redirectAttributes.addFlashAttribute("messages", "Produto excluído com sucesso!");

		return "redirect:/produto";
	}

}
