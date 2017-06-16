package com.whitekapok.passwordnote.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.whitekapok.passwordnote.R;
import com.whitekapok.passwordnote.entity.NoteEntity;

import java.util.List;

/**
 *
 * Created by Lance on 2017/6/16.
 */

public class MainItemAdapter extends BaseQuickAdapter<NoteEntity,BaseViewHolder>{

    public MainItemAdapter(@Nullable List<NoteEntity> data) {
        super( R.layout.item_main_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoteEntity item) {
        helper.setText(R.id.tv_item_title,item.getItemName());
        helper.setText(R.id.tv_item_content,item.getItemNote());
    }

//    class MainHolder extends BaseViewHolder{
//
//        public MainHolder(View view) {
//            super(view);
//        }
//    }
}
