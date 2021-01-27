package com.bielik.quwitestapp.network.response;

import com.bielik.quwitestapp.model.Ticket;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketsResponse {

    @SerializedName("issues")
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

}
