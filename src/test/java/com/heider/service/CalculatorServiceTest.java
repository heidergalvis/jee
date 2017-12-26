package com.heider.service;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

    private ICalculatorService calculatorService;
    private MathApplication mathApplication;

    @Before
    public void before() {

        this.mathApplication = new MathApplication();
        this.calculatorService = mock(ICalculatorService.class);
        this.mathApplication.setCalculatorService(this.calculatorService);

    }

    @After
    public void after() {
        this.calculatorService = null;
    }

    @Test
    public void doubleOf() {

        int number = 4;
        int expectedNuymber = 8;

        when(this.calculatorService.doubleOf(4)).thenReturn(8);

        int actual = this.calculatorService.doubleOf(number);

        Assert.assertEquals(expectedNuymber, actual);

        //verify call to calcService is made or not
        verify(this.calculatorService).doubleOf(4);

    }
}
