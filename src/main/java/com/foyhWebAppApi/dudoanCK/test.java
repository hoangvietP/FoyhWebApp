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
        String a= "helo";
        String[] k = a.split("m");
        System.out.println(k.length);
    }
}
