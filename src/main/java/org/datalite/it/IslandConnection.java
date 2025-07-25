package org.datalite.it;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Properties;

public class IslandConnection {
    private static final int PORT = 22;
    private final JSch jschInstance;
    private final String host;
    private final String username;
    private final String password;
    private Session session;

    public IslandConnection(JSch jschInstance, String host, String username, String password) {
        this.jschInstance = jschInstance;
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public boolean connect() {
        Properties configuration = setHostKeyChecking();
        try {
            session = this.jschInstance.getSession(this.username,this.host,PORT);
            session.setPassword(this.password);
            session.setConfig(configuration);
            session.connect();
            return session.isConnected();
        } catch (JSchException e) {
            return false;
        }
    }

    public boolean isConnected() {
        return session != null && session.isConnected();
    }

    private static Properties setHostKeyChecking() {
        Properties configuration = new Properties();
        configuration.put("StrictHostKeyChecking","no");
        return configuration;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
