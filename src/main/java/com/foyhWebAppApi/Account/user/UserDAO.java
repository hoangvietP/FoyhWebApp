package com.foyhWebAppApi.Account.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import net.sf.json.JSON;
import org.json.simple.JSONObject;


@Entity
@Table(name = "t_user")
@Data
public class UserDAO {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String fullname;
    private String birthday;
    private String dataday;
    private String bhrt;
    private String bhdt;



}
