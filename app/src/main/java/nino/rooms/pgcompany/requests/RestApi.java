package nino.rooms.pgcompany.requests;

import java.util.List;

import nino.rooms.pgcompany.model.NinoRooms;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    // SEARCH
    @GET("api/")
    Call<List<NinoRooms>> getNinoRooms();

}
