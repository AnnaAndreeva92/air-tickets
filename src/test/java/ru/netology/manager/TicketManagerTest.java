package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);
    private Ticket ticket1 = new Ticket(1, 1299, "SVO", "KZN", 90);
    private Ticket ticket2 = new Ticket(2, 2199, "VKO", "KZN", 95);
    private Ticket ticket3 = new Ticket(3, 6754, "SVO", "AER", 240);
    private Ticket ticket4 = new Ticket(4, 50_882, "DME", "SAW", 265);
    private Ticket ticket5 = new Ticket(5, 71_477, "AER", "MLE", 910);
    private Ticket ticket6 = new Ticket(6, 28_220, "AER", "DXB", 225);
    private Ticket ticket7 = new Ticket(7, 4658, "VKO", "LED", 85);

    @BeforeEach
    public void setUp() {
        repository.save(ticket1);
        repository.save(ticket2);
        repository.save(ticket3);
        repository.save(ticket4);
        repository.save(ticket5);
        repository.save(ticket6);

    }

    @Test // показывает все предложения
    public void mustShowOffers() {
        Ticket[] expected = new Ticket[]{ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = manager.showOffers();
        Arrays.sort(expected);
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // тест добавления еще одного элемента
    public void shouldAddOneMore() {
        manager.add(ticket7);
        Ticket[] actual = repository.findAll();
        Ticket[] expected = new Ticket[]{ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // Тест удаление одного элемента
    public void shouldRemoveExist() {
        repository.removeById(1);
        Ticket[] expected = new Ticket[]{ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test //сортировка по возрастанию
    public void ticketPriceAscending() {
        Ticket[] expected = new Ticket[]{ticket1, ticket2, ticket3, ticket6, ticket4, ticket5};
        Ticket[] actual = new Ticket[]{ticket1, ticket2, ticket3, ticket6, ticket4, ticket5};
        Arrays.sort(expected);
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));

    }

    @Test // сортировка по времени
    public void sortedByTime() {
        Ticket[] actual = new Ticket[]{ticket7, ticket1, ticket2, ticket6, ticket3, ticket4, ticket5};
        Ticket[] expected = new Ticket[]{ticket7, ticket1, ticket2, ticket6, ticket3, ticket4, ticket5};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // неверный аэропорт вылета
    public void wrongAirportOfDeparture() {
        Ticket[] actual = manager.findAll("", "STS");
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(actual));
    }

    @Test // неверный аэропорт прилета
    public void wrongAirportOfArrival() {
        Ticket[] actual = manager.findAll("FUF", "");
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(actual));
    }

    @Test //верный аэропорт прилета
    public void arrivalAirport() {
        Ticket[] actual = manager.findAll("SVO", "KZN");
        Ticket[] expected = new Ticket[]{ticket1};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // поиск имеющегося в списке
    void SearchIfExists() {
        Ticket[] expected = new Ticket[]{ticket4};
        assertArrayEquals(expected, manager.findAll("DME", "SAW"));
    }

    @Test // поиск несуществующего
    void mustSearchIfNotExists() {
        Ticket[] expected = new Ticket[0];
        assertArrayEquals(expected, manager.findAll("MAM", "PAP"));
    }

}


