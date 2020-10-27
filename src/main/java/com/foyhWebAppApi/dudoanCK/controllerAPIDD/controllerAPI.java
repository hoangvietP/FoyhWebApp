package com.foyhWebAppApi.dudoanCK.controllerAPIDD;


import com.foyhWebAppApi.Account.user.User;
import com.foyhWebAppApi.dudoanCK.model.thisMo;
import com.foyhWebAppApi.dudoanCK.service.serviceMo;
import com.google.gson.Gson;
import net.sf.json.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class controllerAPI {
    @Autowired
    serviceMo service;

    @PostMapping("/dudoan")
    public JSONObject APIDD(@RequestBody JSONObject objec){
        JSONArray dt = new JSONArray();
        JSONObject dt1 = new JSONObject(objec);
        String dtt = dt1.toJSONString();
        JSONParser parser = new JSONParser();
        JSONObject json= new JSONObject();
        try {
            json = (JSONObject) parser.parse(dtt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray object = (JSONArray) json.get("data");

        for (int i=object.size()-1;i>=object.size()-4;i--){
            JSONObject dtd = (JSONObject) object.get(i);
            JSONArray ar1 = (JSONArray) dtd.get("dataday");
            JSONArray bh = (JSONArray) dtd.get("bh");
            ar1.add(bh);
            dt.add(ar1);
        }
        JSONObject data = new JSONObject();
        data.put("dataUser",dt);
//        System.out.println(data);
//        int[] rt= {10,15};
//        int[] rt1= {11,15};
//        thisMo mo1 = new thisMo(1,30,4,rt);
//        thisMo mo2 = new thisMo(2,31,5,rt1);
//        thisMo mo3 = new thisMo(3,28,6,rt);
//        thisMo mo4 = new thisMo(4,29,7,rt);
        serviceMo sv= new serviceMo();
        JSONObject obj= sv.dataFT(sv.getDataUS(data));
        return obj;

    }
}
