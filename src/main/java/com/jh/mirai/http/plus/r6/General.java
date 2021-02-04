package com.jh.mirai.http.plus.r6;



import java.io.Serializable;

/**
 * 全部
 */
public class General implements Serializable {
    private Long deaths;
    private Long kills;
    private Double kd;
    private Long losses;
    private Long wins;
    private Double wl;
    //致盲击杀
    private Long blind_kills;
    //近战击杀
    private Long melee_kills;
    //穿透击杀
    private Long penetration_kills;

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

    public Long getBlind_kills() {
        return blind_kills;
    }

    public void setBlind_kills(Long blind_kills) {
        this.blind_kills = blind_kills;
    }

    public Long getMelee_kills() {
        return melee_kills;
    }

    public void setMelee_kills(Long melee_kills) {
        this.melee_kills = melee_kills;
    }

    public Long getPenetration_kills() {
        return penetration_kills;
    }

    public void setPenetration_kills(Long penetration_kills) {
        this.penetration_kills = penetration_kills;
    }
}
