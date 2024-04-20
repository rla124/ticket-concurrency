package com.fcfs.concurrency.service;

import com.fcfs.concurrency.domain.Reservation;
import com.fcfs.concurrency.domain.Ticket;
import com.fcfs.concurrency.domain.TicketReservationAmount;
import com.fcfs.concurrency.repository.ReservationRepository;
import com.fcfs.concurrency.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SequentialTicketingService {

    private TicketReservationAmount reservation;
    private TicketRepository ticketRepository;
    private ReservationRepository reservationRepository;

    @Transactional
    public void ticketing() {
        Ticket ticket = createTicket();
        if (reservation.isPossibleToReserve(ticket)) {
            reservation.increaseReservedAmount();
            ticket.succeed();
            ticketRepository.save(ticket);
            reservationRepository.save(new Reservation(ticket.getId()));
            return;
        }
        ticket.failed();
        throw new IllegalStateException("예약이 불가능합니다.");
    }

    public Ticket createTicket() {
        Ticket ticket = new Ticket();
        return ticketRepository.save(ticket);
    }
}
