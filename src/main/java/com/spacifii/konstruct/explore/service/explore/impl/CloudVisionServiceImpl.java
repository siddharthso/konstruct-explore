package com.spacifii.konstruct.explore.service.explore.impl;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.spacifii.konstruct.explore.entities.explore.CloudVisionResponse;
import com.spacifii.konstruct.explore.entities.explore.CloudVisionType;
import com.spacifii.konstruct.explore.entities.explore.Vertices;
import com.spacifii.konstruct.explore.service.explore.CloudVisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CloudVisionServiceImpl implements CloudVisionService {

    @Autowired
    ImageAnnotatorClient imageAnnotatorClient;

  /*  @PostConstruct
    public void doPostConstruct() throws IOException {
        CredentialsProvider credentialsProvider = FixedCredentialsProvider.
                create(ServiceAccountCredentials.
                        fromStream(new FileInputStream("loginapis-189310-2c3ff929e93d.json")));


        ImageAnnotatorSettings clientSettings = ImageAnnotatorSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .build();

        imageAnnotatorClient.create(clientSettings);
    }
*/

    /**
     * This service method is used to detect labels from Image
     * @param file
     * @return
     */
    public Map<String, Float> detectLables(MultipartFile file) {
        try {

           /* byte[] imageBytes = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(imageBytes); //read file into bytes[]
            fis.close();*/

            byte[] imageBytes = file.getBytes();

            BatchAnnotateImagesResponse responses;

            Image image = Image.newBuilder().setContent(ByteString.copyFrom(imageBytes)).build();

            // Sets the type of request to label detection, to detect broad sets of categories in an image.
            Feature feature = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();

            AnnotateImageRequest request =
                    AnnotateImageRequest.newBuilder().setImage(image).addFeatures(feature)
                            .build();

            responses = this.imageAnnotatorClient.batchAnnotateImages(Collections.singletonList(request));


            // We're only expecting one response.
            if (responses.getResponsesCount() == 1) {
                AnnotateImageResponse response = responses.getResponses(0);

                if (response.hasError()) {
                    throw new Exception(response.getError().getMessage());
                }
                Map<String,Float> stringFloatMap = new HashMap<>();
                for (EntityAnnotation annotation : response.getLabelAnnotationsList()) {
                    stringFloatMap.put(annotation.getDescription(),annotation.getScore());
                    System.out.println("Description: " + annotation.getDescription());
                    System.out.println("Score: " + annotation.getScore());
                }
                return stringFloatMap;

            }


        } catch (Exception e){
            e.printStackTrace();
            //TODO: LOG here
        }
        return null;
    }

    /**
     * This service method is used to detect labels and ImageProperties from Image
     *
     * @param file
     * @return
     */
    @Override
    public List<CloudVisionResponse> detectLablesAndImageProperties(MultipartFile file) {

        try {
            byte[] imageBytes = file.getBytes();

            BatchAnnotateImagesResponse responses;

            Image image = Image.newBuilder().setContent(ByteString.copyFrom(imageBytes)).build();

            // Sets the type of request to label detection, to detect broad sets of categories in an image.
            Feature feature = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();

            Feature featureObjectDetection = Feature.newBuilder().setType(Feature.Type.OBJECT_LOCALIZATION).build();


            Feature featureImageProperties = Feature.newBuilder().setType(Feature.Type.IMAGE_PROPERTIES).build();
            //Feature.Type.I
            AnnotateImageRequest request =
                    AnnotateImageRequest.newBuilder().setImage(image).addAllFeatures(Arrays.asList(feature, featureImageProperties,featureObjectDetection))
                            .build();

            responses = this.imageAnnotatorClient.batchAnnotateImages(Collections.singletonList(request));


            // We're only expecting one response.
            if (responses.getResponsesCount() == 1) {
                AnnotateImageResponse response = responses.getResponses(0);

                if (response.hasError()) {
                    throw new Exception(response.getError().getMessage());
                }
                List<CloudVisionResponse> cloudVisionResponses = new ArrayList<>();
                for (EntityAnnotation annotation : response.getLabelAnnotationsList()) {

                    CloudVisionResponse cloudVisionResponse = new CloudVisionResponse();
                    cloudVisionResponse.setCloudVisionType(CloudVisionType.LABEL_DETECTION);
                    cloudVisionResponse.setLabel(annotation.getDescription());
                    cloudVisionResponse.setScore(BigDecimal.valueOf(annotation.getScore()));
                    cloudVisionResponses.add(cloudVisionResponse);
                    //stringFloatMap.put(annotation.getDescription(),annotation.getScore());
                    System.out.println("Description: " + annotation.getDescription());
                    System.out.println("Score: " + annotation.getScore());
                }


                for (ColorInfo colorInfo : response.getImagePropertiesAnnotation().getDominantColors().getColorsList()) {
                    CloudVisionResponse cloudVisionResponse = new CloudVisionResponse();
                    cloudVisionResponse.setCloudVisionType(CloudVisionType.IMAGE_PROPERTIES);
                    cloudVisionResponse.setScore(BigDecimal.valueOf(colorInfo.getScore()));
                    cloudVisionResponse.setRed(BigDecimal.valueOf(colorInfo.getColor().getRed()));
                    cloudVisionResponse.setGreen(BigDecimal.valueOf(colorInfo.getColor().getGreen()));
                    cloudVisionResponse.setBlue(BigDecimal.valueOf(colorInfo.getColor().getBlue()));
                    cloudVisionResponse.setPixelFraction(BigDecimal.valueOf(colorInfo.getPixelFraction()));
                    cloudVisionResponses.add(cloudVisionResponse);
                    System.out.println("Color: " + colorInfo.getColor());
                    System.out.println("Score: " + colorInfo.getScore());
                }

                for(LocalizedObjectAnnotation entity : response.getLocalizedObjectAnnotationsList()){
                    CloudVisionResponse cloudVisionResponse = new CloudVisionResponse();
                    cloudVisionResponse.setCloudVisionType(CloudVisionType.OBJECT_LOCALIZATION);
                    cloudVisionResponse.setLabel(entity.getName());
                    cloudVisionResponse.setScore(BigDecimal.valueOf(entity.getScore()));
                    List<Vertices> vertices = new ArrayList<>();
                    for (NormalizedVertex v:entity.getBoundingPoly().getNormalizedVerticesList()) {

                        vertices.add(new Vertices(BigDecimal.valueOf(v.getX()),BigDecimal.valueOf(v.getY())));
                    }
                    cloudVisionResponse.setVerticesList(vertices);
                    cloudVisionResponses.add(cloudVisionResponse);
                    System.out.println("LABEL: " + cloudVisionResponse.getLabel());
                    System.out.println("SCORE: " + cloudVisionResponse.getScore());
                    System.out.println("VERTICES: " + cloudVisionResponse.getVerticesList().toString());


                }

                return cloudVisionResponses;

            }


        } catch (Exception e) {
            e.printStackTrace();
            //TODO: LOG here
        }
        return null;
    }

}
