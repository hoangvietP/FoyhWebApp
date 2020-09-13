package com.foyhWebAppApi.dudoanCK;


import com.foyhWebAppApi.dudoanCK.model.thisMo;
import com.foyhWebAppApi.dudoanCK.service.serviceMo;

public class st {
    public static void main(String[] args) {
//        //setdata
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
//        //set data to test BH
//        for (int i=0;i<=32;i++){
//            String[] bh={"bh1","bh2","bh3"};
//            String[] bh1={"bh11","bh22"};
//            mo1.addBH(bh1,i);
//            mo2.addBH(bh,i);
//            mo3.addBH(bh,i);
//            mo4.addBH(bh,i);
//        }
//        //add dataMonth to data user
//        dataUser.add(mo1);
//        dataUser.add(mo2);
//        dataUser.add(mo3);
//        dataUser.add(mo4);

        System.out.println("start at: "+java.time.LocalTime.now());
        int[] rt= {10,15};
        int[] rt1= {11,15};
        thisMo mo1 = new thisMo(1,30,4,rt);
        thisMo mo2 = new thisMo(2,31,5,rt1);
        thisMo mo3 = new thisMo(3,28,6,rt);
        thisMo mo4 = new thisMo(4,29,7,rt);
        serviceMo sv= new serviceMo();
        sv.SetJsondt(mo1,mo2,mo3,mo4);
        sv.dataFT(sv.getDataUS(sv.getJsonDT()));
        System.out.println("finish at: "+java.time.LocalTime.now());

    }
}
