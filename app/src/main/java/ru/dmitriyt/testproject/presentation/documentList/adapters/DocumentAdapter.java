package ru.dmitriyt.testproject.presentation.documentList.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import ru.dmitriyt.testproject.databinding.ItemDocumentBinding;
import ru.dmitriyt.testproject.R;
import ru.dmitriyt.testproject.model.Document;
import ru.dmitriyt.testproject.presentation.documentList.IDocumentListPresenter;
import ru.dmitriyt.testproject.presentation.documentList.IDocumentListView;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    private List<Document> documents;
    private IDocumentListView view;
    private IDocumentListPresenter presenter;

    public DocumentAdapter(List<Document> documents, IDocumentListView view, IDocumentListPresenter presenter) {
        this.documents = documents;
        this.view = view;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDocumentBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_document,
                viewGroup,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.binding.fileName.setText(documents.get(i).getFullName());
        holder.binding.btnInfo.setOnClickListener(view1 -> {
            view.showInfo(documents.get(i));
        });
        holder.binding.getRoot().setOnClickListener(view1 -> {
            presenter.loadDocument(documents.get(i).getId());
        });
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemDocumentBinding binding;

        public ViewHolder(ItemDocumentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
