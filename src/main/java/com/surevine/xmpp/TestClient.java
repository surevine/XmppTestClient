package com.surevine.xmpp;

import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.logging.LogManager;

public class TestClient {

    static final String USERNAME = "jane";
    static final String PASSWORD = "secret";
    static final String DOMAIN = "example.org";
    static final String HOST = "127.0.0.1";
    static final int PORT = 5222;
    static final String SASL_MECHANISM = "PLAIN";
    private static final SecurityMode SECURITY_MODE = SecurityMode.disabled;
    private static final boolean COMPRESSION_ENABLED = true;


    public static void main(String[] args) throws Exception {

        SmackConfiguration.DEBUG = true;

        try {
            InputStream is = TestClient.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(is);
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }

        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword(USERNAME, PASSWORD)
                .setXmppDomain(DOMAIN)
                .setHost(HOST)
                .setPort(PORT)
                .addEnabledSaslMechanism(SASL_MECHANISM)
                .setSecurityMode(SECURITY_MODE)
                .setCompressionEnabled(COMPRESSION_ENABLED)
                .build();

        XMPPTCPConnection connection = new XMPPTCPConnection(config);
        connection.setUseStreamManagement(false); // To reduce the output in the debug log.

        connection.connect();
        connection.login();

        Thread.sleep(Duration.ofMinutes(20).toMillis()); // Open connection for 20 minutes.

        connection.disconnect();
    }
}
