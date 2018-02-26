package com.example.gocode.care.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.gocode.care.R;
import com.example.gocode.care.interfaces.RecyclerViewOnClickListnerHack;
import com.example.gocode.care.models.Usuario;

import java.util.ArrayList;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private ArrayList<Usuario> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListnerHack mRecyclerViewOnClickListnerHack;

    public UserAdapter(Context c, ArrayList<Usuario> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_user_card, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.ivUser.setImageResource(mList.get(position).getFoto());
        holder.tvNome.setText(mList.get(position).getNome());
        holder.tvCidade.setText(mList.get(position).getCidade());
        holder.tvEstado.setText(mList.get(position).getEstado());

        try {
            YoYo.with(Techniques.Landing)
                    .duration(700)
                    .playOn(holder.itemView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addListItem(Usuario u, int position) {
        mList.add(u);
        notifyItemInserted(position);
    }

    public void setRecyclerViewOnClickListnerHack(RecyclerViewOnClickListnerHack r) {
        mRecyclerViewOnClickListnerHack = r;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivUser;
        public TextView tvNome;
        public TextView tvCidade;
        public TextView tvEstado;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivUser = (ImageView) itemView.findViewById(R.id.ivUser);
            tvNome = (TextView) itemView.findViewById(R.id.tvNome);
            tvCidade = (TextView) itemView.findViewById(R.id.tvCidade);
            tvEstado = (TextView) itemView.findViewById(R.id.tvEstado);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListnerHack != null) {
                mRecyclerViewOnClickListnerHack.OnClickListner(v, getPosition());
            }
        }
    }
}
