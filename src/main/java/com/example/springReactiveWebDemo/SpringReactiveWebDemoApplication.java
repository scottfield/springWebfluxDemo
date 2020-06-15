package com.example.springReactiveWebDemo;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.time.Duration;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EnableR2dbcRepositories(basePackages = "com.example.dao")
public class SpringReactiveWebDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveWebDemoApplication.class, args);
    }

    @Bean(name = "connectionFactory")
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(DRIVER, "mysql")
                .option(HOST, "127.0.0.1")
                .option(USER, "root")
                .option(PORT, 3306)  // optional, default 3306
//                .option(PASSWORD, "database-password-in-here") // optional, default null, null means has no password
                .option(DATABASE, "controllercenter") // optional, default null, null means not specifying the database
                .option(CONNECT_TIMEOUT, Duration.ofSeconds(3)) // optional, default null, null means no timeout
                .option(SSL, true) // optional, default is enabled, it will be ignore if "sslMode" is set
//                .option(Option.valueOf("sslMode"), "verify_identity") // optional, default "preferred"
//                .option(Option.valueOf("sslCa"), "/path/to/mysql/ca.pem") // required when sslMode is verify_ca or verify_identity, default null, null means has no server CA cert
//                .option(Option.valueOf("sslKey"), "/path/to/mysql/client-key.pem") // optional, default null, null means has no client key
//                .option(Option.valueOf("sslCert"), "/path/to/mysql/client-cert.pem") // optional, default null, null means has no client cert
//                .option(Option.valueOf("sslKeyPassword"), "key-pem-password-in-here") // optional, default null, null means has no password for client key (i.e. "sslKey")
//                .option(Option.valueOf("tlsVersion"), "TLSv1.1,TLSv1.2,TLSv1.3") // optional, default is auto-selected by the server
                .option(Option.valueOf("zeroDate"), "use_null") // optional, default "use_null"
                .option(Option.valueOf("useServerPrepareStatement"), true) // optional, default false
                .build();
        ConnectionFactory connectionFactory = ConnectionFactories.get(options);
        return connectionFactory;
    }

}
