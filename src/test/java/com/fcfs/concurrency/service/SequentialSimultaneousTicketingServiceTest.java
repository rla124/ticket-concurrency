package com.fcfs.concurrency.service;

import com.fcfs.concurrency.domain.TicketReservationAmount;
import com.fcfs.concurrency.repository.ReservationRepository;
import com.fcfs.concurrency.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class SequentialSimultaneousTicketingServiceTest {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void test() throws InterruptedException {
        //given
        int memberCount = 30;
        int ticketAmount = 10;

        final TicketReservationAmount ticketReservationAmount = new TicketReservationAmount(ticketAmount);
        SequentialTicketingService sequentialTicketingService = new SequentialTicketingService(ticketReservationAmount, ticketRepository, reservationRepository);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();
    }
}
