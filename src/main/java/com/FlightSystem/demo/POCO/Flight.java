package com.FlightSystem.demo.POCO;

import java.sql.Timestamp;

public class Flight implements POCO{
    public long id;
    public long airlineCompanyId;
    public int originCountryId;
    public int destinationCountryId;
    public Timestamp departureTime;
    public Timestamp landingTime;
    public int remainingTickets;

    public Flight(long id, long airlineCompanyId, int originCountryId, int destinationCountryId,
                  Timestamp departureTime, Timestamp landingTime, int remainingTickets) {
        this.id = id;
        this.airlineCompanyId = airlineCompanyId;
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.remainingTickets = remainingTickets;
    }

    public Flight() {
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineCompanyId=" + airlineCompanyId +
                ", originCountryId=" + originCountryId +
                ", destinationCountryId=" + destinationCountryId +
                ", departureTime=" + departureTime +
                ", landingTime=" + landingTime +
                ", remainingTickets=" + remainingTickets +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        Flight other = (Flight) obj;
        return (id==other.id
                && airlineCompanyId==other.airlineCompanyId
                && originCountryId==other.originCountryId
                && destinationCountryId==other.destinationCountryId
                && departureTime.equals(other.departureTime)
                && landingTime.equals(other.landingTime)
                && remainingTickets==other.remainingTickets);
    }
}
