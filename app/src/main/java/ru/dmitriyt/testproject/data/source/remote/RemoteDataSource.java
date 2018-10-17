package ru.dmitriyt.testproject.data.source.remote;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.dmitriyt.testproject.data.source.DataSource;
import ru.dmitriyt.testproject.model.Document;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource instance;
    private APIClient apiClient;

    public static RemoteDataSource getInstance() {
        return instance != null ? instance : new RemoteDataSource();
    }

    private RemoteDataSource() {
        apiClient = APIClient.getInstance();
    }

    @Override
    public Flowable<List<Document>> getDocuments() {
        return apiClient.getApiInterface().getDocuments();
    }

    @Override
    public Flowable<Document> getDocument(String id) {
        return apiClient.getApiInterface().getDocument(id);
    }

    @Override
    public Flowable<List<String>> getFavoriteDocuments() {
        return null;
    }
}
