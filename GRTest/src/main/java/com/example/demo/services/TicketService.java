package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.TicketModel;
import com.example.demo.repositories.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	TicketRepository ticketRepository;

	public List<TicketModel> getTicketById(Long id){
		
		ticketRepository.findById(id);
		return null;
	}
}
