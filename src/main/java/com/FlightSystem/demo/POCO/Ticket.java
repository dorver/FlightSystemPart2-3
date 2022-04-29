package com.FlightSystem.demo.POCO;

public class Ticket implements POCO{
    public long id;
    public long flightId;
    public long customerId;

    public Ticket(long id, long flightId, long customerId) {
        this.id = id;
        this.flightId = flightId;
        this.customerId = customerId;
    }

    public Ticket() {
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", customerId=" + customerId +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Ticket other = (Ticket) obj;
        return (id==other.id
                && flightId==other.flightId
                && customerId==other.customerId);
    }
}
