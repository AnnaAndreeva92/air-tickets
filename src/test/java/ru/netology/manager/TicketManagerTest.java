package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);

    Ticket ticket1 = new Ticket(1, 1299, "SVO", "KZN", 90);
    Ticket ticket2 = new Ticket(2, 2199, "VKO", "KZN", 95);
    Ticket ticket3 = new Ticket(3, 6754, "SVO", "AER", 240);
    Ticket ticket4 = new Ticket(4, 50_882, "DME", "SAW", 265);
    Ticket ticket5 = new Ticket(5, 71_477, "AER", "MLE", 910);
    Ticket ticket6 = new Ticket(6, 28_220, "AER", "DXB", 225);
    Ticket ticket7 = new Ticket(7, 4658, "VKO", "LED", 85);

    @BeforeEach
    public void setUp() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

    }

    @Test // Тест сохранить элементы
    public void saveItems() {
        Ticket[] expected = new Ticket[]{ticket1,ticket2,ticket3,ticket4,ticket5,ticket6};
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

    @Test //добавление одного элемента
    public void addingOneElement() {
        manager.add(ticket7);
        Ticket[] actual = repository.findAll();
        Ticket[] expected = new Ticket[]{ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // удаление одного элемента
    public void removingOneElement() {
        repository.removeById(4);
        Ticket[] expected = new Ticket[]{ticket1, ticket2, ticket3, ticket5,ticket6};
        Ticket[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
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
        assertArrayEquals(expected, manager.findAll("DME","SAW"));
    }

    @Test // поиск несуществующего
    void mustSearchIfNotExists() {
        Ticket[] expected = new Ticket[0];
        assertArrayEquals(expected, manager.findAll("MAM", "PAP"));
    }


}


