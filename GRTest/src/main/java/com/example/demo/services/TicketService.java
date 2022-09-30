package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.TicketModel;
import com.example.demo.repositories.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	TicketRepository ticketRepository;
	
	public TicketModel createTicket(TicketModel ticket){
		
		return ticketRepository.save(ticket);
		
	}

	public List<TicketModel> getTickets(){
		
		return (ArrayList<TicketModel>) ticketRepository.findAll();
		
	}
	
	public TicketModel updateTicket(TicketModel ticket){
		
		return ticketRepository.save(ticket);
		
	}
	
	public void removeTicket(Long id){
		
		ticketRepository.deleteById(id);
		
	}
	
	public TicketModel getTicketById(Long id){
		
		return ((Optional<TicketModel>) ticketRepository.findById(id)).get();
		
	}
	
	public List<TicketModel> getTicketsByDateRange(Date from, Date to){
		return ticketRepository.getTicketsByDateRange(from, to);
	}
}
