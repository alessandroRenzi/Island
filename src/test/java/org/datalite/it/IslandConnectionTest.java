package org.datalite.it;

import com.jcraft.jsch.JSch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class IslandConnectionTest {
    private JSch jschInstance;

    @BeforeEach
    public void initializeTest(){
        this.jschInstance = Mockito.mock(JSch.class);
    }

    @Test
    public void testSshNewConnectionNotNull() {
        IslandConnection connection = new IslandConnection(this.jschInstance,"217.73.217.219","root","tI700#AP");
        assertNotNull(connection);
    }

    @Test
    public void testSshNewConnectionConnected(){
        IslandConnection connection = new IslandConnection(this.jschInstance,"217.73.217.219","root","tI700#AP");
        assertTrue(connection.connect());
    }

    @Test
    public void testSshNewConnectionFailedAuthentication(){
        IslandConnection connection = new IslandConnection(this.jschInstance,"217.73.217.219","wronguser","tI700#AP");
        assertFalse(connection.connect());
    }
}
