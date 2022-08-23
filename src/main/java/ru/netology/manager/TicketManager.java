package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Objects;


public class TicketManager {
    protected TicketRepository repository;

    public TicketManager(TicketRepository repository) {

        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.save(ticket);
    }


    public Ticket[] findAll(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (ticket.getFrom().equalsIgnoreCase(from) && ticket.getTo().equalsIgnoreCase(to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }



    public Ticket[] showOffers() {
        Ticket[] result = repository.getAll();
        Arrays.sort(result);
        return result;
    }

}
