package nino.rooms.pgcompany.model;

import com.google.gson.annotations.SerializedName;

public class NinoRooms {

    @SerializedName("id")
    private int id;

    @SerializedName("pg_name")
    private String pg_name;

    @SerializedName("phone_number")
    private String phone_number;

    @SerializedName("owner_name")
    private String owner_name;

    @SerializedName("location")
    private String location;

    @SerializedName("details")
    private  String details;

    @SerializedName("ac_prices")
    private  String ac_prices;

    @SerializedName("non_ac_prices")
    private  String non_ac_prices;


    @SerializedName("pg_image")
    private String pg_image;

    @SerializedName("pg_image_two")
    private String pg_image_two;

    @SerializedName("pg_image_three")
    private  String pg_image_three;


    public NinoRooms(int id, String pg_name, String phone_number, String owner_name, String location, String details, String ac_prices, String non_ac_prices, String pg_image, String pg_image_two, String pg_image_three) {
        this.id = id;
        this.pg_name = pg_name;
        this.phone_number = phone_number;
        this.owner_name = owner_name;
        this.location = location;
        this.details = details;
        this.ac_prices = ac_prices;
        this.non_ac_prices = non_ac_prices;
        this.pg_image = pg_image;
        this.pg_image_two = pg_image_two;
        this.pg_image_three = pg_image_three;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPg_name() {
        return pg_name;
    }

    public void setPg_name(String pg_name) {
        this.pg_name = pg_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAc_prices() {
        return ac_prices;
    }

    public void setAc_prices(String ac_prices) {
        this.ac_prices = ac_prices;
    }

    public String getNon_ac_prices() {
        return non_ac_prices;
    }

    public void setNon_ac_prices(String non_ac_prices) {
        this.non_ac_prices = non_ac_prices;
    }

    public String getPg_image() {
        return pg_image;
    }

    public void setPg_image(String pg_image) {
        this.pg_image = pg_image;
    }

    public String getPg_image_two() {
        return pg_image_two;
    }

    public void setPg_image_two(String pg_image_two) {
        this.pg_image_two = pg_image_two;
    }

    public String getPg_image_three() {
        return pg_image_three;
    }

    public void setPg_image_three(String pg_image_three) {
        this.pg_image_three = pg_image_three;
    }

    @Override
    public String toString() {
        return "NinoRooms{" +
                "id=" + id +
                ", pg_name='" + pg_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", location='" + location + '\'' +
                ", details='" + details + '\'' +
                ", ac_prices='" + ac_prices + '\'' +
                ", non_ac_prices='" + non_ac_prices + '\'' +
                ", pg_image='" + pg_image + '\'' +
                ", pg_image_two='" + pg_image_two + '\'' +
                ", pg_image_three='" + pg_image_three + '\'' +
                '}';
    }
}


