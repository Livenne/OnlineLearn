package com.livenne.onlinelearn.feature.explore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.data.model.Consultation;
import com.livenne.onlinelearn.core.network.NetworkUtils;
import com.livenne.onlinelearn.feature.explore.ConsultationDetailActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context context;

    private List<Consultation> consultationList;
    public ListViewAdapter(Context context,List<Consultation> consultationList) {
        this.context = context;
        this.consultationList = consultationList;
    }

    @Override
    public int getCount() {
        return consultationList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private void getData() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context)
                .inflate(R.layout.consultation_item, parent, false);

        ImageView imageView = convertView.findViewById(R.id.imageView4);
        TextView name = convertView.findViewById(R.id.textView8);
        TextView desc = convertView.findViewById(R.id.textView7);

        Consultation consultation = consultationList.get(position);

        Glide.with(context)
                .load(NetworkUtils.baseUrl + "/image?imageName=" + consultation.getCoverUrl())
                .into(imageView);
        name.setText(consultation.getName());
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        desc.setText(consultation.getTeacher() + " " + formatDate.format(new Date(consultation.getCreateTime())));



        convertView.setOnClickListener(v->{
            Intent intent = new Intent(context, ConsultationDetailActivity.class);
            intent.putExtra("consultationId",consultation.getConsultationId());
            context.startActivity(intent);
        });
        return convertView;
    }
}
