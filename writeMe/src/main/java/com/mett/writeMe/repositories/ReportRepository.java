package com.mett.writeMe.repositories;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.mett.writeMe.ejb.Report;
/**
 * @author Mildred Guerra
 * Report Repository 
 *
 */
public interface ReportRepository extends CrudRepository<Report,Integer> {
	List<Report> findAll();
	Report save(Report report);
	List<Report> findAllByWrittingWrittingId(int idWritting);
}
