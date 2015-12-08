package me.pinbike.provider.exception;


import me.pinbike.sharedjava.model.constanst.MessageCode;
import me.pinbike.util.LogUtil;
import me.pinbike.util.ResponseWrapper;
import org.apache.log4j.Logger;
import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;

import javax.validation.ValidationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;


/**
 * Created by hpduy17 on 10/13/15.
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    private Logger logger = LogUtil.getLogger(this.getClass());

    @Context
    private HttpHeaders headers;

    @Override
    public Response toResponse(ValidationException exception) {
        String exMess = exception.getMessage();
        int messageCode = MessageCode.ELEMENT_INVALID;
        if (exception instanceof MethodConstraintViolationException) {
            MethodConstraintViolationException methodConstraintViolationException = MethodConstraintViolationException.class.cast(exception);
            Set<MethodConstraintViolation<?>> constraints = methodConstraintViolationException.getConstraintViolations();
            if (constraints != null && !constraints.isEmpty()) {
                exMess = "";
                for (MethodConstraintViolation c : constraints) {
                    exMess += c.getMessage() + "\n";
                }
            }
        }
        if (exception instanceof PinBikeException) {
            PinBikeException pinBikeException = PinBikeException.class.cast(exception);
            exMess = pinBikeException.getMessage();
            messageCode = pinBikeException.getMessageCode();
        }
        ResponseWrapper res = new ResponseWrapper(false, messageCode, exMess);
        logger.error(res.toString());
        return Response.status(200).
                entity(res).
                type(headers.getMediaType()).
                build();

    }

}
