package org.example;

import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/main/resources/data2.json";
        String strJson = readString(fileName);
        List<Employee> list = jsonToList(strJson);

        for (Employee element : list) {
            System.out.println(element.toString());
        }
    }

    public static String readString(String fileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
        }

        return result.toString();
    }

    public static List<Employee> jsonToList(String str) {
        List<Employee> list = new ArrayList<>();
        try {
            Object obj = new JSONParser().parse(str);
            JSONArray arr = (JSONArray) obj;
            Iterator iter = arr.iterator();
            while (iter.hasNext()) {
                Gson g = new Gson();
                Employee employee = g.fromJson(iter.next().toString(), Employee.class);
                list.add(employee);
            }
        } catch (Exception e) {
        }

        return list;
    }
}