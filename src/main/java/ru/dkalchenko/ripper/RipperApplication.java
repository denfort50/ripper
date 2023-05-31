package ru.dkalchenko.ripper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.dkalchenko.ripper.quoters.TerminatorQuoter;

@SpringBootApplication
public class RipperApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RipperApplication.class, args);
        TerminatorQuoter terminatorQuoter = context.getBean(TerminatorQuoter.class);
        terminatorQuoter.sayQuote();
    }

}
