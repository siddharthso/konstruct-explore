package com.spacifii.konstruct.explore.repository.recentlyspacified;

import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedProject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for RecentlySpacifiedProject
 */
@Repository
public interface RecentlySpacifiedProjectRepository extends MongoRepository<RecentlySpacifiedProject,String> {

    /**
     * This repository method is used to get Recently Spacified Project via RouteUrl
     * @param routeUrl
     * @return
     */
    RecentlySpacifiedProject findFirstByRouteUrl(String routeUrl);

}
