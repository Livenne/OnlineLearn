package com.livenne.onlinelearn.page.explorepage.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.common.model.Consultation;
import com.livenne.onlinelearn.common.utils.NetworkUtils;
import com.livenne.onlinelearn.page.explorepage.adapter.ListViewAdapter;

import java.util.List;

public class ConsultationFragment extends Fragment {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_consultation, container, false);

        listView = inflate.findViewById(R.id.list_view);

        NetworkUtils.loadResource("/consultation/list", new TypeReference<List<Consultation>>() {
        }, result -> {
            ListViewAdapter listViewAdapter = new ListViewAdapter(inflate.getContext(),result);
            listView.setAdapter(listViewAdapter);
        });



        return inflate;
    }
}