package com.foyhWebAppApi.dudoanCK.controllerAPIDD;


import com.foyhWebAppApi.Account.user.User;
import com.foyhWebAppApi.dudoanCK.model.thisMo;
import com.foyhWebAppApi.dudoanCK.service.serviceMo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class controllerAPI {
    @Autowired
    serviceMo service;

    @PostMapping("/dudoan")
    public JSONObject APIDD(@RequestBody JSONObject objec){
        JSONArray dt = new JSONArray();
        JSONArray object = (JSONArray) objec.get("data");
        for (int i=object.size()-1;i>=object.size()-4;i--){
            JSONObject dtd = (JSONObject) object.get(i);
            JSONArray ar1 = (JSONArray) dtd.get("dataday");
            JSONArray bh = (JSONArray) dtd.get("bh");
            JSONArray dtr = new JSONArray();
            dtr.add(ar1);
            dtr.add(bh);
            dt.add(dtr);
        }
        JSONObject data = new JSONObject();
        data.put("dataUser",dt);
        int[] rt= {10,15};
        int[] rt1= {11,15};
        thisMo mo1 = new thisMo(1,30,4,rt);
        thisMo mo2 = new thisMo(2,31,5,rt1);
        thisMo mo3 = new thisMo(3,28,6,rt);
        thisMo mo4 = new thisMo(4,29,7,rt);
        serviceMo sv= new serviceMo();
        JSONObject obj= sv.dataFT(sv.getDataUS(data));
        return obj;

    }
}
