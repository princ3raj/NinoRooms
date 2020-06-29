//package nino.rooms.pgcompany.requests;
//
//
//import android.util.Log;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import java.io.IOException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//
//import nino.rooms.pgcompany.AppExecutors;
//import nino.rooms.pgcompany.model.NinoRooms;
//import retrofit2.Call;
//import retrofit2.Response;
//
//import static nino.rooms.pgcompany.utils.Constants.NETWORK_TIMEOUT;
//
//public class NinoRoomsApiClient {
//
//    private static final String TAG = "NinoRoomsApiClient";
//
//    private static NinoRoomsApiClient instance;
//    private MutableLiveData<List<NinoRooms>> mRooms;
//    private RetrieveNinoRoomsRunnable mRetrieveNinoRoomsRunnable;
//
//    public static NinoRoomsApiClient getInstance(){
//
//        if (instance==null)
//        {
//            instance=new NinoRoomsApiClient();
//        }
//
//        return instance;
//    }
//
//    private NinoRoomsApiClient() {
//        mRooms= new MutableLiveData<>();
//    }
//
//
//    public LiveData<List<NinoRooms>> getRooms(){
//        return mRooms;
//    }
//
//    public void searchNinoRoomsApi(){
//
//        if (mRetrieveNinoRoomsRunnable!=null)
//        {
//            mRetrieveNinoRoomsRunnable=null;
//        }
//        mRetrieveNinoRoomsRunnable=new RetrieveNinoRoomsRunnable();
//
//        final Future handler= AppExecutors.getInstance().networkIO().submit(mRetrieveNinoRoomsRunnable);
//
//        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
//            @Override
//            public void run() {
//
//                //let the user know it's timed out
//               handler.cancel(true);
//            }
//        },NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
//
//    }
//
//    private  class RetrieveNinoRoomsRunnable implements Runnable
//    {
//
//        boolean cancelRequest;
//
//        @Override
//        public void run() {
//
//            try {
//                Response response=getNinoRooms().execute();
//                if(cancelRequest){
//                    return;
//                }
//
//                if (response.code()==200){
//
//                    List<NinoRooms> list= new ArrayList<>();
//                    list.add((NinoRooms) response.body());
//                    Log.d(TAG, "run: "+list);
//                    mRooms.postValue(list);
//
//
//
//
//
//                }
//
//                else
//                {
//                    assert response.errorBody() != null;
//                    String error=response.errorBody().string();
//                    Log.d(TAG, "run: "+error);
//                    mRooms.postValue(null);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                mRooms.postValue(null);
//            }
//
//
//
//
//        }
//
//        private Call<List<NinoRooms>> getNinoRooms(){
//
//            RestApi restApi=RetrofitClientInstance.getRetrofitInstance().create(RestApi.class);
//            return restApi.getNinoRooms();
//
//        }
//
//        private void cancelRequest()
//        {
//
//            Log.d(TAG, "cancelRequest: canceling the search request");
//            cancelRequest=true;
//        }
//
//    }
//}
