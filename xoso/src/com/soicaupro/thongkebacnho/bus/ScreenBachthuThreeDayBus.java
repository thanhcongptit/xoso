/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho.bus;

import java.util.ArrayList;
import java.util.List;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.domain.ScreenBachthuSpecialDTO;
import com.soicaupro.thongkebacnho.domain.SpecialAwardDTO;

/**
 *
 * @author conglt
 */
public class ScreenBachthuThreeDayBus {
    public List<SpecialAwardDTO> getListAward(List<SpecialAwardDTO> awards, List<ScreenBachthuSpecialDTO> results) {
        
        List<SpecialAwardDTO> rs = new ArrayList<>();
        
        for(SpecialAwardDTO awardDTO: awards) {
            if(!checkExitAwardLoto(results, awardDTO)) {
                rs.add(awardDTO);
            }
        }
        
        return rs;
    }
    
    public boolean checkExitAwardLoto(List<ScreenBachthuSpecialDTO> results, SpecialAwardDTO awardDTO) {
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
}
