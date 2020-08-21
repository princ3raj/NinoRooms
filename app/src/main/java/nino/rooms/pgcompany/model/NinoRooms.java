package nino.rooms.pgcompany.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "history")
public class NinoRooms implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    private int uid;

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
    private String details;

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

    @ColumnInfo(name = "ac_prices")
    @SerializedName("ac_prices")
    private String ac_prices;
    @ColumnInfo(name = "non_ac_prices")
    @SerializedName("non_ac_prices")
    private String non_ac_prices;
    @ColumnInfo(name = "pg_image")
    @SerializedName("pg_image")
    private String pg_image;
    @ColumnInfo(name = "pg_image_two")
    @SerializedName("pg_image_two")
    private String pg_image_two;
    @ColumnInfo(name = "pg_image_three")
    @SerializedName("pg_image_three")
    private String pg_image_three;
    @ColumnInfo(name = "pg_type")
    @SerializedName("pg_type")
    private String pg_type;
    @ColumnInfo(name = "pg_image_four")
    @SerializedName("pg_image_four")
    private String pg_image_four;
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private String id;
    @ColumnInfo(name = "bookmark")
    private String bookmark;
    @ColumnInfo(name = "history")
    private String history;
    @ColumnInfo(name = "address")
    @SerializedName("address")
    private String address;
    @ColumnInfo(name = "pg_image_five")
    @SerializedName("pg_image_five")
    private String pg_image_five;

    protected NinoRooms(Parcel in) {
        uid = in.readInt();
        pg_name = in.readString();
        phone_number = in.readString();
        owner_name = in.readString();
        location = in.readString();
        details = in.readString();
        pg_type = in.readString();
        ac_prices = in.readString();
        non_ac_prices = in.readString();
        pg_image = in.readString();
        pg_image_two = in.readString();
        pg_image_three = in.readString();
        pg_image_four = in.readString();
        pg_image_five = in.readString();
        id = in.readString();
        bookmark = in.readString();
        history = in.readString();
        address = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public NinoRooms(int uid, String pg_name, String phone_number, String owner_name, String location, String details, String pg_type, String ac_prices, String non_ac_prices, String pg_image, String pg_image_two, String pg_image_three, String pg_image_four, String pg_image_five, String id, String bookmark, String history, String address) {
        this.uid = uid;
        this.pg_name = pg_name;
        this.phone_number = phone_number;
        this.owner_name = owner_name;
        this.location = location;
        this.details = details;
        this.pg_type = pg_type;
        this.ac_prices = ac_prices;
        this.non_ac_prices = non_ac_prices;
        this.pg_image = pg_image;
        this.pg_image_two = pg_image_two;
        this.pg_image_three = pg_image_three;
        this.pg_image_four = pg_image_four;
        this.pg_image_five = pg_image_five;
        this.id = id;
        this.bookmark = bookmark;
        this.history = history;
        this.address = address;
    }

    public static Creator<NinoRooms> getCREATOR() {
        return CREATOR;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(pg_name);
        dest.writeString(phone_number);
        dest.writeString(owner_name);
        dest.writeString(location);
        dest.writeString(details);
        dest.writeString(pg_type);
        dest.writeString(ac_prices);
        dest.writeString(non_ac_prices);
        dest.writeString(pg_image);
        dest.writeString(pg_image_two);
        dest.writeString(pg_image_three);
        dest.writeString(pg_image_four);
        dest.writeString(pg_image_five);
        dest.writeString(id);
        dest.writeString(bookmark);
        dest.writeString(history);
        dest.writeString(address);
    }

    public String getPg_type() {
        return pg_type;
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

    public void setPg_type(String pg_type) {
        this.pg_type = pg_type;
    }

    public String getPg_image_four() {
        return pg_image_four;
    }

    public void setPg_image_four(String pg_image_four) {
        this.pg_image_four = pg_image_four;
    }

    public String getPg_image_five() {
        return pg_image_five;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPg_image_five(String pg_image_five) {
        this.pg_image_five = pg_image_five;
    }

    @Override
    public String toString() {
        return "NinoRooms{" +
                "uid=" + uid +
                ", pg_name='" + pg_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", location='" + location + '\'' +
                ", details='" + details + '\'' +
                ", pg_type='" + pg_type + '\'' +
                ", ac_prices='" + ac_prices + '\'' +
                ", non_ac_prices='" + non_ac_prices + '\'' +
                ", pg_image='" + pg_image + '\'' +
                ", pg_image_two='" + pg_image_two + '\'' +
                ", pg_image_three='" + pg_image_three + '\'' +
                ", pg_image_four='" + pg_image_four + '\'' +
                ", pg_image_five='" + pg_image_five + '\'' +
                ", id='" + id + '\'' +
                ", bookmark='" + bookmark + '\'' +
                ", history='" + history + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}


