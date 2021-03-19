package com.lwz.automsg.DTO;

import javax.xml.crypto.Data;

/**
 * @author 吕文哲
 */
public class RootDTO extends JsonDTO{
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "RootDTO{" +
                "data=" + data +
                '}';
    }
}