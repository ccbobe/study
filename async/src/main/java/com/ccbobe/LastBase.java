package com.ccbobe;

import org.springframework.stereotype.Component;

/**
 * @author ccbobe
 */
@Component(value = "LastBase")
public class LastBase implements Base {

    @Override
    public String run(String context) {
        return "=====>xxxx";
    }
}
