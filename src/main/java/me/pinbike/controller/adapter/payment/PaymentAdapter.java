package me.pinbike.controller.adapter.payment;

import com.pinride.pinbike.payment.config.Const;
import com.pinride.pinbike.payment.thrift.TTransaction;
import com.pinride.pinbike.payment.thrift.TUserProfile;
import com.pinride.pinbike.thrift.TBike;
import com.pinride.pinbike.thrift.TTrip;
import com.pinride.pinbike.thrift.TUser;
import me.pinbike.controller.adapter.adapter_interface.Converter;
import me.pinbike.dao.BikeDao;
import me.pinbike.dao.PromotionDao;
import me.pinbike.dao.UserDao;
import me.pinbike.dao.payment.PaymentDao;
import me.pinbike.sharedjava.model.EndTripAPI;
import me.pinbike.sharedjava.model.GetDefaultSettingAPI;
import me.pinbike.sharedjava.model.base.IncomeSum;
import me.pinbike.sharedjava.model.payment.*;
import me.pinbike.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by hpduy17 on 3/1/16.
 */
public class PaymentAdapter {

    public EndTripAPI.Response endTripTransaction(TTrip trip) {
        // insert transaction
        PaymentDao paymentDao = new PaymentDao();
        PromotionDao promotionDao = new PromotionDao();
        int type = Const.PinBike.TransactionType.END_TRIP;
        try{
            promotionDao.get(trip.promoCodeId);
            type = Const.PinBike.TransactionType.END_TRIP_WITH_PROMO;
        }catch (Exception ignored){

        }
        TTransaction transaction = new TTransaction();
        transaction.description = "đến "+trip.endLocation;
        transaction.pinbikeTax = new GetDefaultSettingAPI.Response().priceModel.getCommissionCut(trip.distance);
        transaction.promoValue = trip.promoCodeValue > transaction.tripFare ? transaction.tripFare : transaction.promoValue;
        transaction.transactionType = type;
        transaction.tripFare = trip.price;
        transaction.tripId = trip.tripId;
        transaction.userId = trip.driverId;
        transaction.userIdModified = trip.driverId;
        transaction = paymentDao.insert(transaction);
        // update user credit
        paymentDao.updateCreditForUser(trip.driverId, transaction.transactionId, trip.driverId);
        TUserProfile userProfile = paymentDao.getUserProfile(trip.driverId);
        EndTripAPI.Response response = new EndTripAPI.Response();
        response.driverCredit = userProfile.userCredit;
        response.promoCredit = userProfile.promoCredit;
        response.driverIncome = paymentDao.getInComeInMonth(trip.driverId, new Date().getMonth(), new Date().getYear());
        return response;
    }

    public GetDriverAccountDetailAPI.Response getDriverAccountDetail(GetDriverAccountDetailAPI.Request request) {
        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        PaymentDao paymentDao = new PaymentDao();
        TUser user = userDao.get(request.userId);
        TBike bike = bikeDao.get(user.currentBikeId);
        TUserProfile userProfile = paymentDao.getUserProfile(request.userId);
        GetDriverAccountDetailAPI.Response response = new GetDriverAccountDetailAPI.Response();
        response.driverCredit = userProfile.userCredit;
        response.promoCredit = userProfile.promoCredit;
        response.driverIncome = paymentDao.getInComeInMonth(request.userId, new Date().getMonth(), new Date().getYear());
        response.currentBike = new Converter().convertBike(bike);
        return response;
    }

    public GetIncomeSummaryInYearAPI.Response getIncomeSummaryInYear(GetIncomeSummaryInYearAPI.Request request) {
        UserDao userDao = new UserDao();
        PaymentDao paymentDao = new PaymentDao();
        TUser user = userDao.get(request.userId);
        GetIncomeSummaryInYearAPI.Response response = new GetIncomeSummaryInYearAPI.Response();
        response.incomeSummary = new ArrayList<>();
        LinkedHashMap<Integer, List<Integer>> yearAndMonths = DateTimeUtils.getYearAndMonthBetween2Day(user.dateCreated, new Date().getTime());
        for (int year : yearAndMonths.keySet()) {
            List<Integer> months = yearAndMonths.get(year);
            if (months != null) {
                for (int month : months) {
                    try {
                        IncomeSum incomeSum = new IncomeSum();
                        incomeSum.month = month;
                        incomeSum.year = year;
                        incomeSum.incomeValue = paymentDao.getInComeInMonth(user.userId, month, year);
                        response.incomeSummary.add(incomeSum);
                    } catch (Exception ignored) {

                    }
                }
            }
        }
        return response;
    }

    public GetIncomeDetailInMonthAPI.Response getIncomeDetailInMonth(GetIncomeDetailInMonthAPI.Request request) {
        UserDao userDao = new UserDao();
        PaymentDao paymentDao = new PaymentDao();
        TUser user = userDao.get(request.userId);
        GetIncomeDetailInMonthAPI.Response response = new GetIncomeDetailInMonthAPI.Response();
        List<TTransaction> transactions = paymentDao.getInComesDetailInMonth(user.userId, request.month, request.year);
        if (transactions != null) {
            for (TTransaction t : transactions) {
                try {
                    response.transactions.add(new Converter().convertTransactionSum(t, Converter.INCOME_CREDIT_TYPE));
                } catch (Exception ignored) {

                }
            }
        }
        return response;
    }


    public GetDriverCreditDetailAPI.Response getDriverCreditDetail(GetDriverCreditDetailAPI.Request request) {
        UserDao userDao = new UserDao();
        PaymentDao paymentDao = new PaymentDao();
        TUser user = userDao.get(request.userId);
        GetDriverCreditDetailAPI.Response response = new GetDriverCreditDetailAPI.Response();
        List<TTransaction> transactions = paymentDao.getTransactionUserCredit(user.userId, request.limit);
        if (transactions != null) {
            for (TTransaction t : transactions) {
                try {
                    response.transactions.add(new Converter().convertTransactionSum(t, Converter.DRIVER_CREDIT_TYPE));
                } catch (Exception ignored) {

                }
            }
        }
        return response;
    }

    public GetPromoCreditDetailAPI.Response getPromoCreditDetail(GetPromoCreditDetailAPI.Request request) {
        UserDao userDao = new UserDao();
        PaymentDao paymentDao = new PaymentDao();
        TUser user = userDao.get(request.userId);
        GetPromoCreditDetailAPI.Response response = new GetPromoCreditDetailAPI.Response();
        List<TTransaction> transactions = paymentDao.getTransactionPromoCredit(user.userId, request.limit);
        if (transactions != null) {
            for (TTransaction t : transactions) {
                try {
                    response.transactions.add(new Converter().convertTransactionSum(t, Converter.PROMO_CREDIT_TYPE));
                } catch (Exception ignored) {

                }
            }
        }
        return response;
    }

    public TransferPromoCreditToDriverCreditAPI.Response transferPromoCreditToDriverCredit(TransferPromoCreditToDriverCreditAPI.Request request) {
        // insert transaction
        UserDao userDao = new UserDao();
        PaymentDao paymentDao = new PaymentDao();
        TUser user = userDao.get(request.userId);
        TUserProfile userProfile = paymentDao.getUserProfile(user.userId);
        TTransaction transaction = new TTransaction();
        transaction.description = "chuyển sang tài khoản tài xế";
        transaction.pinbikeTax = userProfile.promoCredit;
        transaction.promoValue = userProfile.promoCredit;
        transaction.transactionType = Const.PinBike.TransactionType.SWITCH_CREDIT;
        transaction.tripFare = 0;
        transaction.tripId = 0;
        transaction.userId = user.userId;
        transaction.userIdModified = user.userId;
        transaction = paymentDao.insert(transaction);
        // update user credit
        paymentDao.updateCreditForUser(user.userId, transaction.transactionId, user.userId);
        userProfile = paymentDao.getUserProfile(user.userId);
        TransferPromoCreditToDriverCreditAPI.Response response = new TransferPromoCreditToDriverCreditAPI.Response();
        response.driverCredit = userProfile.userCredit;
        response.promoCredit = userProfile.promoCredit;
        response.driverIncome = paymentDao.getInComeInMonth(user.userId, new Date().getMonth(), new Date().getYear());
        return response;
    }

}
