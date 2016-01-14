package me.pinbike.dao.non_db;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by hpduy17 on 1/12/16.
 */
public class ActivationDaoTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetActivationCode() throws Exception {
        new ActivationDao().getActivationCode("0908587305");
    }

    @Test
    public void testActivateAccount() throws Exception {

    }
}