package kerryle.thienan.quanlytinhnguyen.kerryle.thienan.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import kerryle.thienan.quanlytinhnguyen.XuLyDeleteActivity;
import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model.DanhSachUpdate;

/**
 * Created by Thien An on 2017-12-21.
 */

public class DanhSachDeleteAdapter extends BaseAdapter {
    private Activity context;
    private  int resource;
    private List<DanhSachUpdate> objects;

    public DanhSachDeleteAdapter(Activity context, int resource, List<DanhSachUpdate> objects) {
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    private  class ViewHolder{
        TextView txtMaTinhNguyenDelete ,txtTenTinhNguyenDelete ;
        ImageButton btnXoaTNDelete ;

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

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);
            ;

            holder.txtMaTinhNguyenDelete = (TextView) view.findViewById(R.id.txtMaTNDelete);
            holder.txtTenTinhNguyenDelete = (TextView) view.findViewById(R.id.txtTenTNDelete);
            holder.btnXoaTNDelete = (ImageButton) view.findViewById(R.id.btnXoaTinhNguyenDelete);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final DanhSachUpdate ds = this.objects.get(position);

        holder.txtMaTinhNguyenDelete.setText("Mã: " + ds.getMATN().toString());
        holder.txtTenTinhNguyenDelete.setText("Tên: " + ds.getTenTN().toString());

        holder.btnXoaTNDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MATN = ds.getMATN().toString();
                xuLySua(MATN);
            }
        });

        return view;
    }
    private void xuLySua(String matn) {
        Intent i = new Intent(context, XuLyDeleteActivity.class);
        i.putExtra("MATN" , matn);
        context.startActivity(i);
    }
}
