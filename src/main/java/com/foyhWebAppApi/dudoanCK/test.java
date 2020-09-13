package com.foyhWebAppApi.dudoanCK;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class test {
    private static FileWriter file;
    public static void main(String[] args) throws FileNotFoundException {
//        JSONParser parser = new JSONParser();
//        Object obj = null;
//        try {
//            obj = parser.parse(new FileReader("src/main/java/com/javainuse/dudoanCK/data_C.json"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        ArrayList<thisMo> dtUS= new ArrayList<>();
//        JSONObject jsonObject = (JSONObject) obj;
//        JSONArray dataUser= (JSONArray) jsonObject.get("dataUser");
//        for (int i=0;i<=dataUser.size()-1;i++){
//            JSONArray arrM= (JSONArray) dataUser.get(i);
//            JSONArray rt = (JSONArray) arrM.get(3);
//            String rt1= String.valueOf(rt.get(0));
//            String rt2= String.valueOf(rt.get(1));
//            String nameM= String.valueOf(arrM.get(0));
//            String Longdt= String.valueOf(arrM.get(1));
//            String LongM= String.valueOf(arrM.get(2));
//            int name = Integer.parseInt(nameM);
//            int longdt = Integer.parseInt(Longdt);
//            int longm= Integer.parseInt(LongM);
//            int rtt1= Integer.parseInt(rt1);
//            int rtt2= Integer.parseInt(rt2);
//            int[] rtt = {rtt1,rtt2};
//            thisMo mo1 = new thisMo(name,longm,longdt,rtt);
//
//
//            //lay bh
//            JSONArray bha= (JSONArray) arrM.get(4);
//            for (int j=0;j<=bha.size()-1;j++){
//
//            }
//
//            dtUS.add(mo1);
//        }
//        dtUS.stream().forEach(p-> System.out.println(p.toString()));


//        ArrayList<thisMo> dataUser= new ArrayList<>();
//        int[] rt= {13,19};
//        int[] rt1= {9,15};
//        int[] rt2= {6,12};
//        int[] rt3= {4,10};
//        // add BH
//        thisMo mo1 = new thisMo(1,33,5,rt);
//        thisMo mo2 = new thisMo(2,33,4,rt1);
//        thisMo mo3 = new thisMo(3,31,5,rt2);
//        thisMo mo4 = new thisMo(4,31,4,rt3);



//        String json = new Gson().toJson(dataUser);
//        JSONArray day= new JSONArray();
//
//        JSONArray bh= new JSONArray();
//        bh.add("1");
//        bh.add("2");
//        for (int i=0;i<=5;i++){
//            day.add(bh);
//        }
//
//
//
//        JSONArray ar = new JSONArray();
//        JSONArray ar1 = new JSONArray();
//        ar1.add(mo1.getWRT()[0]);
//        ar1.add(mo1.getWRT()[1]);
//
//
//        ar.add(mo1.getNameM());
//        ar.add(mo1.getLongMonth());
//        ar.add(mo1.getLongDT());
//        ar.add(ar1);
//        ar.add(day);
//
//        JSONArray dataUS= new JSONArray();
//        dataUS.add(ar);
//        dataUS.add(ar);
//
//
//
//        JSONObject ob = new JSONObject();
//        ob.put("dataUser", dataUS);
//        try {
//
//            // Constructs a FileWriter given a file name, using the platform's default charset
//            file = new FileWriter("src/main/java/com/javainuse/dudoanCK/data_C.json");
//            file.write(ob.toJSONString());
////            CrunchifyLog("Successfully Copied JSON Object to File...");
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        } finally {
//
//            try {
//                file.flush();
//                file.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                System.out.println("add data in data_method");
//            }
//        }

        System.out.println(java.time.LocalTime.now());
    }
}
