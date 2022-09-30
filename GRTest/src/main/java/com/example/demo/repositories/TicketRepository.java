package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.TicketModel;

@Repository
public interface TicketRepository extends CrudRepository<TicketModel, Long>{
	
	@Query("SELECT t FROM TICKETS t where t.creationDate > ?1 AND t.creationDate < ?2")
	public List<TicketModel> getTicketsByDateRange(Date from, Date to);
	
}
