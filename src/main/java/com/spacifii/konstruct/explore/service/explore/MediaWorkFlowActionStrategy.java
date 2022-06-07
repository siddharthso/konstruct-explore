package com.spacifii.konstruct.explore.service.explore;

import java.util.Set;

public interface MediaWorkFlowActionStrategy {

    void doAction(Set<String> mediaIds, Long subjectId);
}
