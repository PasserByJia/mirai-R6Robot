package com.jh.mirai.http.plus.r6;




import java.io.Serializable;

public class Queue implements Serializable {

    private Casual casual;

    private Ranked ranked;

    public Casual getCasual() {
        return casual;
    }

    public void setCasual(Casual casual) {
        this.casual = casual;
    }

    public Ranked getRanked() {
        return ranked;
    }

    public void setRanked(Ranked ranked) {
        this.ranked = ranked;
    }
}
