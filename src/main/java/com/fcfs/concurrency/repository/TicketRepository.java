package com.fcfs.concurrency.repository;

import com.fcfs.concurrency.domain.ReservationStatus;
import com.fcfs.concurrency.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(Long id);
    List<Ticket> findByReservationStatus(ReservationStatus status);
}
