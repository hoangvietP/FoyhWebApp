package com.foyhWebAppApi.Account.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.io.File;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String fullname;
    private String birthday;
    private String dataday;
    private String bhrt;
    private String bhdt;

}
