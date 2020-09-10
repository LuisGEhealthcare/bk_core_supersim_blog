package br.com.supersim.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supersim.blog.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

	Publication findByPhotoKey(String photoKey);
	
}
