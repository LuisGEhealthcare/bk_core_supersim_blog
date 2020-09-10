package br.com.supersim.blog.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.supersim.blog.DTO.PublicationDTO;
import br.com.supersim.blog.entity.Publication;
import br.com.supersim.blog.exception.PublicationException;
import br.com.supersim.blog.exception.UserException;

public interface PublicationService {
	
	public PublicationDTO save(PublicationDTO publicationDTO, MultipartFile multipartFile) throws UserException, PublicationException;
	
	public void delete(Long publicationId);
	
	public PublicationDTO update(Publication publication);
	
	public List<PublicationDTO> getAllByUserId(Long userId);
	
	public PublicationDTO getPublicationById(Long id) throws IOException;

}
