package com.spacifii.konstruct.explore.service.explore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.query.SearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QueryUtilService {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    MongoOperations mongoOperations;


    /**
     * This method returns filtered output with pagination and filter
     * @param filterRequestDto
     * @param t
     * @param <T>
     * @return
     */
    public <T> Page<T> getResultList(FilterRequestDto filterRequestDto, Set<String> defaultSortKey , T t){
        Class aClass = t.getClass();

        List<String> sortKeys = new ArrayList<>();
        if(filterRequestDto.getSortKeys().size() > 0){
            sortKeys.addAll(filterRequestDto.getSortKeys());
        } else {
            sortKeys.addAll(defaultSortKey);
        }

        Query query = searchQuery(filterRequestDto.getSearchQuery());
        Sort sort = new Sort(filterRequestDto.getDirection(),sortKeys);
        Pageable pageable = new PageRequest(filterRequestDto.getPage()-1,filterRequestDto.getSize(),sort);
        query.with(pageable);

        List<T> ts = mongoOperations.find(query,aClass);

        Long count = mongoOperations.count(query,aClass);

        return new PageImpl<T>(ts,pageable,count);
    }


    /**
     * This method returns adhoc Request output
     * @param searchQueries
     * @param t
     * @param <T>
     * @return
     */
    public <T> List<List<Map<String, Object>>> findAdhoc(List<SearchQuery> searchQueries,T t){

        Class aClass = t.getClass();

        List<List<Map<String, Object>>> searchResults = new ArrayList<>();
        if (searchQueries == null) {
            return searchResults;
        }
        for (SearchQuery searchQuery: searchQueries) {
            List<T> medias = mongoOperations.find(searchQuery(searchQuery),aClass);
            searchResults.add(searchResult(searchQuery.getRequiredKeys(), medias));
        }

        return searchResults;
    }

    /**
     * This Method Returns Query From Search
     * @param searchQuery
     * @return
     */
    public Query searchQuery(SearchQuery searchQuery) {
        Criteria criteria = new Criteria();
        if(searchQuery != null) {
            Map<String, List<Object>> where = searchQuery.getWhere();
            if (where != null) {
                for (Map.Entry<String, List<Object>> entry : where.entrySet()) {
                    String key = entry.getKey();
                    List<Object> value = entry.getValue();
                    if (value.size() == 1) {
                        criteria.and(key).is(value.get(0));
                    } else {
                        criteria.and(key).in(value);
                    }
                }
            }
        }
        return new Query(criteria);
    }

    /**
     * This method returns only those keys which were asked
     * @param requiredKeys
     * @param ts
     * @param <T>
     * @return
     */
    public <T> List<Map<String, Object>> searchResult(List<String> requiredKeys, List<T> ts) {

        List<Map<String, Object>> queryResults = new ArrayList<>();
        try {


            for (T t : ts) {

                Map<String, Object> queryData = JsonFlattener.flattenAsMap(OBJECT_MAPPER.writeValueAsString(t));
                if (requiredKeys.size() > 0) {

                    Map<String, Object> data = new LinkedHashMap<>();
                    for (String requiredKey : requiredKeys) {
                        Object objectValue = queryData.get(requiredKey);
                        if (objectValue != null) {
                            data.put(requiredKey, objectValue);
                        }
                    }
                    queryResults.add(data);
                } else {

                    Map<String, Object> data = new LinkedHashMap<>();
                    for (Map.Entry<String, Object> entry : queryData.entrySet()) {
                        Object objectValue = entry.getValue();
                        if (objectValue != null) {
                            data.put(entry.getKey(), objectValue);
                        }
                    }
                    queryResults.add(data);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
            //TODO: Throw some  RuntimeException
        }

        return queryResults;
        }

}
