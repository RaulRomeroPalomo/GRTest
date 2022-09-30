package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.TicketModel;

@Repository
public interface TicketRepository extends CrudRepository<TicketModel, Long>{
	
}
