package ru.dmitriyt.testproject.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.dmitriyt.testproject.data.source.remote.RemoteDataSource;
import ru.dmitriyt.testproject.model.Document;

public class RemoteDataRepository implements DataRepository {
    private RemoteDataSource remoteDataSource;

    private static RemoteDataRepository instance;

    public static RemoteDataRepository getInstance() {
        return instance != null ? instance : new RemoteDataRepository();
    }

    private RemoteDataRepository() {
        remoteDataSource = RemoteDataSource.getInstance();
    }

    @Override
    public Flowable<List<Document>> getDocuments() {
        return remoteDataSource.getDocuments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<Document> getDocument(String id) {
        return remoteDataSource.getDocument(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
