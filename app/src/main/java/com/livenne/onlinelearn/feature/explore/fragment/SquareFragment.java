package com.livenne.onlinelearn.feature.explore.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.data.model.Question;
import com.livenne.onlinelearn.core.network.NetworkUtils;
import com.livenne.onlinelearn.feature.explore.PublishQuestionActivity;
import com.livenne.onlinelearn.feature.explore.adapter.SquareListViewAdapter;

import java.util.List;

public class SquareFragment extends Fragment {

    private ListView listView;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_square, container, false);

        listView = inflate.findViewById(R.id.list_view2);
        button = inflate.findViewById(R.id.button);

        NetworkUtils.loadResource("/question/list", new TypeReference<List<Question>>() {
        }, result -> {

            SquareListViewAdapter squareListViewAdapter = new SquareListViewAdapter(inflate.getContext(),result);

            listView.setAdapter(squareListViewAdapter);
        });

        button.setOnClickListener(v->{
            Intent intent = new Intent(inflate.getContext(), PublishQuestionActivity.class);
            startActivity(intent);
        });




        return inflate;
    }
}