package com.spacifii.konstruct.explore.controller.conceptBoard.advice;

import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardMediaDuplicateForSameSubjectException;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardMediaNotFoundException;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardNotFoundException;
import com.spacifii.konstruct.explore.exception.conceptBoard.DuplicateConceptBoardFoundException;
import com.spacifii.konstruct.explore.exception.explore.DuplicateMediaFoundException;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * This is controller advice of exception handling of ConceptBoard
 */
@ControllerAdvice
public class GenericControllerConceptBoardAdvice {


    /**
     * This is Exception Handler for ConceptBoardMediaDuplicateForSameSubjectException
     * @param e
     * @return
     */
    @ExceptionHandler(ConceptBoardMediaDuplicateForSameSubjectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleConceptBoardMediaDuplicateForSameSubjectException(ConceptBoardMediaDuplicateForSameSubjectException e){
        List<Error> errors = new ArrayList<>();
        String message = e.getApiResponseKey() + "Filename : " + e.getFileaname();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for ConceptBoardMediaNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(ConceptBoardMediaNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleConceptBoardMediaNotFoundException(ConceptBoardMediaNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for ConceptBoardNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(ConceptBoardNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleConceptBoardNotFoundException(ConceptBoardNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for DuplicateConceptBoardFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateConceptBoardFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleDuplicateConceptBoardFoundException(DuplicateConceptBoardFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }
}
