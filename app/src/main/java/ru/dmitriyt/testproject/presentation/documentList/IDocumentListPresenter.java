package ru.dmitriyt.testproject.presentation.documentList;

public interface IDocumentListPresenter {
    void loadDocuments();
    void loadDocument(String documentId);
    void onCreateView(IDocumentListView view);

    void onStart();
    void onStop();
}
