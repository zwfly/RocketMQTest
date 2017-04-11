package com.yrsd.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Main {

    public static final String broker = "tcp://mqf-i267tv48ae.mqtt.aliyuncs.com:1883";
    public static final String acessKey = "LTAIDagmU5PlE8GR";
    public static final String secretKey = "ZTrVFyB4K3G9RoZkS8LyyV2RTghyML";
    public static final String topic = "plug";
    public static final String grounpId = "GID_gee9dk40g2n6";
    MqttUtil client = null;

    public static void main(String[] args) {

        final String producerClientId = "GID_sdfgh78903420@@@5df" + Utils.getUUID();
        //final String consumerClientId = "GID_sdfgh78903420@@@ClientID_123455";
        String sign;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            final MqttClient sampleClient = new MqttClient(broker, producerClientId, persistence);
            final MqttConnectOptions connOpts = new MqttConnectOptions();
            System.out.println("Connecting to broker: " + broker);
            /**
             * 计算签名，将签名作为MQTT的password。
             * 签名的计算方法，参考工具类MacSignature，第一个参数是ClientID的前半部分，即GroupID
             * 第二个参数阿里云的SecretKey
             */
            sign = MacSignature.macSignature(producerClientId.split("@@@")[0], secretKey);
            connOpts.setUserName(acessKey);
            connOpts.setServerURIs(new String[]{broker});
            connOpts.setPassword(sign.toCharArray());
            connOpts.setCleanSession(false);
            connOpts.setKeepAliveInterval(100);
            sampleClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {
                    System.out.println("mqtt connection lost");
                    throwable.printStackTrace();
                    while (!sampleClient.isConnected()) {
                        try {
                            sampleClient.connect(connOpts);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    System.out.println("messageArrived:" + topic + "------" + new String(mqttMessage.getPayload()));

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            recv_data_send(topic, mqttMessage, sampleClient);
                        }
                    }).start();
                }

                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    //   System.out.println("deliveryComplete:" + iMqttDeliveryToken.getMessageId());

                }
            });
            sampleClient.connect(connOpts);
            sampleClient.subscribe(topic + "/g1/test123456D/", 0);
        } catch (Exception me) {
            me.printStackTrace();
        }

    }

    private static void recv_data_send(String tp, MqttMessage msg, MqttClient c) {

        msg.setQos(0);
        try {
            c.publish(topic + "/g1/test123456M/", msg);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
