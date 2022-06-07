package com.spacifii.konstruct.explore.controller.explore.advice;

import com.spacifii.konstruct.explore.exception.explore.*;
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
public class GenericControllerAdvice {

    /**
     * This is Exception Handler for DuplicateMediaFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateMediaFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleDuplicateMediaFoundException(DuplicateMediaFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }


    /**
     * This is Exception Handler for MemberServiceNotReabableException
     * @param e
     * @return
     */
    @ExceptionHandler(MemberServiceNotReabableException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public @ResponseBody
    ApiResponse<Void> handleMemberServiceNotReabableException(MemberServiceNotReabableException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for MemberServiceException
     * @param e
     * @return
     */
    @ExceptionHandler(MemberServiceException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public @ResponseBody
    ApiResponse<Void> handleMemberServiceException(MemberServiceException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }


    /**
     * This is Exception Handler for MediasNotForThisProjectException
     * @param e
     * @return
     */
    @ExceptionHandler(MediasNotForThisProjectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleMediasNotForThisProjectException(MediasNotForThisProjectException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for ExtensionAlreadyExistsException
     * @param e
     * @return
     */
    @ExceptionHandler(ExtensionAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleExtensionAlreadyExistsException(ExtensionAlreadyExistsException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for KeywordAlreadyExistsException
     * @param e
     * @return
     */
    @ExceptionHandler(KeywordAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse<Void> handleKeywordAlreadyExistsException(KeywordAlreadyExistsException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for KeywordNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(KeywordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleKeywordNotFoundException(KeywordNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for MediaNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(MediaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleMediaNotFoundException(MediaNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }


    /**
     * This is Exception Handler for MediaTypeExtensionNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(MediaTypeExtensionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleMediaTypeExtensionNotFoundException(MediaTypeExtensionNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }

    /**
     * This is Exception Handler for ProjectNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiResponse<Void> handleProjectNotFoundException(ProjectNotFoundException e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(e.getApiResponseKey().getCode(), e.getApiResponseKey().getMessage()));

        return new ApiResponse<>(e.getApiResponseKey(),null,errors);
    }


}
