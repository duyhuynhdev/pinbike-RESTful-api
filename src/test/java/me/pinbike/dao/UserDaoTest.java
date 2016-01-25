package me.pinbike.dao;

import com.pinride.pinbike.config.Const;
import com.pinride.pinbike.thrift.TUser;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.sharedjava.model.base.LatLng;
import me.pinbike.sharedjava.model.base.UserDetail;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hpduy17 on 12/30/15.
 */
public class UserDaoTest {
    private UserDao dao = new UserDao();
    private ModelDataFactory factory = new ModelDataFactory();
    private long userId;

    @Test
    public void testGetDriverAround() throws Exception {
        LatLng latLng = new LatLng();
        latLng.lat = 10.101;
        latLng.lng = 106.201;
        for (int i = 0; i < 5; i++) {
            UserDetail userDetail = factory.getUserDetail();
            TUser user = new TUser();
            user.avatar = userDetail.avatar;
            user.email = userDetail.email;
            user.socialId = userDetail.email;
            user.socialType = Const.PinBike.SocialType.EMAIL;
            user.intro = userDetail.intro;
            user.name = userDetail.givenName;
            user.lastName = userDetail.familyName;
            user.middleName = userDetail.middleName;
            user.phone = userDetail.phone;
            user.birthday = userDetail.birthday;
            user.currentBikeId = userDetail.currentBikeId;
            user.availableDriver = true;
            user.dateCreated = userDetail.joinedDate;
            user.sex = userDetail.sex;
            user.status = AC.UpdatedStatus.AVAILABLE;
            user = dao.insert(user);
            dao.updateLocation(user.userId, factory.getLocation(latLng.lat, latLng.lng, 0.05));
            System.out.println(dao.get(user.userId).currentLocation.toString());
        }
        System.out.println(latLng.toString());
        int size = dao.getDriverAround(latLng).size();
        System.out.println(size);
        assert  size == 5;
    }

    @Test
    public void testUpdateAvailableDriver() throws Exception {

    }

    @Test
    public void testUpdateLocation() throws Exception {

    }

    @Test
    public void testInsert() throws Exception {
        UserDetail userDetail = factory.getUserDetail();
        TUser user = new TUser();
        user.avatar = userDetail.avatar;
        user.email = userDetail.email;
        user.socialId = userDetail.email;
        user.socialType = Const.PinBike.SocialType.EMAIL;
        user.intro = userDetail.intro;
        user.name = userDetail.givenName;
        user.lastName = userDetail.familyName;
        user.middleName = userDetail.middleName;
        user.phone = userDetail.phone;
        user.birthday = userDetail.birthday;
        user.currentBikeId = userDetail.currentBikeId;
        user.availableDriver = userDetail.isAvailable;
        user.dateCreated = userDetail.joinedDate;
        user.sex = userDetail.sex;
        user.status = userDetail.status;
        // insert
        System.out.println("[INPUT]" + user.toString());
        TUser response = dao.get(dao.insert(user).userId);
        // check
        assert user.avatar.equals(response.avatar);
        assert user.socialId.equals(response.socialId);
        assert user.intro.equals(response.intro);
        assert user.name.equals(response.name);
        assert user.phone.equals(response.phone);
        assert user.birthday == response.birthday;
        assert user.currentBikeId == response.currentBikeId;
        assert user.sex == response.sex;
        assert Const.PinBike.SocialType.EMAIL == response.socialType;

        System.out.println("[OUTPUT]" + response.toString());

    }

    @Test
    public void testUpdate() throws Exception {
        TUser user = dao.get(userId);
        user.avatar = ("avatar");
        user.socialId = "socialId";
        user.intro = "intro";
        user.name = "Updater";
        user.phone = "123456789";
        user.birthday = DateTimeUtils.now();
        dao.update(user);
        TUser response = dao.get(userId);
        assert response.avatar.equals("avatar");
        assert response.socialId.equals("socialId");
        assert response.intro.equals("intro");
        assert response.name.equals("Updater");
        assert response.phone.equals("123456789");
        assert response.birthday == user.birthday;
        System.out.println("[INPUT]" + user.toString());
        System.out.println("[OUTPUT]" + response.toString());
    }

    @Test
    public void testDelete() throws Exception {
        dao.delete(userId);
        assert dao.get(userId) == null;
    }

    @Test
    public void testGet() throws Exception {
        assert dao.get(1234) != null;
    }

    @Test
    public void testGetList() throws Exception {
        assert dao.getList(new ArrayList<>(Arrays.asList(userId))) != null;
        assert !dao.getList(new ArrayList<>(Arrays.asList(userId))).isEmpty();
    }

    @Test
    public void testGetAll() throws Exception {
        assert dao.getAll() != null;
        assert !dao.getAll().isEmpty();
    }

    @Before
    public void setUp() throws Exception {
        UserDetail userDetail = factory.getUserDetail();
        TUser user = new TUser();
        user.avatar = userDetail.avatar;
        user.socialId = userDetail.email;
        user.socialType = Const.PinBike.SocialType.EMAIL;
        user.intro = userDetail.intro;
        user.name = userDetail.givenName;
        user.lastName = userDetail.familyName;
        user.middleName = userDetail.middleName;
        user.phone = userDetail.phone;
        user.birthday = userDetail.birthday;
        user.currentBikeId = userDetail.currentBikeId;
        user.availableDriver = userDetail.isAvailable;
        user.dateCreated = userDetail.joinedDate;
        user.sex = userDetail.sex;
        user.status = userDetail.status;
        // insert
        userId = dao.insert(user).userId;
    }

    @After
    public void tearDown() throws Exception {
           dao.delete(userId);
    }
}