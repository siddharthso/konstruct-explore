package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedSpacifiiColor;
import com.spacifii.konstruct.explore.service.explore.SpacifiiColorService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This is service class for SPACIFII COLOR
 */
@Service
public class SpacifiiColorServiceImpl implements SpacifiiColorService {
    /**
     * This service method is used to get All EnvelopedSpacifiiColor
     *
     * @return
     */
    @Override
    public Set<EnvelopedSpacifiiColor> getEnvelopedSpacifiiColor() {
        Map<String, EnvelopedSpacifiiColor> stringSpacifiiColorMap = ImagePropertiesCalculator.getStringEnvelopedSpacifiiColorMap();
        Set<EnvelopedSpacifiiColor> envelopedSpacifiiColors = new TreeSet<>();
        for (Map.Entry<String,EnvelopedSpacifiiColor> envelopedSpacifiiColorEntry: stringSpacifiiColorMap.entrySet()) {
            envelopedSpacifiiColors.add(envelopedSpacifiiColorEntry.getValue());
        }
        return envelopedSpacifiiColors;
    }
}
