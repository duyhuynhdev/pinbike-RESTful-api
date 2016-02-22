package me.pinbike.controller.adapter;

import com.pinride.pinbike.thrift.TUser;
import me.pinbike.controller.adapter.adapter_interface.ICustomerServiceAdapter;
import me.pinbike.dao.UserDao;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.FeedbackAPI;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.DateTimeUtils;
import me.pinbike.util.PinBikeConstant;
import me.pinbike.util.SendMailUtil;
import me.pinbike.util.common.Path;

import java.io.IOException;
import java.util.Date;

/**
 * Created by hpduy17 on 2/17/16.
 */
public class CustomerServiceAdapter implements ICustomerServiceAdapter {
    @Override
    public FeedbackAPI.Response feedback(FeedbackAPI.Request request) throws IOException {
        try {
            UserDao userDao = new UserDao();
            TUser user = userDao.get(request.userId);
            String content = PinBikeConstant.MailLayout.getFeedbackLayout(request.feedbackContent,
                    Path.getInstance().getUrlFromPath(user.avatar),
                    user.lastName +" "+ user.middleName +" "+ user.name, request.email, user.phone, request.imageUrls, DateTimeUtils.getHCMFormatDate(new Date().getTime()));
            for(int i = 0 ; i < PinBikeConstant.CustomerServiceMail.feedback_mails.length;i++) {
                new SendMailUtil(PinBikeConstant.CustomerServiceMail.feedback_mails[i], user.name+PinBikeConstant.MailLayout.title_feedback, content);
            }
            return null;
        }catch (PinBikeException ex){
           throw ex;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new PinBikeException(AC.MessageCode.FAIL,"Send feed back fail. Please try again!");
        }
    }
}
