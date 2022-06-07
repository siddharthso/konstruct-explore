package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.CloudVisionResponse;
import com.spacifii.konstruct.explore.entities.explore.SpacifiiColor;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedSpacifiiColor;
import com.spacifii.konstruct.explore.repository.explore.SpacifiiColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImagePropertiesCalculator {

    @Autowired
    SpacifiiColorRepository spacifiiColorRepository;


    @Value("${primaryColorFileName}")
    private String primaryColorFileName;

    @Value("${colorFileName}")
    private String colorFileName;

    @Value("${fillColorDatabase}")
    private boolean fillDatabase;


    private static Map<String, SpacifiiColor> knownColorsMap = new HashMap<>();
    private static Map<String, SpacifiiColor> primaryColorsMap = new HashMap<>();
    private static Map<String,String> primaryColorNameToHexCode = new HashMap<>();
    private static Map<String,Color> stringColorMap = new HashMap<>();
    private static List<Color> knownColor = new ArrayList<>();
    private static Map<Integer,String> integerHexCodeMap = new HashMap<>();
    private static Map<String, SpacifiiColor> nameSpacifiiColors = new HashMap<>();
    private static Map<String, EnvelopedSpacifiiColor> stringEnvelopedSpacifiiColorMap = new HashMap<>();


     @PostConstruct
    public void fillMap(){
        if(fillDatabase){
           fillDatabase();
        }
        knownColorsMap.putAll(spacifiiColorRepository.findAll().stream()
                .collect(Collectors.toMap(SpacifiiColor::getHexCode, Function.identity())));

         primaryColorsMap.putAll(spacifiiColorRepository.findByPrimaryTrue().stream()
                 .collect(Collectors.toMap(SpacifiiColor::getColorName,Function.identity())));

         for(Map.Entry<String,SpacifiiColor> entry: primaryColorsMap.entrySet()){
             primaryColorNameToHexCode.put(entry.getValue().getColorName(),entry.getValue().getHexCode());
             EnvelopedSpacifiiColor envelopedSpacifiiColor = new EnvelopedSpacifiiColor();
             envelopedSpacifiiColor.setPrimaryColor(entry.getValue());
             stringEnvelopedSpacifiiColorMap.put(entry.getValue().getColorName(),envelopedSpacifiiColor);
         }

        for (Map.Entry<String,SpacifiiColor> entry: knownColorsMap.entrySet()) {
            System.out.println(entry.getValue().toString());
            Color color = Color.decode(entry.getValue().getHashHexCode());
            knownColor.add(color);
            integerHexCodeMap.put(color.getRGB(),entry.getKey());
            stringColorMap.put(entry.getKey(),color);

            if(!entry.getValue().getPrimary()){
                System.out.println(entry.getValue().toString());
                EnvelopedSpacifiiColor envelopedSpacifiiColor =
                        stringEnvelopedSpacifiiColorMap.get(entry.getValue().getPrimaryColorName());
                Set<SpacifiiColor> spacifiiColors = envelopedSpacifiiColor.getChildColors();
                spacifiiColors.add(entry.getValue());
                envelopedSpacifiiColor.setChildColors(spacifiiColors);

                stringEnvelopedSpacifiiColorMap.put(entry.getValue().getPrimaryColorName(),envelopedSpacifiiColor);
            }

        }
         System.out.println("HERE");
    }


    public static Map<String, EnvelopedSpacifiiColor> getStringEnvelopedSpacifiiColorMap(){
         return stringEnvelopedSpacifiiColorMap;
    }

    //@PostConstruct
    public void fillDatabase(){
        spacifiiColorRepository.deleteAll();
        List<String> strings = new ArrayList<>();
        List<String> primary  = new ArrayList<>();

        //read file into stream, try-with-resources


        try (Stream<String> stream = Files.lines(Paths.get(primaryColorFileName))) {

            primary = stream.map(String::toUpperCase).collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }


        try (Stream<String> stream = Files.lines(Paths.get(colorFileName))) {


            strings = stream.map(String::toUpperCase).collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }


        for (String s: primary) {
            String[] sNew = s.split(",");
            SpacifiiColor spacifiiColor = new SpacifiiColor();
            spacifiiColor.setColorName(sNew[0].trim().toUpperCase());
            spacifiiColor.setHexCode(sNew[1].toUpperCase().toUpperCase());
            spacifiiColor.setHashHexCode("#"+spacifiiColor.getHexCode());
            spacifiiColor.setRed(new BigDecimal(sNew[2].trim()));
            spacifiiColor.setGreen(new BigDecimal(sNew[3].trim()));
            spacifiiColor.setBlue(new BigDecimal(sNew[4].trim()));
            spacifiiColor.setHue(new BigDecimal(sNew[5].trim()));
            spacifiiColor.setSaturation(new BigDecimal(sNew[6].trim()));
            spacifiiColor.setLuminance(new BigDecimal(sNew[7].trim()));
            spacifiiColor.setPrimary(true);
            spacifiiColor.setPrimaryColorName(spacifiiColor.getColorName());
            spacifiiColor.setPrimaryColorHex(spacifiiColor.getHexCode());
            spacifiiColor.setPrimaryColorHashHex(spacifiiColor.getHashHexCode());

            System.out.println(spacifiiColor.toString());


            spacifiiColorRepository.save(spacifiiColor);
            nameSpacifiiColors.put(spacifiiColor.getColorName(),spacifiiColor);



        }

        for (String s: strings) {
            String[] sNew = s.split(",");
            SpacifiiColor spacifiiColor = new SpacifiiColor();
            spacifiiColor.setColorName(sNew[0].trim().toUpperCase());
            if(nameSpacifiiColors.get(spacifiiColor.getColorName()) != null){
                continue;
            }
            spacifiiColor.setHexCode(sNew[2].toUpperCase().toUpperCase());
            spacifiiColor.setHashHexCode("#"+spacifiiColor.getHexCode());
            spacifiiColor.setRed(new BigDecimal(sNew[3].trim()));
            spacifiiColor.setGreen(new BigDecimal(sNew[4].trim()));
            spacifiiColor.setBlue(new BigDecimal(sNew[5].trim()));
            spacifiiColor.setHue(new BigDecimal(sNew[6].trim()));
            spacifiiColor.setSaturation(new BigDecimal(sNew[7].trim()));
            spacifiiColor.setLuminance(new BigDecimal(sNew[8].trim()));
            spacifiiColor.setPrimary(false);
            String primaryColorname = sNew[1].trim().toUpperCase();

            SpacifiiColor primaryColor = nameSpacifiiColors.get(primaryColorname);
            spacifiiColor.setPrimaryColorName(primaryColor.getColorName());
            spacifiiColor.setPrimaryColorHex(primaryColor.getHexCode());
            spacifiiColor.setPrimaryColorHashHex(primaryColor.getHashHexCode());
            System.out.println(spacifiiColor.toString());
            spacifiiColorRepository.save(spacifiiColor);
        }

       /* for (String s: strings) {
            String[] sNew = s.split(",");
            hexToName.put("#"+sNew[0],sNew[1]);
        }

        for (String s: primary) {
            String[] sNew = s.split(",");
            hexToNamePrimary.put("#"+sNew[0],sNew[1]);
        }*/

        System.out.println("here");


    }

    public static CloudVisionResponse updateCloudVisionImgeResponseAttributes(CloudVisionResponse cloudVisionResponse){


        int red = cloudVisionResponse.getRed().intValue();
        int blue =  cloudVisionResponse.getBlue().intValue();
        int green = cloudVisionResponse.getGreen().intValue();

        Color color = new Color( red,green,blue);

        String hex = Integer.toHexString( color.getRGB() & 0x00ffffff);
        Color nearestColor = someNearest(color);
        cloudVisionResponse.setHexCode(hex);
        cloudVisionResponse.setHashHexCode("#"+hex);

        cloudVisionResponse.mergeSpacifiiColorInMe(knownColorsMap.get(integerHexCodeMap.get(nearestColor.getRGB())));

        return cloudVisionResponse;

    }


    static Color  someNearest(Color c1){

        Color nearstColor = null;
        double distance = 99999999;

        for (Color c2: knownColor) {
            double calculated = colorDistance(c1,c2);
            if(distance > calculated){
                distance = calculated;
                nearstColor =  c2;
            }
        }

        return nearstColor;

    }

    static double colorDistance(Color c1, Color c2)
    {
        int red1 = c1.getRed();
        int red2 = c2.getRed();
        int rmean = (red1 + red2) >> 1;
        int r = red1 - red2;
        int g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        return Math.sqrt((((512+rmean)*r*r)>>8) + 4*g*g + (((767-rmean)*b*b)>>8));
    }
}
