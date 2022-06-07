package com.spacifii.konstruct.explore.controller.showcase.advice;

import com.spacifii.konstruct.explore.exception.showcase.*;
import com.spacifii.konstruct.explore.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.spacifii.konstruct.explore.model.Error;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GenericControllerShowcaseAdvice {



    /**
     * This is Exception Handler for CustomerReviewNotFound
     * @param e
     * @return
     */
    @ExceptionHandler(ContainerMediaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleContainerMediaNotFoundException(ContainerMediaNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for ContainerMediaViewTypeNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(ContainerMediaViewTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleContainerMediaViewTypeNotFoundException(ContainerMediaViewTypeNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }


    /**
     * This is Exception Handler for MaterialNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(MaterialNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleMaterialNotFoundException(MaterialNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for OfferingProductNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(OfferingProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleOfferingProductNotFoundException(OfferingProductNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for OfferingProductViewTypeNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(OfferingProductViewTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleOfferingProductViewTypeNotFoundException(OfferingProductViewTypeNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for ShowcaseNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(ShowcaseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleShowcaseNotFoundException(ShowcaseNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }
}
