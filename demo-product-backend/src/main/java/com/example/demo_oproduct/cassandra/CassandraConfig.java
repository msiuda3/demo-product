package com.example.demo_oproduct.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

import java.net.InetSocketAddress;

@Configuration
@Log
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "my_keyspace";
    }

    @Override
    protected String getLocalDataCenter() {
        return "datacenter1";
    }


    @Bean
    @Primary
    public CqlSession cqlSession() {
        log.info("Creating Cassandra CqlSession...");
        InetSocketAddress address = new InetSocketAddress("cassandra", 9042);
        log.info("Connecting to Cassandra at " + address);
        return CqlSession.builder()
                .addContactPoint(address)
                .withKeyspace("my_keyspace")
                .withLocalDatacenter("datacenter1")
                .build();
    }

    @Override
    protected String getContactPoints() {
        return "cassandra";
    }




}

