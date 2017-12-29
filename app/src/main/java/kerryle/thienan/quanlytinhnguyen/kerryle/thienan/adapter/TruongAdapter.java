package kerryle.thienan.quanlytinhnguyen.kerryle.thienan.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import kerryle.thienan.quanlytinhnguyen.R;
import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model.Truong;

/**
 * Created by Thien An on 2017-12-29.
 */

public class TruongAdapter extends BaseAdapter {
    private Activity context;
    private  int resource;
    private List<Truong> objects;

    public TruongAdapter(Activity context, int resource, List<Truong> objects) {
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    private  class ViewHolder{
        TextView txtMTDST ,txtTenDST  ;
        ImageButton btnDST;
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {

            holder = new ViewHolder();

            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);;

            holder.txtMTDST = (TextView) view.findViewById(R.id.txtMaTruongDST);
            holder.txtTenDST = (TextView) view.findViewById(R.id.txtTenTruongDST);
            holder.btnDST =(ImageButton) view.findViewById(R.id.btnXemDST);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        final Truong ds = this.objects.get(position);

        holder.txtMTDST.setText("Mã Trường : "+ds.getMAT().toString());
        holder.txtTenDST.setText("Tên Trường : " +ds.getTenTruog().toString());

        return view;

    }
}
