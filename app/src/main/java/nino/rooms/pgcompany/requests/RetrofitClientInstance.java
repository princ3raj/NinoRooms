package nino.rooms.pgcompany.requests;

import nino.rooms.pgcompany.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit sRetrofit;


    public static Retrofit getRetrofitInstance()
    {
        if (sRetrofit==null)
        {
            sRetrofit=new  retrofit2.Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return sRetrofit;


    }

}
