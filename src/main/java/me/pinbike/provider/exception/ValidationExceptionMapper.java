package me.pinbike.provider.exception;


import me.pinbike.util.PinBikeResponse;
import me.pinbike.util.LogUtil;
import me.pinbike.util.PinBikeConstant;
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
        int messageCode = PinBikeConstant.MessageCode.Element_is_invalid;
        if (exception instanceof MethodConstraintViolationException) {
            MethodConstraintViolationException methodConstraintViolationException = MethodConstraintViolationException.class.cast(exception);
            Set<MethodConstraintViolation<?>> contraints = methodConstraintViolationException.getConstraintViolations();
            if (contraints != null && !contraints.isEmpty()) {
                exMess = "";
                for (MethodConstraintViolation c : contraints) {
                    exMess += c.getMessage() + "\n";
                }
            }
        }
        if (exception instanceof PinBikeException) {
            PinBikeException pinBikeException = PinBikeException.class.cast(exception);
            exMess = pinBikeException.getMessage();
            messageCode = pinBikeException.getMessageCode();
        }
        PinBikeResponse res = new PinBikeResponse(false, messageCode, exMess);
        logger.error(res.toString());
        return Response.status(200).
                entity(res).
                type(headers.getMediaType()).
                build();

    }

}
