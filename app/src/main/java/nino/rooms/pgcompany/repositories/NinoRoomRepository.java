//package nino.rooms.pgcompany.repositories;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import java.util.List;
//
//import nino.rooms.pgcompany.model.NinoRooms;
//import nino.rooms.pgcompany.requests.NinoRoomsApiClient;
//
//public class NinoRoomRepository {
//
//      private static NinoRoomRepository instance;
//
//      private NinoRoomsApiClient mNinoRoomsApiClient;
//
//
//
//    public  static NinoRoomRepository getInstance(){
//
//        if(instance==null)
//        {
//            instance= new NinoRoomRepository();
//        }
//
//        return instance;
//    }
//
//    private NinoRoomRepository()
//    {
//        mNinoRoomsApiClient=NinoRoomsApiClient.getInstance();
//
//    }
//
//    public LiveData<List<NinoRooms>> getRooms()
//    {
//        return mNinoRoomsApiClient.getRooms();
//    }
//
//
//    public void searchNinoRoomsApi() {
//
//        mNinoRoomsApiClient.searchNinoRoomsApi();
//    }
//}
