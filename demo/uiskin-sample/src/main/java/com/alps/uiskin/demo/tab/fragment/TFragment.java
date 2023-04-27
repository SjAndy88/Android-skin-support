package com.alps.uiskin.demo.tab.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import uiskin.demo.R;



public class TFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t, container, false);
        RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new CustomDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new TAdapter());
        return view;
    }

    private class TAdapter extends RecyclerView.Adapter<TAdapter.TViewHolder> {
        @NonNull
        @Override
        public TViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TViewHolder(
                    LayoutInflater.from(getActivity())
                            .inflate(R.layout.item_t, parent, false));
        }

        @Override
        public void onBindViewHolder(TViewHolder holder, int position) {
            ((TextView) holder.itemView).setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return 6;
        }

        class TViewHolder extends RecyclerView.ViewHolder {

            public TViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
