package com.plunex.emilokan.utill;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class PriceValidator implements Predicate<Integer> {

    @Override
    public boolean test(Integer integer) {
        if(integer <= 0){
            return false;
        }
        return true;
    }
}
