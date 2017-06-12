package com.example.michadomagaa.javaprojektdelta;

import android.os.Environment;
import android.test.suitebuilder.annotation.SmallTest;

import com.example.michadomagaa.javaprojektdelta.com.filter.mvc.FilterModel;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Michał Domagała on 2017-06-12.
 */

public class FilterModelTest {

    @Test
    public void addition_isCorrect() throws Exception {
        FilterModel model = new FilterModel();
        double delta = 0;
        assertEquals(50, model.cleanValue(50,100),delta);
        assertEquals(100, model.cleanValue(100,100),delta);
        assertEquals(100, model.cleanValue(111,100),delta);
        
    }

}
