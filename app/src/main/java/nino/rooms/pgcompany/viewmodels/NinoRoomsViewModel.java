//package nino.rooms.pgcompany.viewmodels;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import java.util.List;
//
//import nino.rooms.pgcompany.model.NinoRooms;
//import nino.rooms.pgcompany.repositories.NinoRoomRepository;
//
//public class NinoRoomsViewModel extends ViewModel {
//
//     private NinoRoomRepository mNinoRoomRepository;
//    private Boolean mIsPerformingQuery;
//
//    public NinoRoomsViewModel() {
//
//        mNinoRoomRepository=NinoRoomRepository.getInstance();
//    }
//
//    public LiveData<List<NinoRooms>> getRooms()
//    {
//
//        return mNinoRoomRepository.getRooms();
//    }
//
//    public void searchNinoRoomsApi()
//    {
//        mNinoRoomRepository.searchNinoRoomsApi();
//    }
//
//    public void setIsPerformingQuery(Boolean isPerformingQuery){
//        mIsPerformingQuery = isPerformingQuery;
//    }
//
//}
