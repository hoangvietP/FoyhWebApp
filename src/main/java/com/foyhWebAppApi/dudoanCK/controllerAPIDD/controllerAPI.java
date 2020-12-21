package com.foyhWebAppApi.dudoanCK.controllerAPIDD;


import com.foyhWebAppApi.Account.jwt.JwtTokenProvider;
import com.foyhWebAppApi.Account.user.User;
import com.foyhWebAppApi.Account.user.UserDAO;
import com.foyhWebAppApi.Account.user.UserRepository;
import com.foyhWebAppApi.Account.user.UserService;
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
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class controllerAPI {
    @Autowired
    serviceMo service;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/dudoan")
    public JSONObject APIDD(@RequestBody JSONObject objec, @RequestHeader("Authorization") String Autho){

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
        serviceMo sv= new serviceMo();
        JSONObject obj= sv.dataFT(sv.getDataUS(data));
        char[] a = Autho.toCharArray();
        String jwt="";
        for (int i=0; i<=a.length-1;i++){
            if (a[i]!='"'){
                jwt+=a[i];
            }
        }
        Boolean da = tokenProvider.validateToken(jwt);
        if (da==true) {
            Long userId = tokenProvider.getUserIdFromJWT(jwt);
            try {
                JSONObject ob= new JSONObject();
                ob.put("data",obj.toString());
                JSONArray dtd = (JSONArray) obj.get("dataday");
                JSONArray bhrt = (JSONArray) obj.get("bhrt");
                JSONArray bhdt = (JSONArray) obj.get("bhdt");
                new UserService().upDateData(userId,dtd,bhrt,bhdt);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return obj;

    }
}
