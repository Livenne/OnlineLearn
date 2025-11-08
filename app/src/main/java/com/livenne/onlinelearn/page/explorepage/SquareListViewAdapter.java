package com.livenne.onlinelearn.page.explorepage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.R;
import com.livenne.onlinelearn.common.model.Question;
import com.livenne.onlinelearn.common.model.vo.UserVo;
import com.livenne.onlinelearn.common.utils.NetworkUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SquareListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Question> questionList;

    public SquareListViewAdapter(Context context,List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Long questionId = (long) (position + 1);
        Question question = questionList.get(position);

        convertView = LayoutInflater.from(context)
                .inflate(R.layout.question_item, parent, false);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView username = convertView.findViewById(R.id.username);
        TextView time = convertView.findViewById(R.id.time);
        TextView questionT = convertView.findViewById(R.id.question);
        TextView likes = convertView.findViewById(R.id.likes);
        TextView comments = convertView.findViewById(R.id.comments);

        NetworkUtils.loadResource("/user/" + question.getUserId(), new TypeReference<UserVo>() {
        }, result -> {
            Glide.with(context)
                    .load(NetworkUtils.baseUrl + "/image/" + result.getAvatarUrl())
                    .into(imageView);
            username.setText(result.getUsername());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            time.setText(format.format(new Date(question.getCreateTime())));
            questionT.setText(question.getQuestion());
            likes.setText(question.getLikes().toString());
            comments.setText(question.getComments().toString());
        });



        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuestionDetailActivity.class);
            intent.putExtra("questionId", questionId);
            context.startActivity(intent);
        });
        return convertView;
    }
}
