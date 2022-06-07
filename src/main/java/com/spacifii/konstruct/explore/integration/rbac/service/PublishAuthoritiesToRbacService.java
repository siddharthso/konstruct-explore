package com.spacifii.konstruct.explore.integration.rbac.service;



import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.integration.rbac.entity.RbacAuthorityDto;
import com.spacifii.konstruct.explore.integration.rbac.entity.RbacRegisterDto;
import com.spacifii.konstruct.explore.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * This service class is used for integration with RBAC for authorityNames publishing
 */
@Service
public class PublishAuthoritiesToRbacService {


    private final Logger logger = LoggerFactory.getLogger(PublishAuthoritiesToRbacService.class);

    @Value("${rbac.application.name}")
    String applicationName;

    @Value("${rbac.register.url}")
    String rbacRegisterUrl;

    @Autowired
    RestTemplate restTemplate;


    //@PostConstruct
    public void publishToRobac() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(RestController.class));

        Set<RbacAuthorityDto> rbacAuthorityDtos = new HashSet<>();
        for (BeanDefinition beanDefinition : provider.findCandidateComponents("com.spacifii")) {
            try {

                String claszzName = beanDefinition.getBeanClassName();
                Class<?> claszz = Class.forName(claszzName);
                rbacAuthorityDtos = findRbacAuthorities(claszz,rbacAuthorityDtos);
            } catch (ClassNotFoundException exception) {
                logger.error(exception.getLocalizedMessage(), exception);
            }

        }

        if(rbacAuthorityDtos.size() > 0 ){
            System.out.println(rbacAuthorityDtos);

            RbacRegisterDto rbacRegisterDto = new RbacRegisterDto();
            rbacRegisterDto.setApplicationName(applicationName);
            rbacRegisterDto.setRbacAuthorities(rbacAuthorityDtos);
            System.out.println(rbacRegisterDto);

            //REST CALL TO RBAC Service

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<RbacRegisterDto> request = new HttpEntity<>(rbacRegisterDto, headers);

            ApiResponse<Void> response = restTemplate.postForObject(rbacRegisterUrl, request, ApiResponse.class);


        }
    }

    public Set<RbacAuthorityDto>  findRbacAuthorities(Class<?> claszz, Set<RbacAuthorityDto> rbacAuthorityDtos){
        for (Method method: claszz.getMethods()) {
            if(method.isAnnotationPresent(RbacRegister.class)){
                RbacRegister rbacRegister = method.getAnnotation(RbacRegister.class);
                if (rbacRegister != null){
                    RbacAuthorityDto rbacAuthorityDto = new RbacAuthorityDto();
                    rbacAuthorityDto.setApiType(rbacRegister.apiType());
                    rbacAuthorityDto.setAuthorityName(rbacRegister.authorityName());
                    rbacAuthorityDto.setExclusion(rbacRegister.excluded());
                    rbacAuthorityDto.setServiceClassName(claszz.getSimpleName());
                    rbacAuthorityDtos.add(rbacAuthorityDto);

                }
            }
        }
        return rbacAuthorityDtos;
    }

}
