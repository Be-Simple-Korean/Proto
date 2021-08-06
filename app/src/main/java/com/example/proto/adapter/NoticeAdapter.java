package com.example.proto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proto.R;
import com.example.proto.databinding.ItemNoticeBinding;
import com.example.proto.vo.NoticeVO;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.CustomViewHolder> {
    private ArrayList<NoticeVO> noticeList;
    private ItemNoticeBinding mBinding;

    public NoticeAdapter(ArrayList<NoticeVO> noticeList) {
        this.noticeList = noticeList;

    }

    @NonNull
    @NotNull
    @Override
    public NoticeAdapter.CustomViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        mBinding=ItemNoticeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoticeAdapter.CustomViewHolder holder, int position) {
        holder.bind(noticeList.get(position));
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }

        public void bind(NoticeVO noticeVO){
                mBinding.tvNoticeTitle.setText(noticeVO.getTitle());
                mBinding.tvNoticeDate.setText(noticeVO.getDate());
        }
    }
}
