package com.mett.writeMe.repositories;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.mett.writeMe.ejb.LegalEstablishment;

public interface LegalEstablishmentRepository extends CrudRepository<LegalEstablishment,Integer> {
	List<LegalEstablishment> findAll();
	LegalEstablishment save(LegalEstablishment legal);
}

