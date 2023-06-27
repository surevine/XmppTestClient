package com.surevine.xmpp;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.logging.LogManager;

public class TestClient {

    public static void main(String[] args) throws Exception {

        SmackConfiguration.DEBUG = true;

        try {
            InputStream is = TestClient.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(is);
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }

        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword("jane", "secret")
                .setXmppDomain("example.org")
//            .setResource("test")
                .setHost("127.0.0.1")
                .setPort(5222)
                .addEnabledSaslMechanism("PLAIN")
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .build();

        XMPPTCPConnection connection = new XMPPTCPConnection(config);
        connection.setUseStreamManagement(false); // To reduce output in the debug log.

        connection.connect();
        connection.login();


        Thread.sleep(Duration.ofMinutes(20).toMillis());

        connection.disconnect();
    }
}
