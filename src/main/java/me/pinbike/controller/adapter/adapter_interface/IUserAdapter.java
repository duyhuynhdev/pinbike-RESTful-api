package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.*;

import java.io.IOException;

/**
 * Created by hpduy17 on 12/4/15.
 */
public interface IUserAdapter {

    GetUserProfileAPI.Response getUserProfile(GetUserProfileAPI.Request request);

    GetUserRatingsAPI.Response getUserRatings(GetUserRatingsAPI.Request request);

    GetDriverAroundAPI.Response getDriverAround(GetDriverAroundAPI.Request request);

    UpdateMyLocationAPI.Response updateMyLocation(UpdateMyLocationAPI.Request request);

    ChangeAvailableStatusAPI.Response changeAvailableStatus(ChangeAvailableStatusAPI.Request request);

    RegisterAPI.Response register(RegisterAPI.Request request);

    LoginByEmailAPI.Response loginByEmail(LoginByEmailAPI.Request request);

    LoginBySocialAPI.Response loginBySocial(LoginBySocialAPI.Request request);

    GetActivationCodeAPI.Response getActivationCode(GetActivationCodeAPI.Request request);

    ActivatePhoneNumberAPI.Response activatePhoneNumber(ActivatePhoneNumberAPI.Request request);

    LogoutAPI.Response logout(LogoutAPI.Request request);

    ForgotPasswordAPI.Response forgotPassword(ForgotPasswordAPI.Request request, String os, String deviceId, String ipAddress) throws IOException;

    ChangePasswordAPI.Response changePassword(ChangePasswordAPI.Request request, String os, String deviceId, String ipAddress) throws IOException;

    UpdateUserPhoneNumberAPI.Response updateUserPhoneNumber(UpdateUserPhoneNumberAPI.Request request);

    UpdateUserAvatarAPI.Response updateUserAvatar(UpdateUserAvatarAPI.Request request);

    GetLocationUpdatedAPI.Response getLocationUpdated(GetLocationUpdatedAPI.Request request);

    UpdateUserEnglishCommunicateAPI.Response updateUserEnglishCommunicate(UpdateUserEnglishCommunicateAPI.Request request);

    UpdateUserSocialAPI.Response updateUserSocial(UpdateUserSocialAPI.Request request);

    UpdateUserIntroAPI.Response updateUserIntro(UpdateUserIntroAPI.Request request);

}
