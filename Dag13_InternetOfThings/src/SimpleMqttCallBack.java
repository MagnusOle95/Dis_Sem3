
//opg7pgm

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

public class SimpleMqttCallBack implements MqttCallback {
    int status = 0;
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String res= new String(mqttMessage.getPayload());
        //Opgave 7, finder jasonfilens doubles frem. og printer dem ud.
        JSONObject modtagetJson = new JSONObject(res);
        JSONObject jsonObjekt =  modtagetJson.getJSONObject("AM2301");
        double temp = jsonObjekt.getDouble("Temperature");
        double humit = jsonObjekt.getDouble("Humidity");
        System.out.println("Temp: " + temp + " " + "Humit: " + humit);
        System.out.println(res); // Printer json fra sensor.

        //Opgave 8, starter blæser hvis luftfugtighed er over 85. og slukker når den er under.
        if (humit >= 85){
            MQTTprogram.publishMessage(MQTTprogram.sampleClient,"cmnd/grp3297/Power1" ,"1" );
        }
        else{
            MQTTprogram.publishMessage(MQTTprogram.sampleClient,"cmnd/grp3297/Power1" ,"0" );
        }
        // res indeholder en måling som et JSON-object
        // put real stuff here     < --------    !!!!!!!!!!
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // not used in this example
    }
}

