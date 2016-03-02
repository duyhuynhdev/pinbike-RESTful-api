package me.pinbike.controller.api;

import me.pinbike.controller.adapter.payment.PaymentAdapter;
import me.pinbike.sharedjava.model.base.RequestWrapper;
import me.pinbike.sharedjava.model.payment.*;
import me.pinbike.util.LogUtil;
import me.pinbike.util.PinBikeConstant;
import me.pinbike.util.ResponseWrapper;
import org.apache.log4j.Logger;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

/**
 * Created by hpduy17 on 2/17/16.
 */
@Path("/")
@ValidateRequest
public class PaymentService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/GetDriverAccountDetailAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetDriverAccountDetailAPI.Response> GetDriverAccountDetailAPI (@Valid RequestWrapper<GetDriverAccountDetailAPI.Request> request) throws IOException {

        PaymentAdapter adapter = new PaymentAdapter();

        GetDriverAccountDetailAPI.Response responseContent;
        GetDriverAccountDetailAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getDriverAccountDetail(requestContent);
        ResponseWrapper<GetDriverAccountDetailAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }
    @POST
    @Path("/GetDriverCreditDetailAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetDriverCreditDetailAPI.Response> GetDriverCreditDetailAPI (@Valid RequestWrapper<GetDriverCreditDetailAPI.Request> request) throws IOException {

        PaymentAdapter adapter = new PaymentAdapter();

        GetDriverCreditDetailAPI.Response responseContent;
        GetDriverCreditDetailAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getDriverCreditDetail(requestContent);
        ResponseWrapper<GetDriverCreditDetailAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }
    @POST
    @Path("/GetIncomeDetailInMonthAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetIncomeDetailInMonthAPI.Response> GetIncomeDetailInMonthAPI (@Valid RequestWrapper<GetIncomeDetailInMonthAPI.Request> request) throws IOException {

        PaymentAdapter adapter = new PaymentAdapter();

        GetIncomeDetailInMonthAPI.Response responseContent;
        GetIncomeDetailInMonthAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getIncomeDetailInMonth(requestContent);
        ResponseWrapper<GetIncomeDetailInMonthAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }
    @POST
    @Path("/GetIncomeSummaryInYearAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetIncomeSummaryInYearAPI.Response> GetIncomeSummaryInYearAPI (@Valid RequestWrapper<GetIncomeSummaryInYearAPI.Request> request) throws IOException {

        PaymentAdapter adapter = new PaymentAdapter();

        GetIncomeSummaryInYearAPI.Response responseContent;
        GetIncomeSummaryInYearAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getIncomeSummaryInYear(requestContent);
        ResponseWrapper<GetIncomeSummaryInYearAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }
    @POST
    @Path("/GetPromoCreditDetailAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetPromoCreditDetailAPI.Response> GetPromoCreditDetailAPI (@Valid RequestWrapper<GetPromoCreditDetailAPI.Request> request) throws IOException {

        PaymentAdapter adapter = new PaymentAdapter();

        GetPromoCreditDetailAPI.Response responseContent;
        GetPromoCreditDetailAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getPromoCreditDetail(requestContent);
        ResponseWrapper<GetPromoCreditDetailAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }
    @POST
    @Path("/TransferPromoCreditToDriverCreditAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<TransferPromoCreditToDriverCreditAPI.Response> TransferPromoCreditToDriverCreditAPI (@Valid RequestWrapper<TransferPromoCreditToDriverCreditAPI.Request> request) throws IOException {

        PaymentAdapter adapter = new PaymentAdapter();

        TransferPromoCreditToDriverCreditAPI.Response responseContent;
        TransferPromoCreditToDriverCreditAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.transferPromoCreditToDriverCredit(requestContent);
        ResponseWrapper<TransferPromoCreditToDriverCreditAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }
}
