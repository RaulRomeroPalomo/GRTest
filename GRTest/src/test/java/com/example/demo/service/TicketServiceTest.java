package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.models.TicketModel;
import com.example.demo.repositories.TicketRepository;
import com.example.demo.services.TicketService;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

	@Mock
	private TicketRepository ticketRepository;
	
	@InjectMocks
	private TicketService ticketService;
	
	@Test
	void getTickets_test() {
		List<TicketModel> tickets = new ArrayList<>();
		tickets.add(new TicketModel());
		
		when(ticketRepository.findAll()).thenReturn(tickets);
		
		List<TicketModel> expectedTickets = ticketService.getTickets();
		
		assertNotNull(tickets, "Unexpected response received");
		assertEquals(expectedTickets, tickets);
		verify(ticketRepository).findAll();
	}
	
	@Test
	void getTicketById_test() {
		TicketModel ticket = new TicketModel();
		ticket.setId(1L);
		
		when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.of(ticket));
		
		TicketModel expectedTicket = ticketService.getTicketById(ticket.getId());
		
		assertThat(expectedTicket).isSameAs(ticket);
		verify(ticketRepository).findById(ticket.getId());
	}
	
	@Test
	void getTicketById_notFound_test() {
		
		Long id = 1L;
		
		when(ticketRepository.findById(id)).thenReturn(Optional.empty());
		
		TicketModel ticket;
		try {
			ticket = ticketService.getTicketById(id);
		} catch (Exception e) {
			ticket = null;
		}
		
		assertNull(ticket, "Unexpected response received");
	}
	
	@Test
	void createTicket_test() {
		TicketModel ticket = new TicketModel();
		ticket.setId(1L);
		
		when(ticketRepository.save(ticket)).thenReturn(ticket);
		
		TicketModel expectedTicket = ticketService.createTicket(ticket);
		assertNotNull(expectedTicket, "Unexpected response received");
		assertEquals(ticket, expectedTicket, "Not the same ticket");
	}
	
	@Test
	void updateTicket_test() {
		TicketModel ticket = new TicketModel();
		ticket.setId(1L);
		
		when(ticketRepository.save(ticket)).thenReturn(ticket);
		
		TicketModel expectedTicket = ticketService.updateTicket(ticket);
		assertNotNull(expectedTicket, "Unexpected response received");
		assertEquals(ticket, expectedTicket, "Not the same ticket");
	}
	
	@Test
	void removeTicket_test() {
		TicketModel ticket = new TicketModel();
		ticket.setId(1L);
		
		lenient().when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.of(ticket));
		
		ticketService.removeTicket(ticket.getId());
		
		verify(ticketRepository).deleteById(ticket.getId());
	}
	
	@Test
	void getTicketsBetweenDates_test() {
		TicketModel ticket = new TicketModel();
		ticket.setId(1L);
		ticket.setCreationDate(new Date(System.currentTimeMillis()));
		List<TicketModel> tickets = new ArrayList<>();
		tickets.add(ticket);
		
		String dateStr="2015-03-31";
		
		when(ticketRepository.findByCreationDateBetween(Date.valueOf(dateStr), new Date(System.currentTimeMillis()))).thenReturn(tickets);
		
		List<TicketModel> expectedTickets = ticketService.getTicketsBetweenDates(Date.valueOf(dateStr), new Date(System.currentTimeMillis()));
		
		assertNotNull(tickets, "Unexpected response received");
		assertEquals(expectedTickets, tickets);
		verify(ticketRepository).findByCreationDateBetween(Date.valueOf(dateStr), new Date(System.currentTimeMillis()));
	}
}
