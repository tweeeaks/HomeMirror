package com.morristaedt.mirror.modules;

import android.os.AsyncTask;
import android.util.Log;

import com.morristaedt.mirror.requests.BitcoinRequest;
import com.morristaedt.mirror.requests.BitcoinResponse;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class BitcoinModule {

    public interface BitcoinListener {
        void getBitstampLast(String last);
        void getBitstampDetails(String details);
    }

    /**
     *
     * Docs:
     * https://www.bitstamp.net/api/
     * Do not make more than 600 requests per 10 minutes or we will ban your IP address.
     *
     * Api endpoint:
     * https://www.bitstamp.net/api/v2/ticker/btcusd/
     *
     */
    public static void getBitcoinIOBiminutelyTicker(final BitcoinListener listener) {
        new AsyncTask<Void, Void, BitcoinResponse>() {

            @Override
            protected BitcoinResponse doInBackground(Void... params) {
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("https://www.bitstamp.net")
                        .build();

                BitcoinRequest service = restAdapter.create(BitcoinRequest.class);

                try {
                    return service.getBiminutelyTicker();
                } catch (RetrofitError error) {
                    Log.w("BitcoinModule", "Bitcoin error: " + error.getMessage());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(BitcoinResponse bitcoinResponse) {
                if (bitcoinResponse != null) {
                    if (bitcoinResponse.last != null) {
                        listener.getBitstampLast("$" + bitcoinResponse.roundedLast());
                    }
                    if (bitcoinResponse.high != null && bitcoinResponse.low != null) {
                        listener.getBitstampDetails("$" + bitcoinResponse.roundedHigh() + " / $" + bitcoinResponse.roundedLow() + "\n" + bitcoinResponse.roundedVolume() + " v");
                    }
                }
            }
        }.execute();

    }
}
