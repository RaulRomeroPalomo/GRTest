package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.TicketModel;
import com.example.demo.services.TicketService;

@RestController
public class TicketController {

	@Autowired
	TicketService ticketService;

	// creating a get mapping that retrieves all the ticket details from the database
	@GetMapping("/tickets")
	private List<TicketModel> getTickets() {
		return ticketService.getTickets();
	}

	// creating a get mapping that retrieves the detail of a specific ticket
	@GetMapping("/ticket/{ticketId}")
	private TicketModel getTicketById(@PathVariable("ticketId") Long ticketId) {
		return ticketService.getTicketById(ticketId);
	}

	// creating a delete mapping that deletes a specified ticket
	@DeleteMapping("/tickets/{ticketId}")
	private void removeTicket(@PathVariable("bookid") Long ticketId) {
		ticketService.removeTicket(ticketId);
	}

	// creating post mapping that post the ticket detail in the database
	@PostMapping("/ticketn")
	private TicketModel createTicket(@RequestBody TicketModel ticket) {
		return ticketService.createTicket(ticket);
	}

	// creating put mapping that updates the ticket detail
	@PutMapping("/ticketu")
	private TicketModel updateTicket(@RequestBody TicketModel ticket) {
		return ticketService.updateTicket(ticket);
	}

}
