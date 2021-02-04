package com.jh.mirai.http.plus.r6;


import java.io.Serializable;


public class Casual implements Serializable {
    private Long deaths;
    private Long kills;
    private Double kd;
    private Long losses;
    private Long wins;
    private Double wl;

    public Long getDeaths() {
        return deaths;
    }

    public void setDeaths(Long deaths) {
        this.deaths = deaths;
    }

    public Long getKills() {
        return kills;
    }

    public void setKills(Long kills) {
        this.kills = kills;
    }

    public Double getKd() {
        return kd;
    }

    public void setKd(Double kd) {
        this.kd = kd;
    }

    public Long getLosses() {
        return losses;
    }

    public void setLosses(Long losses) {
        this.losses = losses;
    }

    public Long getWins() {
        return wins;
    }

    public void setWins(Long wins) {
        this.wins = wins;
    }

    public Double getWl() {
        return wl;
    }

    public void setWl(Double wl) {
        this.wl = wl;
    }
}
