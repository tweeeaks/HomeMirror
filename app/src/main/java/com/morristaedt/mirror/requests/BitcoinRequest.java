package com.morristaedt.mirror.requests;

import retrofit.http.GET;

public interface BitcoinRequest {

    @GET("/api/v2/ticker/btcusd/")
    BitcoinResponse getBiminutelyTicker();
}
