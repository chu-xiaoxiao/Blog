package com.zyc.util;

/**
 * Created by YuChen Zhang on 17/10/26.
 */
public enum CRDU {
    Select(1),
    Insert(2),
    Update(3),
    Delete(4);
    private Integer input;
    CRDU(Integer input) {
        this.input = input;
    }

    public Integer getCRDU(){
        return input;
    }
}
