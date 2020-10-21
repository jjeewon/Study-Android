package com.jiwon.bmicalculator;

import org.junit.Assert;
import org.junit.Test;

public class BmiValueTest {

    @Test
    public void 생성시에전달Float값을소수2자리까지반올림해서반환한다(){
        BmiValue bmiValue = new BmiValue(20.00511f);
        Assert.assertEquals(20.01f, bmiValue.toFloat());
    }
}
