package ru.dmitriyt.testproject.data.source;

import java.util.List;

import io.reactivex.Flowable;
import ru.dmitriyt.testproject.model.Document;

public interface DataSource {
    Flowable<List<Document>> getDocuments();
    Flowable<Document> getDocument(String id);
    Flowable<List<String>> getFavoriteDocuments();
}
