/*-
 * #%L
 * BroadleafCommerce Open Admin Platform
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
package org.broadleafcommerce.openadmin.web.form;

import java.io.Serial;
import java.io.Serializable;

/**
 * Created by bpolster.
 */
public class ResetPasswordForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String token;
    private String oldPassword;
    private String password;
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // &begin[getToken]
    public String getToken() {
        return token;
    }
    // &end[getToken]

// &begin[setToken]
    public void setToken(String token) {
        this.token = token;
    }
    // &end[setToken]

    // &begin[getPassword]
    public String getPassword() {
        return password;
    }
    // &end[getPassword]

    // &begin[setPassword]
    public void setPassword(String password) {
        this.password = password;
    }
    // &end[setPassword]

    // &begin[getConfirmPassword]
    public String getConfirmPassword() {
        return confirmPassword;
    }
    // &end[getConfirmPassword]

    // &begin[setConfirmPassword]
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    // &end[setConfirmPassword]

    // &begin[getOldPassword]
    public String getOldPassword() {
        return oldPassword;
    }
    // &end[getOldPassword]

    // &begin[setOldPassword]
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    // &end[setOldPassword]

}
