package com.jh.mirai.http.plus.r6;



import java.io.Serializable;


public class Stats implements Serializable {

    private General general;

    private Queue queue;

    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }
}
