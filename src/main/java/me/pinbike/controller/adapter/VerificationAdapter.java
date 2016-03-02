package me.pinbike.controller.adapter;

import com.pinride.pinbike.config.Const;
import com.pinride.pinbike.thrift.TUser;
import com.pinride.pinbike.thrift.TVerification;
import me.pinbike.controller.adapter.adapter_interface.IVerificationAdapter;
import me.pinbike.dao.UserDao;
import me.pinbike.dao.VerificationDao;
import me.pinbike.sharedjava.model.*;
import me.pinbike.util.SendMailUtil;
import me.pinbike.util.common.Path;

import java.io.IOException;

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
            TVerification verification = verificationDao.getVerificationByUser(user.userId);
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
    public RequestVerifyAPI.Response requestVerifyAPI(RequestVerifyAPI.Request request) throws IOException {
        UserDao userDao = new UserDao();
        VerificationDao verificationDao = new VerificationDao();
        TUser user = userDao.get(request.userId);
        TVerification verification = verificationDao.getVerificationByUser(user.userId);
        verification.viberAccount = request.viber;
        verification.facebookAccount = request.facebook;
        verification.skypeAccount = request.skype;
        verification.hangoutAccount = request.hangout;
        verificationDao.update(verification);
        userDao.updateVerifyStatus(user.userId, Const.PinBike.VerifiedStatus.PENDING_VERIFIED);
        String content = String.format("<html><p>%s have sent verified request</p>" +
                        "<p>Contact Info:<br>" +
                        " Skype: %s<br>" +
                        " Viber: %s<br>" +
                        " Facebook: <a href = \"www.facebook.com/%s\"></a>facebook link<br> " +
                        " Hangout: %s<br>" +
                        " </p>" +
                        "<p>To change verified status of user to VERIFIED click <a href=\"%s\">here</a></p></html>",
                user.getLastName() + " " + user.getMiddleName() + " " + user.getName(),
                verification.skypeAccount,
                verification.viberAccount,
                verification.facebookAccount,
                verification.hangoutAccount,
                Path.getInstance().getServerAddress() + "/ConfirmVerifiedUserViaEmail?userId=" + user.userId);
        new SendMailUtil("hpduy17@gmail.com", user.name + " have sent request to you", content);
        return null;
    }

    @Override
    public ConfirmVerifiedUserAPI.Response confirmVerifiedUserAPI(ConfirmVerifiedUserAPI.Request request) {
        UserDao userDao = new UserDao();
        TUser user = userDao.get(request.userId);
        userDao.updateVerifyStatus(user.userId, Const.PinBike.VerifiedStatus.VERIFIED);
        return null;
    }

    @Override
    public GetVerifiedContactOfflineAPI.Response getVerifiedContactOfflineAPI(GetVerifiedContactOfflineAPI.Request request) {

        return null;
    }

    @Override
    public GetUserVerifiedStatusAPI.Response getUserVerifiedStatusAPI(GetUserVerifiedStatusAPI.Request request) {
        UserDao userDao = new UserDao();
        VerificationDao verificationDao = new VerificationDao();
        TUser user = userDao.get(request.userId);
        GetUserVerifiedStatusAPI.Response response = new GetUserVerifiedStatusAPI.Response();
        response.status = user.verifiedStatus;
        try {
            TVerification verification = verificationDao.getVerificationByUser(user.userId);
            if (verification != null) {
                response.faceImageUrl = verification.faceImage;
                response.frontSideDriverLicenseImageUrl = verification.driverLicenseFrontside;
                response.backSideDriverLicenseImageUrl = verification.driverLicenseBackside;
                response.frontSideIdCardImageUrl = verification.idCardFrontside;
                response.backSideIdCardImageUrl = verification.idCardBackside;
            }
        } catch (Exception ignored) {
        }

        return response;
    }


}
