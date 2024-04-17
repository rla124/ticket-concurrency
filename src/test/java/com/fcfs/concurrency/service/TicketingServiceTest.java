package com.fcfs.concurrency.service;

import com.fcfs.concurrency.domain.ReservationOrder;
import com.fcfs.concurrency.repository.ReservationRepository;
import com.fcfs.concurrency.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class TicketingServiceTest {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void test() throws InterruptedException {
        //given
        int memberCount = 30;
        int ticketAmount = 10;

        ReservationOrder ticketReservationAmount = new ReservationOrder(ticketAmount);
        TicketingService ticketingService = new TicketingService(ticketReservationAmount, ticketRepository, reservationRepository);

        ExecutorService executorService = Executors.newFixedThreadPool(memberCount);
        CountDownLatch latch = new CountDownLatch(memberCount);

    }
}
