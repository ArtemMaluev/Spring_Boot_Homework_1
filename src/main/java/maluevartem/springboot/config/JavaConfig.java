package maluevartem.springboot.config;

import maluevartem.springboot.profile.DevProfile;
import maluevartem.springboot.profile.ProductionProfile;
import maluevartem.springboot.profile.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(value = "devProfile", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(value = "devProfile", havingValue = "false", matchIfMissing = true)
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
