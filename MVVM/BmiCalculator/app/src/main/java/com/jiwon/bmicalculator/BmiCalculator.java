package com.jiwon.bmicalculator;

import android.support.annotation.VisibleForTesting;

public class BmiCalculator {

    /**
     * BMI 값을 계산하여 반환한다.
     * @param heightInMeter BMI에 사용할 신장
     * @param weightInKg BMI에 사용할 체중
     * @return BMI 값
     */
    public BmiValue calculate(float heightInMeter, float weightInKg){
        if(heightInMeter < 0 || weightInKg < 0){
            throw new RuntimeException("키와 몸무게를 양수로 지정해주세요.");
        }
        float bmiValue = weightInKg / (heightInMeter * heightInMeter);
        return createValueObj(bmiValue);
    }

    @VisibleForTesting
    BmiValue createValueObj(float bmiValue){
        return new BmiValue(bmiValue);
    }
}
