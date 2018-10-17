package ru.dmitriyt.testproject.presentation.documentList;

import io.reactivex.disposables.Disposable;
import ru.dmitriyt.testproject.data.repository.DataRepository;
import ru.dmitriyt.testproject.data.repository.RemoteDataRepository;

public class DocumentListPresenter implements IDocumentListPresenter {
    private IDocumentListView view;
    private DataRepository dataRepository;
    private Disposable documentsDisposable;
    private Disposable documentDisposable;

    @Override
    public void loadDocuments() {
        view.showProgress();
        documentsDisposable = dataRepository.getDocuments().subscribe(
                documents -> {
                    view.showDocuments(documents);
                },
                throwable -> {
                    view.showError(throwable.getMessage());
                }
        );
    }

    @Override
    public void loadDocument(String documentId) {
        view.setLoading(true);
        if (documentDisposable != null) {
            documentDisposable.dispose();
        }
        documentDisposable = dataRepository.getDocument(documentId).subscribe(
                document -> {
                    view.setLoading(false);
                    view.openDocument(document);
                },
                throwable -> {
                    view.setLoading(false);
                    view.showError(throwable.getMessage());
                }
        );
    }

    @Override
    public void onCreateView(IDocumentListView view) {
        this.view = view;
        dataRepository = RemoteDataRepository.getInstance();
    }

    @Override
    public void onStart() {
        loadDocuments();
    }

    @Override
    public void onStop() {
        if (documentsDisposable != null) documentsDisposable.dispose();
        if (documentDisposable != null) documentDisposable.dispose();
    }
}
