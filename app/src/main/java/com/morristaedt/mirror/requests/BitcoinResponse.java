package com.morristaedt.mirror.requests;

public class BitcoinResponse {

    public String last;
    public String high;
    public String low;
    public String volume;

    public String roundedLast() {
        Float f = Float.parseFloat(last);
        return String.valueOf(Math.round(f));
    }

    public String roundedHigh() {
        Float f = Float.parseFloat(high);
        return String.valueOf(Math.round(f));
    }

    public String roundedLow() {
        Float f = Float.parseFloat(low);
        return String.valueOf(Math.round(f));
    }

    public String roundedVolume() {
        Float f = Float.parseFloat(volume);
        return String.valueOf(Math.round(f));
    }

}
