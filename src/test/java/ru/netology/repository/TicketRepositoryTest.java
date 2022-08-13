package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TicketRepositoryTest {
    private TicketRepository repository = new TicketRepository();
    private Ticket ticket1 = new Ticket(1, 1299, "SVO", "KZN", 90);
    private Ticket ticket2 = new Ticket(2, 2199, "VKO", "KZN", 95);
    private Ticket ticket3 = new Ticket(3, 6754, "SVO", "AER", 240);
    private Ticket ticket4 = new Ticket(4, 50_882, "DME", "SAW", 265);
    private Ticket ticket5 = new Ticket(5, 71_477, "AER", "MLE", 910);
    private Ticket ticket6 = new Ticket(6, 28_220, "AER", "DXB", 225);
    private Ticket ticket7 = new Ticket(7, 4658, "VKO", "LED", 85);


    @Test // показать один товар
    public void mustShowOne() {
        repository.save(ticket4);
        Ticket[] expected = new Ticket[]{ticket4};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test //показать несколько товаров
    public void mustShowNotEmpty() {
        repository.save(ticket1);
        repository.save(ticket2);
        Ticket[] expected = new Ticket[]{ticket1, ticket2};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test // показать пустой список
    public void mustShowEmpty() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }


}
