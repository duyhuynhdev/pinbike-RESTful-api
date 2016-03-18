package me.pinbike;

import com.pinride.pinbike.config.Const;
import com.pinride.pinbike.thrift.TConst;
import com.pinride.pinbike.thrift.TUser;
import me.pinbike.dao.ConstDao;
import me.pinbike.dao.UserDao;
import me.pinbike.sharedjava.model.base.GroupContact;
import me.pinbike.sharedjava.model.base.OfflineContact;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    public void TestSth() throws UnsupportedEncodingException {
        ConstDao.Const consts = new ConstDao.Const();
        consts.beginningCredit = 500 * 1000;
        consts.aroundDistance = 0.5;
        consts.coverImage = "http://www.pinbike.me/api/pinbike2/images/cover.png";
        consts.fanPage = "https://www.facebook.com/PinBikeMe/";
        consts.faqDriverLink = "http://www.pinbike.me/faq/driver/";
        consts.faqPassengerLink = "http://www.pinbike.me/faq/passenger/";
        consts.requestTimeout = 20;
        consts.website = "http://pinbike.me/?utm_source=in-app-about&utm_medium=in-app&utm_term=in-app&utm_content=in-app&utm_campaign=in-app";
        consts.termsLink = "http://pinbike.me/terms/";
        consts.privacyLink = "http://pinbike.me/privacy/";
        consts.groupContacts = getContacts();
        TConst tConst = new TConst();
        tConst.json = consts.toString();
        tConst.userModified = 3723;
        new ConstDao().update(tConst);
        System.out.println(new ConstDao().getConst().toString());
    }

    @Test
    public void verifyNormally() throws UnsupportedEncodingException {
        UserDao userDao = new UserDao();
        TUser user = userDao.getUserBySocial("huanpt8006@gmail.com", Const.PinBike.SocialType.EMAIL);
        user.verifiedStatus = Const.PinBike.VerifiedStatus.VERIFIED;
        System.out.println(user.toString());

    }

    public List<GroupContact> getContacts() {
        String HCM = "Quận 1 (Tùng)\n" +
                "0169 271 5716\n" +
                "Quận 11 (Huy)\n" +
                "0938879567\n" +
                "Quận Phú Nhuận (Tài)\n" +
                "0122 307 3719\n" +
                "Quận Bình Thạnh (Quang)\n" +
                "0164 433 3145\n" +
                "Quận Thủ Đức (Ngân)\n" +
                "0186 929 0902\n";
        String DaLat = "Trung tâm (Ngân)\n" +
                "0127 707 7717\n";
        String NhaTrang = "Trung tâm (Minh)\n" +
                "0167 960 7612";
        HashMap<String, String> map = new HashMap<>();
        map.put("Thành phố Hồ Chí Minh", HCM);
        map.put("Đà Lạt", DaLat);
        map.put("Nha Trang", NhaTrang);

        List<GroupContact> groupContacts = new ArrayList<>();
        for (String city : map.keySet()) {
            GroupContact gc = new GroupContact();
            gc.name = city;
            gc.contacts = new ArrayList<>();
            String[] contacts = map.get(city).split("\\n");
            for (int i = 0; i < contacts.length; i += 2) {
                OfflineContact contact = new OfflineContact();
                contact.name = contacts[i];
                contact.phone = contacts[i + 1];
                gc.contacts.add(contact);
            }
            groupContacts.add(gc);
        }
//        GetVerifiedContactOfflineAPI.Response response = new GetVerifiedContactOfflineAPI.Response();
//        response.groupContacts = groupContacts;
        return groupContacts;
    }

    @Test
    public void TestDataFactoryLibrary() {
        DataFactory df = new DataFactory();
        System.out.println("chance(10): " + df.chance(10));
        System.out.println("getAddress(): " + df.getAddress());
        System.out.println("getAddressLine2(): " + df.getAddressLine2());
        System.out.println("getAddressLine2(2): " + df.getAddressLine2(2));
        System.out.println("getAddressLine2(3, HCMC): " + df.getAddressLine2(3, "HCMC"));
        System.out.println("getBirthDate(): " + df.getBirthDate());
        System.out.println("getBusinessName(): " + df.getBusinessName());
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
        System.out.println("getDate(2015,11,12): " + df.getDate(2015, 11, 12));
        System.out.println("getDateBetween(new Date(2015,11,12),new Date(2015,12,11)): " + df.getDateBetween(new Date(2015, 11, 12), new Date(2015, 12, 11)));
    }
}