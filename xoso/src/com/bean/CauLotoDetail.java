/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bean;

/**
 *
 * @author hanhlm
 */
public class CauLotoDetail {
    private String loto;

    public String getLoto() {
        return loto;
    }

    public void setLoto(String loto) {
        this.loto = loto;
    }

    public LotteryResult getLotteryResult() {
        return lotteryResult;
    }

    public void setLotteryResult(LotteryResult lotteryResult) {
        this.lotteryResult = lotteryResult;
    }
    private LotteryResult lotteryResult;
}
