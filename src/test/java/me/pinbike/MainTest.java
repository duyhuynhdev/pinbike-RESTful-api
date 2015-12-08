package me.pinbike;

import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by hpduy17 on 12/5/15.
 */
public class MainTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void TestDataFactoryLibrary() {
        DataFactory df = new DataFactory();
        System.out.println("chance(10): " + df.chance(10));
        System.out.println("getAddress(): " + df.getAddress());
        System.out.println("getAddressLine2(): " + df.getAddressLine2());
        System.out.println("getAddressLine2(2): " + df.getAddressLine2(2));
        System.out.println("getAddressLine2(3, HCMC): " + df.getAddressLine2(3, "HCMC"));
        System.out.println("getBirthDate(): "+ df.getBirthDate());
        System.out.println("getBusinessName(): "+ df.getBusinessName());
        System.out.println("getCity(): " + df.getCity());
        System.out.println("getDate(): " + df.getDate(new Date(), 2, 10));
        System.out.println("getEmailAddress(): " + df.getEmailAddress());
        System.out.println("getFirstName(): " + df.getFirstName());
        System.out.println("getLastName(): " + df.getLastName());
        System.out.println("getName(): " + df.getName());
        System.out.println("getNumberText(2): " + df.getNumberText(2));
        System.out.println("getPrefix(4): " + df.getPrefix(4));
        System.out.println("getRandomChars(10): " + df.getRandomChars(10));
        System.out.println("getRandomChar(): " + df.getRandomChar());
        System.out.println("getRandomChars(3, 6): " + df.getRandomChars(3, 6));
        System.out.println("getRandomText(100): " + df.getRandomText(100));
        System.out.println("getRandomText(3, 6): " + df.getRandomText(3, 6));
        System.out.println("getRandomWord(): " + df.getRandomWord());
        System.out.println("getRandomWord(5): " + df.getRandomWord(50));
        System.out.println("getRandomWord(5, true): " + df.getRandomWord(5, true));
        System.out.println("getRandomWord(5, false): " + df.getRandomWord(5, false));
        System.out.println("getRandomWord(3, 6): " + df.getRandomWord(3, 6));
        System.out.println("getStreetName(): " + df.getStreetName());
        System.out.println("getStreetSuffix(): " + df.getStreetSuffix());
        System.out.println("getSuffix(4): " + df.getSuffix(4));
        System.out.println("getNumberBetween(3, 6): " + df.getNumberBetween(3, 6));
        System.out.println("getNumberUpTo(10): " + df.getNumberUpTo(10));
        System.out.println("getDate(2015,11,12): " + df.getDate(2015,11,12));
        System.out.println("getDateBetween(new Date(2015,11,12),new Date(2015,12,11)): " + df.getDateBetween(new Date(2015,11,12),new Date(2015,12,11)));
    }
}