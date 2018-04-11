package es.santirivera.data.repository;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import es.santirivera.data.dto.CategoryDTO;
import es.santirivera.data.exceptions.WSNetworkException;
import es.santirivera.data.net.NetworkManager;
import es.santirivera.data.net.WServices;
import es.santirivera.data.repository.responses.NetErrorResponse;
import es.santirivera.data.repository.responses.NetRepositoryResponse;
import es.santirivera.data.repository.responses.RepositoryResponse;
import retrofit2.Call;
import retrofit2.Response;

public class AppRepositoryImpl implements AppRepository {

    private Context context;
    private WServices wServices;
    private NetworkManager networkManager;

    public AppRepositoryImpl(Context context, WServices wServices, NetworkManager networkManager) {
        this.context = context;
        this.wServices = wServices;
        this.networkManager = networkManager;
    }

    @Override
    public String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    public RepositoryResponse<List<CategoryDTO>> getCategoryList() {
        Call<List<CategoryDTO>> call = wServices.getCategories();
        try {
            Response<List<CategoryDTO>> response = call.execute();
            if (response.isSuccessful()) {
                return new NetRepositoryResponse<List<CategoryDTO>>(response.body());
            } else {
                return new NetErrorResponse<>();
            }
        } catch (IOException e) {
            throw new WSNetworkException(e);
        }
    }


    private void checkConnectivity() {
        networkManager.checkConnectivity();
    }
}
