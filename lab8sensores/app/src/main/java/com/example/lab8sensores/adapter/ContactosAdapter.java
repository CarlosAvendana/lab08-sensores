package com.example.lab8sensores.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8sensores.R;
import com.example.lab8sensores.data.Contacto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.MyViewHolder> implements Filterable {
    private List<Contacto> contactoList;
    private List<Contacto> contactoListFiltered;
    private ContactoAdapterListener listener;
    private Contacto deletedItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title1, title2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

        public MyViewHolder(View view) {
            super(view);
            title1 = view.findViewById(R.id.titleFirstLbl);
            title2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactoListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public ContactosAdapter(List<Contacto> contactoList, ContactoAdapterListener listener) {
        this.contactoList = contactoList;
        this.listener = listener;
        //init filter
        this.contactoListFiltered = contactoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // basically a render
        final Contacto contacto = contactoListFiltered.get(position);
        holder.title1.setText(contacto.getNombre());
        holder.title2.setText(contacto.getNumber());
        holder.description.setText(contacto.getRol());
    }

    @Override
    public int getItemCount() {
        return contactoListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = contactoListFiltered.remove(position);
        Iterator<Contacto> iter = contactoList.iterator();
        while (iter.hasNext()) {
            Contacto aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (contactoListFiltered.size() == contactoList.size()) {
            contactoListFiltered.add(position, deletedItem);
        } else {
            contactoListFiltered.add(position, deletedItem);
            contactoList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Contacto getSwipedItem(int index) {
        if (this.contactoList.size() == this.contactoListFiltered.size()) { //not filtered yet
            return contactoList.get(index);
        } else {
            return contactoListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (contactoList.size() == contactoListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(contactoList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(contactoList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(contactoListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(contactoListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactoListFiltered = contactoList;
                } else {
                    List<Contacto> filteredList = new ArrayList<>();
                    for (Contacto row : contactoList) {
                        // filter use two parameters
                        if (row.getNombre().toLowerCase().contains(charString.toLowerCase()) || row.getNumber().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    contactoListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactoListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactoListFiltered = (ArrayList<Contacto>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactoAdapterListener {
        void onContactSelected(Contacto carrera);
    }
}