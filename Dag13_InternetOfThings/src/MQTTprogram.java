import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class MQTTprogram {

    public static MqttClient sampleClient;
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        String broker = "tcp://192.168.1.1:1883";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            sampleClient = new MqttClient(broker,  MqttClient.generateClientId(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);

            //Opgave 7 opretter simpelmqttcallback klasse. ---------------------------
            sampleClient.setCallback(new SimpleMqttCallBack());

            sampleClient.connect(connOpts);
            System.out.println("Connected");

            //Opgave 7
            sampleClient.subscribe("tele/grp3297/SENSOR");
            Thread.sleep(200000); // programmet køre i 200sek.

            //Køre metoden fra opgave 6. så vi kan tænde og slukke for blæseren. ---------
            //publishMessage(sampleClient,"cmnd/grp3297/Power1" ,"1");

            // put real stuff here        < -------- !!!!

            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    //opg6pgm
    public static void  publishMessage(MqttClient sampleClient,String topicsend,String content) throws MqttPersistenceException, MqttException {
        // Laver en publish på sampleClient med topic topicsend og indhold content.
        MqttMessage message = new MqttMessage();
        message.setPayload(content.getBytes());
        System.out.println(content.getBytes());
        sampleClient.publish(topicsend, message);
        System.out.println("Message published");
    }

}

