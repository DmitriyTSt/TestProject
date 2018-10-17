package ru.dmitriyt.testproject.presentation.documentList;

import java.util.List;

import ru.dmitriyt.testproject.model.Document;

public interface IDocumentListView {
    void setLoading(boolean isLoading);
    void showProgress();
    void showDocuments(List<Document> documents);
    void showInfo(Document document);
    void openDocument(Document document);
    void showError(String message);
}
