package com.foyhWebAppApi.dudoanCK.controllerAPIDD;


import com.foyhWebAppApi.dudoanCK.model.thisMo;
import com.foyhWebAppApi.dudoanCK.service.serviceMo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class controllerAPI {
    @Autowired
    serviceMo service;

    @PostMapping("/dudoan")
    public JSONObject APIDD(){
        int[] rt= {10,15};
        int[] rt1= {11,15};
        thisMo mo1 = new thisMo(1,30,4,rt);
        thisMo mo2 = new thisMo(2,31,5,rt1);
        thisMo mo3 = new thisMo(3,28,6,rt);
        thisMo mo4 = new thisMo(4,29,7,rt);
        serviceMo sv= new serviceMo();
        sv.SetJsondt(mo1,mo2,mo3,mo4);
        JSONObject obj= sv.dataFT(sv.getDataUS(sv.getJsonDT()));

        return obj;

    }
}
