package ${project.javaCodeBaseDotDir};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ${launchClassSimpleName} {
    public static void main(String[] args) {
        SpringApplication.run(${launchClassSimpleName}.class, args);
    }
}