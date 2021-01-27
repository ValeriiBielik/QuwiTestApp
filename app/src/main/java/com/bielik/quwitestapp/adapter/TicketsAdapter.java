package com.bielik.quwitestapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bielik.quwitestapp.databinding.ItemTicketBinding;
import com.bielik.quwitestapp.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.TicketViewHolder> {

    private final List<Ticket> dataList = new ArrayList<>();

    public void updateDataList(List<Ticket> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTicketBinding binding = ItemTicketBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TicketViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class TicketViewHolder extends RecyclerView.ViewHolder {

        private final ItemTicketBinding mBinding;

        public TicketViewHolder(@NonNull ItemTicketBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Ticket ticket) {
            mBinding.setTicket(ticket);
            mBinding.executePendingBindings();
        }
    }

}
