package com.foyhWebAppApi.Account.user;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.*;

public class connect_mysql {
    private String dtb = "";
    private String table;
    private String DB_URL = "";
    private String USER_NAME = "root";
    private String PASSWORD = "";
    Connection conn = null;

    public connect_mysql(String dtb, String USER_NAME, String PASSWORD) {
        this.dtb = dtb;
        this.USER_NAME = USER_NAME;
        this.PASSWORD = PASSWORD;
        this.DB_URL = "jdbc:mysql://localhost:3306/" + dtb + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    }

    public String[][] getdt(String query, int ccolum, int slcl, int[] type) throws SQLException {
        String[][] data = new String[slcl][ccolum];
        Connection connect = connect();
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int i = 0;
        while (rs.next()) {
            for (int j = 0; j <= ccolum - 1; j++) {
                if (type[j] == 0) {
                    data[i][j] = "" + rs.getInt(j + 1);
                }
                if (type[j] == 1) {
                    data[i][j] = "" + rs.getString(j + 1);
                }
            }
            i++;
        }
        connect.close();
        return data;
    }

    public JSONObject getDataUser(String userid) throws SQLException {
        Connection connect = connect();
        JSONObject dt = new JSONObject();
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM t_user WHERE id="+userid);
        while (rs.next()) {
            dt.put("birthday",rs.getString(4));
            dt.put("fullname",rs.getString(6));
            String[] bhdt = (rs.getString(2)).split(",");
            String[] bhrt = rs.getString(3).split(",");
            String[] dataday = rs.getString(5).split(",");
            JSONArray bhRT = new JSONArray();
            JSONArray bhDT = new JSONArray();
            JSONArray dataDAY = new JSONArray();
            if (bhdt.length>1 && bhrt.length>1 && dataday.length>1){
                JSONArray bh = new JSONArray();
                JSONArray bh1 = new JSONArray();
                for (int i=0;i<=bhrt.length-1;i++){
                    if (bhrt[i].equals("null") || bhdt[i].equals("null")){
                        bh.add(null);
                        bh1.add(null);
                    }
                    if (i==2 || i== 5 || i==8){
                        bhRT.add(bh);
                        bhDT.add(bh1);
                        bh=new JSONArray();
                        bh1=new JSONArray();
                    }
                    if (i<=4){
                        int number = Integer.parseInt(dataday[i]);
                        dataDAY.add(number);
                    }
                }
            }
            dt.put("dataday",dataDAY);
            dt.put("bhdt",bhDT);
            dt.put("bhrt",bhRT);
        }
        connect.close();
        return dt;
    }

    public JSONArray getALLDataUser()throws SQLException{
        Connection connect = connect();
        JSONArray ar = new JSONArray();
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM t_user ");
        while (rs.next()) {
            JSONObject dt = new JSONObject();
            dt.put("birthday",rs.getString(4));
            dt.put("fullname",rs.getString(6));
            String[] bhdt = (rs.getString(2)).split(",");
            String[] bhrt = rs.getString(3).split(",");
            String[] dataday = rs.getString(5).split(",");
            JSONArray bhRT = new JSONArray();
            JSONArray bhDT = new JSONArray();
            JSONArray dataDAY = new JSONArray();
            if (bhdt.length>1 && bhrt.length>1 && dataday.length>1){
                JSONArray bh = new JSONArray();
                JSONArray bh1 = new JSONArray();
                for (int i=0;i<=bhrt.length-1;i++){
                    bh.add(bhrt[i]);
                    bh1.add(bhdt[i]);
                    if (i==2 || i== 5 || i==8){
                        bhRT.add(bh);
                        bhDT.add(bh1);
                        bh=new JSONArray();
                        bh1=new JSONArray();
                    }
                    if (i<=4){
                        dataDAY.add(dataday[i]);
                    }
                }
            }
            dt.put("dataday",dataDAY);
            dt.put("bhdt",bhDT);
            dt.put("bhrt",bhRT);


            ar.add(dt);
        }
        connect.close();
        return ar;
    }

    public Boolean UpdateDataUser(JSONArray dtd,JSONArray bhrt,JSONArray bhdt,String userid)throws SQLException{
        Connection connection = connect();
        Statement st = connection.createStatement();

        JSONArray rt = (JSONArray) dtd.get(3);
        String dataday =""+dtd.get(0)+","+dtd.get(1)+","+dtd.get(2)+","+rt.get(0)+","+rt.get(1);

        JSONArray bhdt1 =(JSONArray) bhdt.get(0);
        JSONArray bhdt2 =(JSONArray) bhdt.get(1);
        JSONArray bhdt3 =(JSONArray) bhdt.get(2);

        JSONArray bhrt1 =(JSONArray) bhrt.get(0);
        JSONArray bhrt2 =(JSONArray) bhrt.get(1);
        JSONArray bhrt3 =(JSONArray) bhrt.get(2);

        String bhdtst =""+bhdt1.get(0)+","+bhdt1.get(1)+","+bhdt1.get(2)+","+bhdt2.get(0)+","+bhdt2.get(1)+","+bhdt2.get(2)+","+bhdt3.get(0)+","+bhdt3.get(1)+","+bhdt3.get(2);
        String bhrtst=""+bhrt1.get(0)+","+bhrt1.get(1)+","+bhrt1.get(2)+","+bhrt2.get(0)+","+bhrt2.get(1)+","+bhrt2.get(2)+","+bhrt3.get(0)+","+bhrt3.get(1)+","+bhrt3.get(2);;

        st.executeLargeUpdate("UPDATE `t_user` SET `dataday`='"+dataday+"',`bhrt`='"+bhdtst+"',`bhdt`='"+bhrtst+"' WHERE id="+userid);
        connection.close();
        return true;
    }



    public boolean insertData(String query){
        boolean status=false;
        try
        {
            Connection connection = connect();
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            connection.close();
            status=true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }


        return status;
    }
    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,
                    USER_NAME,
                    PASSWORD);
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

//    public static void main(String[] args) {
//        connect_mysql cnn= new connect_mysql("ten database","username","password");
//////        int[] ccl={1,1}   gia trị nhập vào mảng là 1 hoặc 0, 1 là kiểu varchar,char, 0 là int,   kiểu dữ liệu các cột cần lấy;
////        String[][] data =null;   mang data chua du lieu can lay;
////        data= cnn.getdt("query","so luong cot can lay int","so du lieu can lay",ccl);
////        boolean status= false;
////        status=cnn.insertData("query"); ínert thành công status=true và ngc lại
//    }
}
