package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    public static final String ITEM_KEY = "item_key";
    private List<Item> mDataItems;
    private LayoutInflater mInflater;
    private Context mContext;

    public ItemAdapter(Context context, List<Item> objects) {
        super(context, R.layout.item_list, objects);

        mContext = context;
        mDataItems = objects;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        final ItemViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list, parent, false);
            holder = new ItemViewHolder();
            holder.itemName = convertView.findViewById(R.id.itemNameText);
            holder.itemImage = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);

            final Item item = mDataItems.get(position);
            holder.itemName.setText(item.getItemName());
            InputStream inputStream = null;

            try {
                String imageFile = item.getImage();
                inputStream = getContext().getAssets().open(imageFile);
                Drawable d = Drawable.createFromStream(inputStream, null);
                holder.itemImage.setImageDrawable(d);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            View.OnClickListener onClickListener = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DisplayItemDetail.class);
                    intent.putExtra(ITEM_KEY, item);
                    mContext.startActivity(intent);
                }
            };

            convertView.setOnClickListener(onClickListener);
        }
        else {
            holder = (ItemViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public static class ItemViewHolder {
        public TextView itemName;
        public ImageView itemImage;
    }
}
