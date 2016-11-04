/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.bean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import inet.constant.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hanm
 */
public class XSVuiMaxDayResult {

    private String day;
    private List<XSVuiChotSoResult> resultList;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<XSVuiChotSoResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<XSVuiChotSoResult> resultList) {
        this.resultList = resultList;
    }

    public static void main(String[] args) {
        List<XSVuiMaxDayResult> maxDayList = new ArrayList<XSVuiMaxDayResult>();
        XSVuiMaxDayResult dayResult = new XSVuiMaxDayResult();

        String day = "21/07/2015";
        List<XSVuiChotSoResult> resultList = new ArrayList<XSVuiChotSoResult>();
        XSVuiChotSoResult result1 = new XSVuiChotSoResult();
        result1.setBetNumber("25");
        result1.setBetCoin(10);
        result1.setBetType(Constants.CHOTSO_LO);
        result1.setReceiveCoin(40);
        resultList.add(result1);
        result1 = new XSVuiChotSoResult();
        result1.setBetNumber("06");
        result1.setBetCoin(5);
        result1.setBetType(Constants.CHOTSO_DE);
        result1.setReceiveCoin(350);
        resultList.add(result1);
        dayResult.setDay(day);
        dayResult.setResultList(resultList);
        maxDayList.add(dayResult);

        day = "23/07/2015";
        resultList = new ArrayList<XSVuiChotSoResult>();
        result1 = new XSVuiChotSoResult();
        result1.setBetNumber("60");
        result1.setBetCoin(5);
        result1.setBetType(Constants.CHOTSO_LO);
        result1.setReceiveCoin(20);
        resultList.add(result1);
        result1 = new XSVuiChotSoResult();
        result1.setBetNumber("06");
        result1.setBetCoin(2);
        result1.setBetType(Constants.CHOTSO_DE);
        result1.setReceiveCoin(140);
        resultList.add(result1);
        dayResult.setDay(day);
        dayResult.setResultList(resultList);
        maxDayList.add(dayResult);

        String jsonContent = new Gson().toJson(maxDayList);
        System.out.println("content = " + jsonContent);
    }

    public String toPrettyFormat(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }

    public static List<XSVuiMaxDayResult> getMaxDayResultListFromJson(String json) {
        Gson gson = new Gson();
        List<XSVuiMaxDayResult> jsonObject = new ArrayList<XSVuiMaxDayResult>();
        jsonObject = (List<XSVuiMaxDayResult>) gson.fromJson(json, jsonObject.getClass());
        return jsonObject;
    }
}
