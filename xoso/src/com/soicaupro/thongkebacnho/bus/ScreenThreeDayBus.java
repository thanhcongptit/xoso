/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho.bus;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.domain.ScreenThreeDaySpecialDTO;
import com.soicaupro.thongkebacnho.domain.SpecialAwardDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author conglt
 */
public class ScreenThreeDayBus {
    public List<SpecialAwardDTO> getListAward(List<SpecialAwardDTO> awards, List<ScreenThreeDaySpecialDTO> results) {
        
        List<SpecialAwardDTO> rs = new ArrayList<>();
        
        for(SpecialAwardDTO awardDTO: awards) {
            if(!checkExitAwardLoto(results, awardDTO)) {
                rs.add(awardDTO);
            }
        }
        
        return rs;
    }
    
    public boolean checkExitAwardLoto(List<ScreenThreeDaySpecialDTO> results, SpecialAwardDTO awardDTO) {
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
