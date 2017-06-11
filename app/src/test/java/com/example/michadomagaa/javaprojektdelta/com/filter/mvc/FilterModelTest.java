package com.example.michadomagaa.javaprojektdelta.com.filter.mvc;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Michał Domagała on 2017-06-11.
 */
public class FilterModelTest extends TestCase {


    @Override
    protected void setUp() throws Exception{
        super.setUp();
    }


    @Test
    public void cleanValue() throws Exception {
        FilterModel model = new FilterModel();
        double delta = 0;
        assertEquals(50, model.cleanValue(50,100),delta);
        assertEquals(100, model.cleanValue(100,100),delta);
        assertEquals(100, model.cleanValue(111,100),delta);
        assertEquals(180, model.cleanValue(100,100),delta);
        assertEquals(100, model.cleanValue(111,100),delta);
    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }



}