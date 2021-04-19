package com.growfin.ticketingSystem.models.payloads;

public enum TicketStatus {
	
	OPEN ("OPEN"),
    WAITING_ON_CUSTOMER ("WAITING_ON_CUSTOMER"),
    CUSTOMER_RESPONDED ("CUSTOMER_RESPONDED"),
    RESOLVED ("RESOLVED"),
    CLOSED ("CLOSED");

    private final String name;

    TicketStatus(String name) {this.name = name;}

    public String getName() {return name;}

}
