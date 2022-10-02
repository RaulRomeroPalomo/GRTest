package com.example.demo.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.TicketModel;
import com.example.demo.services.TicketService;

@RestController
public class TicketController {

	@Autowired
	TicketService ticketService;

	// creating a get mapping that retrieves all the ticket details from the database
	@GetMapping("/tickets")
	private ResponseEntity<List<TicketModel>> getTickets() {
		return new ResponseEntity<List<TicketModel>>(ticketService.getTickets(), HttpStatus.OK);
	}

	// creating a get mapping that retrieves the detail of a specific ticket
	@GetMapping("/ticket/{ticketId}")
	private ResponseEntity<TicketModel> getTicketById(@PathVariable("ticketId") Long ticketId) {
		return new ResponseEntity<TicketModel>(ticketService.getTicketById(ticketId), HttpStatus.OK);
	}

	// creating a delete mapping that deletes a specified ticket
	@Transactional
	@DeleteMapping("/ticket/delete/{ticketId}")
	private ResponseEntity<Void> removeTicket(@PathVariable("ticketId") Long ticketId) {
		ticketService.removeTicket(ticketId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// creating post mapping that post the ticket detail in the database
	@Transactional
	@PostMapping("/ticket/new")
	private ResponseEntity<TicketModel> createTicket(@RequestBody TicketModel ticket) {
		return new ResponseEntity<TicketModel>(ticketService.createTicket(ticket), HttpStatus.OK);
	}

	// creating put mapping that updates the ticket detail
	@Transactional
	@PutMapping("/ticket/update")
	private ResponseEntity<TicketModel> updateTicket(@RequestBody TicketModel ticket) {
		return new ResponseEntity<TicketModel>(ticketService.updateTicket(ticket), HttpStatus.OK);
	}

	// creating a get mapping that retrieves tickets details created between 2 dates from the database
	@GetMapping("/tickets/between")
	private ResponseEntity<List<TicketModel>> getTicketsBetweenDates(@RequestParam Date from, @RequestParam Date to) {
		return new ResponseEntity<List<TicketModel>>(ticketService.getTicketsBetweenDates(from, to), HttpStatus.OK);
	}

}
