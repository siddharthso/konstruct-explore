package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.STATUS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * This is Repository class for Media
 */
@Repository
public interface MediaRepository extends MongoRepository<Media, String> {

    /**
     * This repository method returns Media by Checksum
     * @param checksum
     * @return
     */
    Media findFirstByChecksum(String checksum);

    /**
     * This repository method returns Media by Checksum and projectId
     * @param checksum
     * @param projectId
     * @return
     */
    Media findFirstByChecksumAndProjectId(String checksum, String projectId);

    /**
     * This repository method returns medias by ProjectId
     * @param exploreProjectId
     * @return
     */
    List<Media> findByProjectId(String exploreProjectId);

    /**
     * This repository method is used to return medias that are approved
     * @param exploreProjectId
     * @param status
     * @return
     */
    List<Media> findByProjectIdAndStatusAndActiveTrue(String exploreProjectId, STATUS status);
    /**
     * This repository method returns medias by subjectId
     * @param subjectId
     * @return
     */
    List<Media> findBySubjectId(String subjectId);

    /**
     * This repository method is used to find externalUrls Present in Database
     * @param urls
     * @return
     */
    List<Media> findByProjectIdAndExternalUrlIn(String projectId,Set<String> urls);
}
