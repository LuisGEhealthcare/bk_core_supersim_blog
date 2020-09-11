package br.com.supersim.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.supersim.blog.DTO.PublicationDTO;
import br.com.supersim.blog.exception.PublicationException;
import br.com.supersim.blog.exception.UserException;
import br.com.supersim.blog.service.PublicationService;

@RestController
@RequestMapping("/publication")
public class PublicationController {
	
	@Autowired
	private PublicationService publicationService;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public PublicationDTO upload(
			@RequestPart(value = "publication", required = true) @Valid PublicationDTO publicationDTO, 
			@RequestPart(value = "file", required = true) MultipartFile multipartFile) throws UserException, PublicationException {
		return publicationService.save(publicationDTO, multipartFile);
	}
	
	@GetMapping("/{photoKey}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable String photoKey) throws IllegalStateException, IOException {
		return publicationService.getPhotoDownloadByKey(photoKey);
		
	}
	
	@GetMapping("/all")
	public List<PublicationDTO> allPublicationByUserEmail(@PathVariable String email){
		return null;
	}

}
