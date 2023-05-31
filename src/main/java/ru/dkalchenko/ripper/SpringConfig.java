package ru.dkalchenko.ripper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dkalchenko.ripper.quoters.TerminatorQuoter;

@Configuration
public class SpringConfig {

    @Bean
    public TerminatorQuoter createTerminatorQuoter() {
        TerminatorQuoter terminatorQuoter = new TerminatorQuoter();
        terminatorQuoter.setMessage("I'll be back");
        return terminatorQuoter;
    }
}
