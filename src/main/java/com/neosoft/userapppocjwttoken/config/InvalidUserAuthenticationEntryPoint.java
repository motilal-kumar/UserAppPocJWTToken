package com.neosoft.userapppocjwttoken.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * InvalidUserAuthenticationEntryPoint.
 *
 * @author Motilal Kumar.
 * version 1.0
 *
 */
@Component
public class InvalidUserAuthenticationEntryPoint  implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(InvalidUserAuthenticationEntryPoint.class);

    /**
     * commence.
     *
     * @return the
     * @param request that resulted in an <code>AuthenticationException</code>
     * @param response so that the user agent can begin authentication
     * @param authException that caused the invocation
     * @throws IOException
     * @throws ServletException
     *
     */
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
                  throws IOException, ServletException

    {
        logger.error("Unauthorized error: {}", authException.getMessage());
        final String expired = (String) request.getAttribute("expired");
      /*  if (expired!=null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,expired);
        }else {*/
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized User!");
       // }
    }
}
