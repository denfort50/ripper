package ru.dkalchenko.ripper.quoters;

import lombok.Setter;

@Setter
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    @Override
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("Message = " + message);
        }
    }
}
