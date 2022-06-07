package com.spacifii.konstruct.explore.service.explore;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.security.MessageDigest;

@Service
public class UtilityService {


    public String getImageHash(MultipartFile multipartFile){
        try {
        byte[] uploadBytes = multipartFile.getBytes();
        MessageDigest md5 = null;

            md5 = MessageDigest.getInstance("MD5");

        byte[] digest = md5.digest(uploadBytes);
        String hashString = new BigInteger(1, digest).toString(16);

        return hashString;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
