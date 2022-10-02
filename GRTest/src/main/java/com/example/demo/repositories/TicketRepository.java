package com.example.demo.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.TicketModel;

@Repository
public interface TicketRepository extends CrudRepository<TicketModel, Long>{
	
	public List<TicketModel> findByCreationDateBetween(Date from, Date to);
	
}
