package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.*;
import com.spacifii.konstruct.explore.service.explore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("SaveMediaImage360Strategy")
public class SaveMediaImage360Strategy implements SaveMediaStrategy {

    @Autowired
    SaveMediaImageStrategy saveMediaImageStrategy;

    @Override
    public Media saveMedia(Media media, MultipartFile multipartFile) {
        return saveMediaImageStrategy.saveMedia(media,multipartFile);
    }
}
