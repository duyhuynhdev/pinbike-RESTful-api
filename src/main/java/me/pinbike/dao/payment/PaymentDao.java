package me.pinbike.dao.payment;

import com.pinride.pinbike.payment.config.adapter.AdapterResponseValue;
import com.pinride.pinbike.payment.thrift.TTransaction;
import com.pinride.pinbike.payment.thrift.TUserProfile;
import com.pinride.pinbike.payment.thriftclient.TPaymentClientImpl;
import me.pinbike.dao.DaoTemplate;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.LogUtil;
import me.pinbike.util.PinBikeConstant;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 2/29/16.
 */
public class PaymentDao {
    private TPaymentClientImpl client;
    private Logger logger;

    public PaymentDao() {
        client = TPaymentClientImpl.getInstance(PinBikeConstant.BackEndConfig.Host_Payment, PinBikeConstant.BackEndConfig.Port_Payment);
        logger = LogUtil.getLogger(this.getClass());
    }


    public TTransaction insert(TTransaction object) {
        try {
            logger.info(object.toString());
            AdapterResponseValue.ResponseValue<TTransaction> response = client.insert(object);
            new DaoTemplate<>().validateResponse(response.getErrorCode(), this.getClass() + ".insert()", object.toString());
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public void update(TTransaction object) {
        try {
            logger.info(object.toString());
            int errorCode = client.update(object);
            new DaoTemplate<>().validateResponse(errorCode, this.getClass() + ".update()", object.toString());
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }

    }

    public void updateCreditForUser(long userId, long transactionId, long userUpdate) {
        try {
            logger.info(String.format("{userId:%d , transactionId:%d, userUpdate:%d}", userId, transactionId, userUpdate));
            int errorCode = client.updateCreditForUser(userId, transactionId, userUpdate);
            new DaoTemplate<>().validateResponse(errorCode, this.getClass() + ".update()", String.format("{userId:%d , transactionId:%d, userUpdate:%d}", userId, transactionId, userUpdate));
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }

    }

    public void updateStatusTransaction(long transactionId, int transactionStatus, long userUpdate) {
        try {
            logger.info(String.format("{transactionId:%d , transactionStatus:%d, userUpdate:%d}", transactionId, transactionStatus, userUpdate));
            int errorCode = client.updateStatusTransaction(transactionId, transactionStatus, userUpdate);
            new DaoTemplate<>().validateResponse(errorCode, this.getClass() + ".update()", String.format("{transactionId:%d , transactionStatus:%d, userUpdate:%d}", transactionId, transactionStatus, userUpdate));
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }

    }

    public void delete(long id) {
        try {
            logger.info(id);
            int errorCode = client.remove(id);
            new DaoTemplate<>().validateResponse(errorCode, this.getClass() + ".delete()", "" + id);
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public TTransaction get(long id) {
        try {
            logger.info(id);
            AdapterResponseValue.ResponseValue<TTransaction> response = client.get(id);
            new DaoTemplate<>().validateResponse(response.getErrorCode(), this.getClass() + ".get()", "" + id);
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public TUserProfile getUserProfile(long userId) {
        try {
            logger.info(userId);
            AdapterResponseValue.ResponseValue<TUserProfile> response = client.getUserProfile(userId);
            new DaoTemplate<>().validateResponse(response.getErrorCode(), this.getClass() + ".getUserProfile()", "" + userId);
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public List<TTransaction> getList(List<Long> ids) {
        try {
            if (ids == null || ids.isEmpty())
                return new ArrayList<>();
            logger.info(ids.toString());
            AdapterResponseValue.ResponseListValue<TTransaction> response = client.gets(ids);
            new DaoTemplate<>().validateResponse(response.getErrorCode(), this.getClass() + ".getList()", ids.toString());
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public List<TTransaction> getTransactionByUser(long userId) {
        try {
            logger.info(userId);
            AdapterResponseValue.ResponseListValue<TTransaction> response = client.getTransactionByUser(userId);
            new DaoTemplate<>().validateResponse(response.getErrorCode(), this.getClass() + ".getTransactionByUser()", userId + "");
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public List<TTransaction> getTransactionByUserAndType(long userId, int transactionType) {
        try {
            logger.info(String.format("{userId:%d , transactionType:%d}", userId, transactionType));
            AdapterResponseValue.ResponseListValue<TTransaction> response = client.getTransactionByUserAndType(userId, transactionType);
            new DaoTemplate<>().validateResponse(response.getErrorCode(), this.getClass() + ".getTransactionByUserAndType()", String.format("{userId:%d , transactionType:%d}", userId, transactionType));
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public List<TTransaction> getInComesDetailInMonth(long userId, int month, int year) {
        try {
            logger.info(String.format("{userId:%d , month:%d, year:%d}", userId, month, year));
            AdapterResponseValue.ResponseListValue<TTransaction> response = client.getInComesDetailInMonth(userId, month, year);
            new DaoTemplate<>().validateResponse(response.getErrorCode(), this.getClass() + ".getInComesDetailInMonth()", String.format("{userId:%d , month:%d, year:%d}", userId, month, year));
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public List<TTransaction> getTransactionPromoCredit(long userId, int limit) {
        try {
            logger.info(String.format("{userId:%d , limit:%d}", userId, limit));
            AdapterResponseValue.ResponseListValue<TTransaction> response = client.getTransactionPromoCredit(userId, limit);
            new DaoTemplate<>().validateResponse(response.getErrorCode(), this.getClass() + ".getTransactionPromoCredit()", String.format("{userId:%d , limit:%d}", userId, limit));
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public List<TTransaction> getTransactionUserCredit(long userId, int limit) {
        try {
            logger.info(String.format("{userId:%d , limit:%d}", userId, limit));
            AdapterResponseValue.ResponseListValue<TTransaction> response = client.getTransactionUserCredit(userId, limit);
            new DaoTemplate<>().validateResponse(response.getErrorCode(), this.getClass() + ".getTransactionUserCredit()", String.format("{userId:%d , limit:%d}", userId, limit));
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public double getInComeInMonth(long userId, int month, int year) {
        try {
            logger.info(String.format("{userId:%d , month:%d, year:%d}", userId, month, year));
            AdapterResponseValue.ResponseValue<Double> response = client.getInComeInMonth(userId, month, year);
            new DaoTemplate<>().validateResponse(response.getErrorCode(), this.getClass() + ".getInComeInMonth()", String.format("{userId:%d , month:%d}", userId, month));
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            return 0;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }


}
