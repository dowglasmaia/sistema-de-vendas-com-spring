package com.maia.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maia.cursomc.domain.Cidade;
import com.maia.cursomc.domain.Estado;
import com.maia.cursomc.dto.CidadeDTO;
import com.maia.cursomc.dto.EstadoDTO;
import com.maia.cursomc.services.CidadeService;
import com.maia.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResources {

	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidService;

	//Endpoit - que retorna uma lista de estadoDTO
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = service.findAll();
		List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}
	/*Endpoint  -  Cidades com Base no ID do seu Estado*/
	@RequestMapping(value="/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> list = cidService.findByEstado(estadoId);
		List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

}
