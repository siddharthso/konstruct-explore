package com.spacifii.konstruct.explore.controller.recentlyspacified.advice;

import com.spacifii.konstruct.explore.exception.recentlyspacified.*;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GenericControllerRecentySpacifiedAdvice {

    /**
     * This is Exception Handler for CustomerReviewNotFound
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerReviewNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleCustomerReviewNotFound(CustomerReviewNotFound e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }


    /**
     * This is Exception Handler for CustomerReviewQuestionNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerReviewQuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleCustomerReviewQuestionNotFoundException(CustomerReviewQuestionNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }


    /**
     * This is Exception Handler for CustomerTestimonialNotFound
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerTestimonialNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleCustomerTestimonialNotFound(CustomerTestimonialNotFound e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }


    /**
     * This is Exception Handler for RecentlySpacifiedProjectNotFound
     * @param e
     * @return
     */
    @ExceptionHandler(RecentlySpacifiedProjectNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleRecentlySpacifiedProjectNotFound(RecentlySpacifiedProjectNotFound e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }


    /**
     * This is Exception Handler for CustomerReviewRequestAlreadyInitiated
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerReviewRequestAlreadyInitiated.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleCustomerReviewRequestAlreadyInitiated(CustomerReviewRequestAlreadyInitiated e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for CustomerReviewRequestAlreadyInitiated
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerTestimonialIniateRequestNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleCustomerTestimonialIniateRequestNotFound(CustomerTestimonialIniateRequestNotFound e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for CustomerTestimonialRequestAlreadyInitiated
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerTestimonialRequestAlreadyInitiated.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleCustomerTestimonialRequestAlreadyInitiated(CustomerTestimonialRequestAlreadyInitiated e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for RecentlySpacifiedProjectAlreadyExisits
     * @param e
     * @return
     */
    @ExceptionHandler(RecentlySpacifiedProjectAlreadyExisits.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleRecentlySpacifiedProjectAlreadyExisits(RecentlySpacifiedProjectAlreadyExisits e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }


}
