package com.spacifii.konstruct.explore.model;


public enum APIResponseKey {
    ALL_GOOD(2000, "Everything's good."),
    NEW_CREATION(1001, "New resource created."),
    DUPLICATE_CHANNEL_FOUND(4004,"Duplicate channel Found"),
    NO_CHANNEL_FOUND(4005,"NO channel Found"),
    DUPLICATE_TYPE_FOUND(4006,"Duplicate type found "),
    DUPLICATE_MODULE_MASTER_FOUND(4007,"Duplicate Module Master found" ),
    DUPLICATE_APPLICATION_MASTER_FOUND(4008,"Duplicate Application Master found" ),
    NO_SUBJECT_FOUND(40020,"Subject Not found" ),
    SUBJECT_DATA_CONFLICT(40019,"Subject Data is conflicting with other subject" ),
    INCORRECT_PASSWORD(40012, "Incorrect Password" ),
    NO_APPLICATION_MASTER_FOUND(40021,"Application Master Not Found" ),
    TOKEN_EXPIRED(40010,"Unauthenticated or Token Expired" ),
    RESOURCE_FORBIDDEN(40022,"You are not Allowed to access this service" ),
    TOKEN_NOT_FOUND(40023,"Token sent is not found" ),
    APPLICATION_MASTER_DATA_CONFLICT(40024, "Application Master Data has conflicts" ),
    NO_ROLE_FOUND(40025,"No Role Found" ),
    DUPLICATE_ROLE_MASTER_FOUND(40026,"Master Already Exists" ),
    NO_KEYWORD_FOUND(7001,"No Keyword/s found" ),
    DUPLICATE_DURATION_MASTER_FOUND(8001,"Duration Master Already Exists" ),
    DUPLICATE_PROPERTY_TYPE_FOUND(8002,"Property Type Already Exists" ),
    DUPLICATE_PROJECT_TYPE_FOUND(8003,"ExploreProject Type Already Exists" ),
    DUPLICATE_SPACE_FOUND(8004,"Space Already Exists" ),
    DUPLICATE_STYLE_FOUND(8005,"Style Already Exists" ),
    PROJECT_RANGE_CONFLICTS(8006,"ExploreProject Budget Range is already present" ),
    EXTENSION_ALREADY_EXISTS(3001, "Extension Already Exists" ),
    KEYWORD_ALREADY_EXISTS(3002, "Keyword Already Exists" ),
    PROJECT_NOT_FOUND(3003,"Explore Project Not Found" ),
    MEDIA_TYPE_EXTENSION_NOTFOUND(3004,"Extension is not supported" ),
    DUPLICATE_MEDIA_FOUND(3005, "Duplicate Media Found." ),
    MEDIA_NOT_FOUND(3006,"Media Not Found" ),
    MEDIA_IS_NOT_FOR_THIS_PROJECT(3007,"Media is Not Mapped with this Project" ),
    MEMBERSERVICE_EXCEPTION(3008,"Member Service has some error" ),
    MEMBERSERVICE_NOT_REACHABLE(3009,"Member Service is not reachable" ),
    CONCEPTBOARD_NOT_FOUND(3010,"ConceptBoard Not found" ),
    DUPLICATE_CONCEPTBOARD_NOT_FOUND(3011,"Duplicate ConceptBoard Found" ),
    CONCEPTBOARDMEIDA_DUPLICATE_FOUND(3012,"Duplicate ConceptBoardMedia for Same user present" ),
    CONCEPTBOARDMEDIA_NOT_FOUND(3013,"ConcpetBoardMedia not found" ),
    MEDIA_USER_ACTION_NOT_FOUND(3014,"Media User Action Not foud" ),
    PROJECT_USER_ACTION_NOT_FOUND(3015,"Project User Action Not Found" ),
    CONCEPTBOARD_USER_ACTION_NOT_FOUND(3016,"Concept User Action Not Found" ),
    CONCEPTBOARD_MEDIA_USER_ACTION_NOT_FOUND(3017,"Concept Media User Action Not Found" ),
    CONCEPTBOARDSHARE_NOT_FOUND(3018,"ConceptBoard Share  Not Found" ),
    MESSAGING_ENGINE_EXCEPTION(3019,"Messaging Engine thrown exception" ),
    MESSAGING_ENGINE_NOT_REACHABLE_EXCEPTION(3020,"Messaging Engine Not Reachable" ),
    CONCEPTBOARD_WALK_THROUGH_NOT_FOUND(3021,"ConceptBoard WalkThrough Not Found" ),
    EXPLORE_WALKTHROUGH_NOT_FOUND(3022,"Explore WalkThrough Not Found" ),
    CUSTOMER_TESTIMONIAL_REQUEST_ALREADY_THERE(3023,"Testimonial Request already exists for the same user and project" ),
    CUSTOMER_TESTIMONIAL_REQUEST_NOT_FOUND(3024,"Testimonial Request not found" ),
    CUSTOMER_TESTIMONIAL_NOT_FOUND(3025,"Testimonial Not Found" ),
    CUSTOMER_REVIEW_NOT_FOUND(3026,"Customer Review Not Found" ),
    CUSTOMER_REVIEW_REQUEST_ALREADY_THERE(3027,"Customer Review Request Already There " ),
    CUSTOMER_REVEIW_QUESTION_NOT_FOUND(3028,"Customer Review Qustion Not Found" ),
    USER_PROFILE_NOT_REACHABLE(3029,"User Profile Not Found" ),
    RECENTLY_SPACIFIED_PROJECT_NOT_FOUND(3030,"Recently Spacified Project Not Found" ),
    RECENTLY_SPACIFIED_PROJECT_ALREADY_FOUND(3031," Recently Spacified Project already exists" ),
    CONTAINER_MEDIA_VIEW_TYPE_NOT_FOUND(3032, "ContainerMediaViewType Not Found" ),
    CONTAINER_MEDIA_NOT_FOUND(3033,"Container Media Not Found" ),
    OFFERING_NOT_FOUND(3034,"Offering Product Not Found" ),
    MATERIAL_NOT_FOUND(3035,"Material not Found" ),
    OFFERING_PRODUCT_VIEW_TYPE_NOT_FOUND(3036,"Offerring Product View Type Not Found" ),
    SHOW_CASE_NOT_FOUND(3037,"Showcase not found" );

    private int code;
    private String message;

    APIResponseKey(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
