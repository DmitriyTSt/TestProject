package ru.dmitriyt.testproject.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import ru.dmitriyt.testproject.model.Document;

public interface DataRepository {
    Flowable<List<Document>> getDocuments();
    Flowable<Document> getDocument(String id);
}
