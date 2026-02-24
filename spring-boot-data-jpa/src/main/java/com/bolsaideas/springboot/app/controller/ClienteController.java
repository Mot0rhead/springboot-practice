package com.bolsaideas.springboot.app.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsaideas.springboot.app.models.Cliente;
import com.bolsaideas.springboot.app.models.dao.IClienteDao;
import com.bolsaideas.springboot.app.models.service.IClienteService;
import com.bolsaideas.springboot.app.models.service.IUploadFileService;
import com.bolsaideas.springboot.app.models.service.UploadFileServiceImpl;
import com.bolsaideas.springboot.app.util.paginator.PageRender;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	// @Qualifier("clienteDaoJPA")
	private IClienteService clienteService;
	@Autowired
	private IUploadFileService uploadFileServiceImpl;

	@GetMapping("/uploads/{filename:.+}") // :.+ hace que no borre la extension
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		/*
		 * log.info("pathFoto" + pathFoto); log.info("pathFotoAbsolute" + pathFoto);
		 * Resource recurso = null; try { recurso = new UrlResource(pathFoto.toUri());
		 * if(!recurso.exists() && !recurso.isReadable()) { throw new
		 * RuntimeException("Error, el recurso no existe"); } } catch
		 * (MalformedURLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } esta lógica de negocio ahora esta en el service
		 */
		Resource recurso = null;
		try {
			recurso = uploadFileServiceImpl.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "atachment; filename =\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping("/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		if (!foto.isEmpty()) {
			// Path directorioRecursos = Paths.get("C://uploads");
			// String rootPath = directorioRecursos.toFile().getAbsolutePath();
			// String rootPath="C://uploads";// in windows we should use a double //
			/*
			 * String uniqueFilename = UUID.randomUUID().toString()+ " " +
			 * foto.getOriginalFilename(); Path
			 * rootPath=Paths.get(UPLOADS_FOLDER).resolve(uniqueFilename); Path
			 * rootAbsolutePath = rootPath.toAbsolutePath();
			 * 
			 * log.info("rootPath: " + rootPath); log.info("rootAbsolutePath " +
			 * rootAbsolutePath);
			 * 
			 * try { /*byte[] bytes = foto.getBytes(); Path rutaCompleta =
			 * Paths.get(rootPath + "//" + foto.getOriginalFilename());
			 * Files.write(rutaCompleta,bytes); lo mismo que la linea siguiente
			 * Files.copy(foto.getInputStream(), rootAbsolutePath);
			 * flash.addFlashAttribute("info", "Has subido correctamente "+uniqueFilename
			 * +"  al servidor"); cliente.setFoto(uniqueFilename); } catch (IOException e) {
			 * // TODO Auto-generated catch block e.printStackTrace(); } Logica en el
			 * service
			 */
			uploadFileServiceImpl.delete(cliente.getFoto());
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileServiceImpl.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Has subido correctamente " + uniqueFilename + "  al servidor");
			cliente.setFoto(uniqueFilename);
		}
		String mensaje = (cliente.getId() != null) ? "Cliente editado con exito!" : "Cliente creado con éxito!";
		clienteService.save(cliente);
		flash.addFlashAttribute("succes", mensaje);
		status.setComplete();
		return "redirect:listar";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, RedirectAttributes flash, Map<String, Object> model) {

		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findById(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El cliente no existe");
				return "redirect:/listar";
			}

		} else {

			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findById(id);
			/*
			 * Path pathFoto =
			 * Paths.get(UPLOADS_FOLDER).resolve(cliente.getFoto()).toAbsolutePath();
			 * 
			 * 
			 * try { Files.deleteIfExists(pathFoto); flash.addAttribute("info","Foto " +
			 * cliente.getFoto()+ " eliminada con exito"); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } logica en el service
			 */

			boolean resultado = uploadFileServiceImpl.delete(cliente.getFoto());
			if (resultado) {
				flash.addAttribute("info", "Foto " + cliente.getFoto() + " eliminada con exito");
			}

			clienteService.delete(id);
			flash.addFlashAttribute("succes", "Cliente eliminado con éxito!");
		}

		return "redirect:../listar";
	}

	@GetMapping(value = "/ver/{id}")
	public String verImagen(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = clienteService.findById(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:../listar";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente: " + cliente.getNombre());
		return "ver";
	}

}