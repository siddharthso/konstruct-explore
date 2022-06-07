package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.Media;
import org.springframework.web.multipart.MultipartFile;

public interface SaveMediaStrategy {

    Media saveMedia(Media media, MultipartFile multipartFile);
}
