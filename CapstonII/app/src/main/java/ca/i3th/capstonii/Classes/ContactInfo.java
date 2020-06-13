package ca.i3th.capstonii.Classes;

public class ContactInfo {
    private  String country;
    private  String region;
    private  String city;
    private  String street;
    private  int buldingNum;
    private  int unit;
    private  String postalCode;

    public ContactInfo(String country, String region, String city, String postalCode) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = "i3th";
        this.buldingNum = 13;
        this.unit = 13;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuldingNum() {
        return buldingNum;
    }

    public void setBuldingNum(int buldingNum) {
        this.buldingNum = buldingNum;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buldingNum=" + buldingNum +
                ", unit=" + unit +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
