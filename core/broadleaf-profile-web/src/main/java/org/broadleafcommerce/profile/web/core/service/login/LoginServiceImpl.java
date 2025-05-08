/*-
 * #%L
 * BroadleafCommerce Profile Web
 * %%
 * Copyright (C) 2009 - 2025 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.profile.web.core.service.login;

import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.common.web.BroadleafWebRequestProcessor;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import jakarta.annotation.Resource;

@Service("blLoginService")
public class LoginServiceImpl implements LoginService {

    @Resource(name = "blCartStateRequestProcessor")
    protected BroadleafWebRequestProcessor cartStateRequestProcessor;
    @Resource(name = "blAuthenticationManager")
    private AuthenticationManager authenticationManager;
    @Resource(name = "blUserDetailsService")
    private UserDetailsService userDetailsService;
    @Resource(name = "blCustomerStateRequestProcessor")
    private BroadleafWebRequestProcessor customerStateRequestProcessor;

    @Override
    public Authentication loginCustomer(Customer customer) {
        return loginCustomer(customer.getUsername(), customer.getUnencodedPassword());
    }

    @Override
            // &begin[loginCustomer]
    public Authentication loginCustomer(String username, String clearTextPassword) {
        UserDetails principal = userDetailsService.loadUserByUsername(username); // &line[SessionManagement_loadUserByUsername_L]
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( // &line[Authentication_UsernamePasswordAuthenticationToken_L]
                principal, clearTextPassword, principal.getAuthorities() // &line[SessionManagement_getAuthorities_L]
        );
        Authentication authentication = authenticationManager.authenticate(token); // &line[Authentication_authenticate_L]
        SecurityContextHolder.getContext().setAuthentication(authentication); // &line[SessionManagement_getContext_L, SessionManagement_setAuthentication_L]
        customerStateRequestProcessor.process(getWebRequest());
        cartStateRequestProcessor.process(getWebRequest());
        return authentication;
    }
    // &end[loginCustomer]

    @Override
            // &begin[logoutCustomer]
    public void logoutCustomer() {
        SecurityContextHolder.getContext().setAuthentication(null); // &line[SessionManagement_getContext_L, SessionManagement_setAuthentication_L]
        customerStateRequestProcessor.process(getWebRequest());
        cartStateRequestProcessor.process(getWebRequest());
    }
    //&end[logoutCustomer]

    protected WebRequest getWebRequest() {
        return BroadleafRequestContext.getBroadleafRequestContext().getWebRequest();
    }

}
