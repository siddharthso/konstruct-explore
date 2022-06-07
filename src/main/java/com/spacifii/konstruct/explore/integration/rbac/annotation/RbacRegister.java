

package com.spacifii.konstruct.explore.integration.rbac.annotation;




import com.spacifii.konstruct.explore.integration.rbac.entity.ApiType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RbacRegister {

    ApiType apiType() default ApiType.INTERNET;

    String authorityName();

    boolean excluded() default false;


}
