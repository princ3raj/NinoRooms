package nino.rooms.pgcompany.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "history")
public class NinoRooms implements Parcelable {


    public static final Creator<NinoRooms> CREATOR = new Creator<NinoRooms>() {
        @Override
        public NinoRooms createFromParcel(Parcel in) {
            return new NinoRooms(in);
        }

        @Override
        public NinoRooms[] newArray(int size) {
            return new NinoRooms[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int id;
    @ColumnInfo(name = "pg_name")
    @SerializedName("pg_name")
    private String pg_name;
    @ColumnInfo(name = "phone_number")
    @SerializedName("phone_number")
    private String phone_number;
    @ColumnInfo(name = "owner_name")
    @SerializedName("owner_name")
    private String owner_name;
    @ColumnInfo(name = "location")
    @SerializedName("location")
    private String location;
    @ColumnInfo(name = "details")
    @SerializedName("details")
    private  String details;
    @ColumnInfo(name = "ac_prices")
    @SerializedName("ac_prices")
    private  String ac_prices;
    @ColumnInfo(name = "non_ac_prices")
    @SerializedName("non_ac_prices")
    private  String non_ac_prices;
    @ColumnInfo(name = "pg_image")
    @SerializedName("pg_image")
    private String pg_image;
    @ColumnInfo(name = "pg_image_two")
    @SerializedName("pg_image_two")
    private String pg_image_two;
    @ColumnInfo(name = "pg_image_three")
    @SerializedName("pg_image_three")
    private String pg_image_three;

    public NinoRooms(int uid, int id, String pg_name, String phone_number, String owner_name, String location, String details, String ac_prices, String non_ac_prices, String pg_image, String pg_image_two, String pg_image_three) {
        this.uid = uid;
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

    protected NinoRooms(Parcel in) {
        uid = in.readInt();
        id = in.readInt();
        pg_name = in.readString();
        phone_number = in.readString();
        owner_name = in.readString();
        location = in.readString();
        details = in.readString();
        ac_prices = in.readString();
        non_ac_prices = in.readString();
        pg_image = in.readString();
        pg_image_two = in.readString();
        pg_image_three = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeInt(id);
        dest.writeString(pg_name);
        dest.writeString(phone_number);
        dest.writeString(owner_name);
        dest.writeString(location);
        dest.writeString(details);
        dest.writeString(ac_prices);
        dest.writeString(non_ac_prices);
        dest.writeString(pg_image);
        dest.writeString(pg_image_two);
        dest.writeString(pg_image_three);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
                "uid=" + uid +
                ", id=" + id +
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


