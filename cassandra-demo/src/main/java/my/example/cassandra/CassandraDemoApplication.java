package my.example.cassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Don't forget to uncomment exclusions for Cassandra auto-configuration classes with this option. */
//@SpringBootApplication(scanBasePackages = "my.example.cassandra.nativeapi")
@SpringBootApplication(scanBasePackages = "my.example.cassandra.spring")
public class CassandraDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CassandraDemoApplication.class, args);
    }
}
