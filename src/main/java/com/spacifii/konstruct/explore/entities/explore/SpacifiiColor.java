package com.spacifii.konstruct.explore.entities.explore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(collection = "SPACIFII_COLOR")
public class SpacifiiColor implements Comparable<SpacifiiColor>{

    @Id
    @Field("HEX_CODE")
    private String hexCode;

    @Field("COLOR_NAME")
    private String colorName;

    @Field("HASH_HEXTCODE")
    private String hashHexCode;

    @Field("HUE")
    private BigDecimal hue;

    @Field("SATURATION")
    private BigDecimal saturation;

    @Field("LUMINANCE")
    private BigDecimal luminance;

    @Field("RED")
    private BigDecimal red;

    @Field("GREEN")
    private BigDecimal green;

    @Field("BLUE")
    private BigDecimal blue;

    @Field("PRIMARY_COLOR_NAME")
    private String primaryColorName;

    @Field("PRIMARY")
    private Boolean primary;

    @Field("PRIMARY_COLOR_HEX")
    private String primaryColorHex;

    @Field("PRIMARY_COLOR_HASH_HEX")
    private String primaryColorHashHex;



    public String getPrimaryColorHex() {
        return primaryColorHex;
    }

    public void setPrimaryColorHex(String primaryColorHex) {
        this.primaryColorHex = primaryColorHex;
    }

    public String getPrimaryColorHashHex() {
        return primaryColorHashHex;
    }

    public void setPrimaryColorHashHex(String primaryColorHashHex) {
        this.primaryColorHashHex = primaryColorHashHex;
    }

    public String getPrimaryColorName() {
        return primaryColorName;
    }

    public void setPrimaryColorName(String primaryColorName) {
        this.primaryColorName = primaryColorName;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getHashHexCode() {
        return hashHexCode;
    }

    public void setHashHexCode(String hashHexCode) {
        this.hashHexCode = hashHexCode;
    }

    public BigDecimal getHue() {
        return hue;
    }

    public void setHue(BigDecimal hue) {
        this.hue = hue;
    }

    public BigDecimal getSaturation() {
        return saturation;
    }

    public void setSaturation(BigDecimal saturation) {
        this.saturation = saturation;
    }


    public BigDecimal getLuminance() {
        return luminance;
    }

    public void setLuminance(BigDecimal luminance) {
        this.luminance = luminance;
    }

    public BigDecimal getRed() {
        return red;
    }

    public void setRed(BigDecimal red) {
        this.red = red;
    }

    public BigDecimal getGreen() {
        return green;
    }

    public void setGreen(BigDecimal green) {
        this.green = green;
    }

    public BigDecimal getBlue() {
        return blue;
    }

    public void setBlue(BigDecimal blue) {
        this.blue = blue;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    @Override
    public String toString() {
        return "SpacifiiColor{" +
                "hexCode='" + hexCode + '\'' +
                ", colorName='" + colorName + '\'' +
                ", hashHexCode='" + hashHexCode + '\'' +
                ", hue=" + hue +
                ", saturation=" + saturation +
                ", luminance=" + luminance +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", primaryColorName='" + primaryColorName + '\'' +
                ", primary=" + primary +
                ", primaryColorHex='" + primaryColorHex + '\'' +
                ", primaryColorHashHex='" + primaryColorHashHex + '\'' +
                '}';
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(SpacifiiColor o) {
        return this.getHue().compareTo(o.getHue());
    }
}
