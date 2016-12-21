/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexoes;

import Modelo.SensorAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProtocoloActions {

    public void Conectar(int i) {

        String url = "http://192.168.0.100:8084/MyNewHome/webresources/sensor?sensorId=" + i;

        AcessoRest a = new AcessoRest(url);
        a.start();

        SensorAnswer s = new SensorAnswer();
        Gson g = new Gson();

        try {

            String json = a.sendGet();            
            Type retornoType = new TypeToken<SensorAnswer>(){}.getType();
            
            s = g.fromJson(json, retornoType);
            
            System.out.println(s.getValue());
            Thread.sleep(500);

        } catch (InterruptedException ex) {
            Logger.getLogger(AcessoRest.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(AcessoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
