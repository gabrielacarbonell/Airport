/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.utils;

/**
 *
 * @author Gabyy
 */
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class JsonController {
    public static ArrayList<ArrayList<Object>> readJson(String archivo){
        ArrayList<ArrayList<Object>> jsonC = new ArrayList<>(); 
        try {
            String contenido = new String(Files.readAllBytes(Paths.get("./json/"+archivo+".json")));

            // Convertir a JSONArray
            JSONArray jsons = new JSONArray(contenido);
            Map<String,Object> jsonP = jsons.getJSONObject(0).toMap();
            Set<String> claves = jsonP.keySet();
            
            ArrayList<Object> valores = new ArrayList<>();
            for (int i = 0; i < jsons.length(); i++) {
                JSONObject json = jsons.getJSONObject(i);
                for (String clave : claves) {
                    try{
                        valores.add(json.getString(clave));
                    }catch(Exception e){
                        valores.add(json.getInt(clave));
                    }
                }
                jsonC.add(valores);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("Error al procesar el JSON: " + e.getMessage());
        }
        return jsonC;
    }
}
