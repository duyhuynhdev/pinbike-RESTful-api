package me.pinbike.controller.adapter.adapter_interface;

import com.pinride.pinbike.config.Const;
import com.pinride.pinbike.thrift.TUser;
import com.pinride.pinbike.thrift.TVerification;
import me.pinbike.dao.UserDao;
import me.pinbike.dao.VerificationDao;
import me.pinbike.sharedjava.model.AddVerificationAPI;
import me.pinbike.sharedjava.model.ConfirmVerifiedUserAPI;
import me.pinbike.sharedjava.model.GetVerifiedContactOfflineAPI;
import me.pinbike.sharedjava.model.RequestVerifyAPI;

/**
 * Created by hpduy17 on 2/22/16.
 */
public class VerificationAdapter implements IVerificationAdapter {
    @Override
    public AddVerificationAPI.Response addVerification(AddVerificationAPI.Request request) {
        UserDao userDao = new UserDao();
        VerificationDao verificationDao = new VerificationDao();
        TUser user = userDao.get(request.userId);
        try {
            TVerification verification = verificationDao.get(user.userId);
            verification.faceImage = request.faceImageUrl;
            verification.idCardFrontside = request.frontSideIdCardImageUrl;
            verification.idCardBackside = request.backSideIdCardImageUrl;
            verification.driverLicenseFrontside = request.frontSideDriverLicenseImageUrl;
            verification.driverLicenseBackside = request.backSideDriverLicenseImageUrl;
            verification.userId = user.userId;
            verificationDao.update(verification);
        } catch (Exception ex) {
            TVerification verification = new TVerification();
            verification.faceImage = request.faceImageUrl;
            verification.idCardFrontside = request.frontSideIdCardImageUrl;
            verification.driverLicenseFrontside = request.frontSideDriverLicenseImageUrl;
            verification.idCardBackside = request.backSideIdCardImageUrl;
            verification.driverLicenseBackside = request.backSideDriverLicenseImageUrl;
            verification.userId = user.userId;
            verificationDao.insert(verification);
        }
        return null;
    }

    @Override
    public RequestVerifyAPI.Response requestVerifyAPI(RequestVerifyAPI.Request request) {
        UserDao userDao = new UserDao();
        VerificationDao verificationDao = new VerificationDao();
        TUser user = userDao.get(request.userId);
        TVerification verification = verificationDao.get(user.userId);
        verification.viberAccount = request.viber;
        verification.facebookAccount = request.facebook;
        verification.skypeAccount = request.skype;
        verification.hangoutAccount = request.hangout;
        verificationDao.update(verification);
        verificationDao.updateVerifyStatus(user.userId, Const.PinBike.VerifiedStatus.PENDING_VERIFIED);
        return null;
    }

    @Override
    public ConfirmVerifiedUserAPI.Response confirmVerifiedUserAPI(ConfirmVerifiedUserAPI.Request request) {
        UserDao userDao = new UserDao();
        VerificationDao verificationDao = new VerificationDao();
        TUser user = userDao.get(request.userId);
        verificationDao.updateVerifyStatus(user.userId, Const.PinBike.VerifiedStatus.VERIFIED);
        return null;
    }

    @Override
    public GetVerifiedContactOfflineAPI.Response GetVerifiedContactOfflineAPI(GetVerifiedContactOfflineAPI.Request request) {

        return null;
    }


}
