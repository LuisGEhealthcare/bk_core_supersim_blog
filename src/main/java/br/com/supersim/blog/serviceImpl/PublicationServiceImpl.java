package br.com.supersim.blog.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.supersim.blog.DTO.PublicationDTO;
import br.com.supersim.blog.DTO.UserDTO;
import br.com.supersim.blog.entity.Category;
import br.com.supersim.blog.entity.Publication;
import br.com.supersim.blog.exception.PublicationException;
import br.com.supersim.blog.exception.UserException;
import br.com.supersim.blog.repository.PublicationRepository;
import br.com.supersim.blog.service.CategoryService;
import br.com.supersim.blog.service.PublicationService;
import br.com.supersim.blog.service.UserService;
import br.com.supersim.blog.utils.AmazonUtils;
import br.com.supersim.blog.utils.Utils;

@Service
public class PublicationServiceImpl implements PublicationService {
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;

	@Override
	public PublicationDTO save(PublicationDTO publicationDTO, MultipartFile multipartFile) throws UserException, PublicationException {
		
		UserDTO userPublication = userService.getUserByEmail(publicationDTO.getUserDTO().getEmail());
		
		if(userPublication == null) { throw new PublicationException("INVALID_USER_PUBLICATION");}
		
		Category category = categoryService.getCategoryByName(publicationDTO.getCategoryDTO().getName());
		
		if(category == null) { throw new PublicationException("INVALID_CATEGORY_PUBLICATION");}
		
		try {
			
			String photoKey = null;
			
			while(true) {
				photoKey = generatePhotoKey(multipartFile);
				Publication publication = publicationRepository.findByPhotoKey(photoKey);
				if(publication == null) {
					break;
				}
			}
			
			AmazonUtils.Upload(multipartFile, photoKey);
			Publication publication = new Publication(publicationDTO, photoKey);
			publicationRepository.save(publication);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public void delete(Long publicationId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PublicationDTO update(Publication publication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PublicationDTO> getAllByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String generatePhotoKey(MultipartFile multipartFile) {
		String alphaNumericString = Utils.randomAlphaNumericString(10);
		return alphaNumericString + multipartFile.getOriginalFilename();
	}

	@Override
	public PublicationDTO getPublicationById(Long id) throws IOException {
		
		Optional<Publication> publication = publicationRepository.findById(id);
		
		if(publication.isPresent() == false) { return null; }
		
		PublicationDTO publicationDTO = new PublicationDTO(publication.get());
		return publicationDTO;
	}



}
