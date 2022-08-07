package ru.netology.domain;

public class Ticket implements Comparable<Ticket> {

    private int id;
    private int price;
    private String from;
    private String to;
    private int time;

    public Ticket(int id, int price, String from, String to, int time) {
        this.id = id;
        this.price = price;
        this.from = from;
        this.to = to;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }


    @Override
    public int compareTo(Ticket comparePrice) {
        return this.price - comparePrice.price;
    }

    @Override
    public String toString() {
        return from;
    }
}
