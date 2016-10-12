package com._xxprodudexx_.easybans.api;

public enum BanType {

    BAN("Ban", "Never"), KICK("Kick", "Now");

    private String name;
    private String expires;

    BanType(String name, String expires){
        this.name = name;
        this.expires = expires;
    }

    public String getName() {
        return name;
    }

    public String getExpires() {
        return expires;
    }
}

