package com.jh.mirai.http.plus.r6;



import java.io.Serializable;


public class Progression implements Serializable {
    private  Long level;

    private Long lootbox_probability;

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getLootbox_probability() {
        return lootbox_probability;
    }

    public void setLootbox_probability(Long lootbox_probability) {
        this.lootbox_probability = lootbox_probability;
    }
}
