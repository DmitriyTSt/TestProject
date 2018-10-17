package ru.dmitriyt.testproject.data.source.remote;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.dmitriyt.testproject.model.Document;

public interface APIInterface {
    @GET("documents/all")
    Flowable<List<Document>> getDocuments();

    @GET("documents/get/{id}")
    Flowable<Document> getDocument(@Path("id") String id);
}
