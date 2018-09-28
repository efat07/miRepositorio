package com.customerService.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/*import com.customerService.model.Customer;
import com.customerService.payload.CustomerRequest;*/
import com.customerService.util.Constant;
/*import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;*/

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(Constant.KEY_HEADER_AUTHORIZATION);
		if (header == null) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		
		if(authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
		}else {
			chain.doFilter(request, response);
			return;
		}		
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(Constant.KEY_HEADER_AUTHORIZATION);
		if (token != null) {
			if(validateToken(token)) {
				// Se procesa el token y se recupera el usuario.
				String user = Jwts.parser()
							.setSigningKey(Constant.JWT_SECRET)
							.parseClaimsJws(token)
							.getBody()
							.getSubject();

				/*CustomerRequest customerRequest;
				try {
					customerRequest = new ObjectMapper().readValue(request.getInputStream(), CustomerRequest.class);
				} catch (JsonParseException e) {
					return null;
				} catch (JsonMappingException e) {
					return null;
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}*/
				
				if (user != null/*&& user.equals(customerRequest.getKeyValue())*/) {
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
				return null;
			}else {
				return null;
			}
		}
		return null;
	}
	
	public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}
