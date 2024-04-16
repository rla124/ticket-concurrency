package com.fcfs.concurrency.service;

import com.fcfs.concurrency.domain.Reservation;
import com.fcfs.concurrency.domain.ReservationOrder;
import com.fcfs.concurrency.domain.Ticket;
import com.fcfs.concurrency.repository.ReservationRepository;
import com.fcfs.concurrency.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TicketingService {

    private ReservationOrder reservationOrder;
    private TicketRepository ticketRepository;
    private ReservationRepository reservationRepository;

    @Transactional
    public void ticketing() {
        Ticket ticket = createTicket();
        handleReservation(ticket);
        ticketRepository.save(ticket);
    }

    private Ticket createTicket() {
        return ticketRepository.save(new Ticket());
    }

    private void handleReservation(Ticket ticket) {
        if (reservationOrder.isPossibleToReserve(ticket)) {
            ticket.succeed();
            reservationRepository.save(new Reservation(ticket.getId()));
        } else {
            ticket.failed();
        }
    }
}
