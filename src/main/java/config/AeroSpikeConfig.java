package config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import javafx.css.CssMetaData;
import javafx.css.Styleable;

import java.io.IOException;
import java.util.Properties;

public class AeroSpikeConfig {

    public AeroSpikeConfig() {
    }

    public static AerospikeClient getAeroSpikeClient() {

//        Properties properties = PropertiesConfig.getProperties();
//
//        String aeroSpikeIpAddress = properties.getProperty("aerospike.ipAddress");
//        String aeroSpikePort = properties.getProperty("aerospike.port");
        ClientPolicy policy = new ClientPolicy();
        policy.readPolicyDefault.socketTimeout = 50;
        policy.readPolicyDefault.totalTimeout = 110;
        policy.readPolicyDefault.sleepBetweenRetries = 10;
        policy.writePolicyDefault.socketTimeout = 200;
        policy.writePolicyDefault.totalTimeout = 450;
        policy.writePolicyDefault.sleepBetweenRetries = 50;
        return new AerospikeClient(policy,"172.28.128.3", 3000);
    }
}
