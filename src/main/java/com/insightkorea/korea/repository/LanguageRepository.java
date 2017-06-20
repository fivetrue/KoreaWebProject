package com.insightkorea.korea.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.insightkorea.korea.model.Language;

public interface LanguageRepository extends Repository<Language, String> {

	@Query("SELECT DISTINCT lang FROM Language lang WHERE lang.name LIKE :name%")
	@Transactional(readOnly = true)
	Collection<Language> findByName(@Param("name") String name);
	
	@Query("SELECT DISTINCT lang FROM Language lang")
	@Transactional(readOnly = true)
	Collection<Language> getLanguages();
	
	void save(Language language);
}
