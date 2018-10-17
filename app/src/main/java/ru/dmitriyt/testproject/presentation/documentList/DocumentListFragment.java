package ru.dmitriyt.testproject.presentation.documentList;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.dmitriyt.testproject.R;
import ru.dmitriyt.testproject.databinding.DialogDocumentInfoBinding;
import ru.dmitriyt.testproject.databinding.FragmentDocumentListBinding;
import ru.dmitriyt.testproject.model.Document;
import ru.dmitriyt.testproject.presentation.documentList.adapters.DocumentAdapter;

public class DocumentListFragment extends Fragment implements IDocumentListView {

    private IDocumentListPresenter presenter;
    private FragmentDocumentListBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DocumentListPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_document_list, container, false);
        binding.documentList.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.onCreateView(this);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    private ProgressDialog progressDialog;

    @Override
    public void setLoading(boolean isLoading) {
        if (isLoading) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Загрузка");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    private void hideAll() {
        binding.documentList.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        hideAll();
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDocuments(List<Document> documents) {
        hideAll();
        binding.documentList.setVisibility(View.VISIBLE);
        binding.documentList.setAdapter(new DocumentAdapter(documents, this, presenter));
    }

    @Override
    public void showInfo(Document document) {
        AlertDialog.Builder infoDialog = new AlertDialog.Builder(getContext());
        DialogDocumentInfoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()),
                R.layout.dialog_document_info,
                null,
                false);
        infoDialog.setView(binding.getRoot());
        infoDialog.setCancelable(false);
        binding.name.setText(document.getFullName());
        infoDialog.setNegativeButton("Открыть с помощью", ((dialogInterface, i) -> {
            presenter.loadDocument(document.getId());
        }));
        infoDialog.setPositiveButton("OK", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
        infoDialog.create().show();
    }

    @Override
    public void openDocument(Document document) {
        Uri documentLink = Uri.parse(document.getLink());
        Intent openLink = new Intent(Intent.ACTION_VIEW, documentLink);
        startActivity(openLink);
    }

    @Override
    public void showError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message);
        builder.setTitle("Ошибка");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
        builder.create().show();
    }
}
