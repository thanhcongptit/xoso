/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho.bus;

import java.util.ArrayList;
import java.util.List;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.domain.DauCamDTO;
import com.soicaupro.thongkebacnho.domain.ScreenBachthuSpecialDTO;
import com.soicaupro.thongkebacnho.domain.ScreenThreeDaySpecialDTO;

/**
 *
 * @author conglt
 */
public class DauCamThreeDayBus {
    public List<DauCamDTO> getListAward(List<DauCamDTO> awards, List<ScreenThreeDaySpecialDTO> results) {
        
        List<DauCamDTO> rs = new ArrayList<>();
        
        for(DauCamDTO awardDTO: awards) {
            if(!checkExitAwardLoto(results, awardDTO)) {
                rs.add(awardDTO);
            }
        }
        
        return rs;
    }
    
    public List<DauCamDTO> getListAwardBachthu(List<DauCamDTO> awards, List<ScreenBachthuSpecialDTO> results) {
        
        List<DauCamDTO> rs = new ArrayList<>();
        
        for(DauCamDTO awardDTO: awards) {
            if(!checkExitAwardLoto1(results, awardDTO)) {
                rs.add(awardDTO);
            }
        }
        
        return rs;
    }
    
    private boolean checkExitAwardLoto1(List<ScreenBachthuSpecialDTO> results, DauCamDTO awardDTO) {
        boolean rs = true;
        
        for(ScreenBachthuSpecialDTO dTO : results) {
            
            String s1 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay1(), 1);
            String s2 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay2(), 2);
            String s3 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay3(), 3);
            
            if(!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2) 
                    || !CommonUtil.isEmptyString(s3)) {
                
                return false;
            }
        }
        
        return rs;
    }
    
    public boolean checkExitAwardLoto(List<ScreenThreeDaySpecialDTO> results, DauCamDTO awardDTO) {
        boolean rs = true;
        
        for(ScreenThreeDaySpecialDTO dTO : results) {
            
            String s1 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay1(), 1);
            String s2 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay2(), 2);
            String s3 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay3(), 3);
            
            if(!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2) 
                    || !CommonUtil.isEmptyString(s3)) {
                
                return false;
            }
        }
        
        return rs;
    }
}
