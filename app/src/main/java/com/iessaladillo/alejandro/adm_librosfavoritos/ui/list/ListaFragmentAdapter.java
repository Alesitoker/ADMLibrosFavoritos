package com.iessaladillo.alejandro.adm_librosfavoritos.ui.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.model.Libro;
import com.iessaladillo.alejandro.adm_librosfavoritos.databinding.FragmentListaItemBinding;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ListaFragmentAdapter extends ListAdapter<Libro, ListaFragmentAdapter.ViewHolder> {

    protected ListaFragmentAdapter() {
        super(new DiffUtil.ItemCallback<Libro>() {
            @Override
            public boolean areItemsTheSame(@NonNull Libro oldItem, @NonNull Libro newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Libro oldItem, @NonNull Libro newItem) {
                return TextUtils.equals(oldItem.getPortada(), newItem.getPortada()) &&
                        TextUtils.equals(oldItem.getTitle(), newItem.getTitle()) &&
                        TextUtils.equals(oldItem.getAutor(), newItem.getAutor()) &&
                        TextUtils.equals(oldItem.getYear(), newItem.getYear());
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentListaItemBinding b = FragmentListaItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItem(position).getId();
    }

    @Override
    protected Libro getItem(int position) {
        return super.getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        FragmentListaItemBinding b;

        public ViewHolder(@NonNull FragmentListaItemBinding binding) {
            super(binding.getRoot());
            b = binding;
        }

        void bind(Libro libro) {
            b.lblAutor.setText(libro.getAutor());
            b.lblFecha.setText(libro.getYear());
            b.lblTitle.setText(libro.getTitle());
            Picasso.with(b.getRoot().getContext()).load(libro.getPortada()).into(b.imgPortada);
        }
    }
}
