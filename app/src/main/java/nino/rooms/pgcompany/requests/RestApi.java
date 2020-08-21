package nino.rooms.pgcompany.requests;

import java.util.List;

import nino.rooms.pgcompany.model.NinoRooms;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    // SEARCH
    @GET("api/noida")
    Call<List<NinoRooms>> getNinoRooms();

    @GET("api/noida")
    Call<List<NinoRooms>> SearchNinoRooms(
            @Query("search") String query
    );

    @GET("api/noida/cheap")
    Call<List<NinoRooms>> CheapNinoRooms();

    @GET("api/noida/top")
    Call<List<NinoRooms>> TopNinoRooms();

}
