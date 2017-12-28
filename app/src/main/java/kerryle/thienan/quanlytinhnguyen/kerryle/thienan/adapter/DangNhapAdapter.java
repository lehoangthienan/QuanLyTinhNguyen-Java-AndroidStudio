package kerryle.thienan.quanlytinhnguyen.kerryle.thienan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kerryle.thienan.quanlytinhnguyen.R;
import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model.DangNhap;

/**
 * Created by Thien An on 2017-12-13.
 */

public class DangNhapAdapter extends BaseAdapter {

    private Activity context;
    private  int resource;
    private List<DangNhap> objects;

    public DangNhapAdapter( Activity context,  int resource,  List<DangNhap> objects) {
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    private  class ViewHolder{
        TextView txtTenTk, txtMk , txtMASVDN;
    }
    @Override
    public int getCount() {
        return 0;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {

            holder = new ViewHolder();

            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);;

            holder.txtTenTk = (TextView) view.findViewById(R.id.txtTenTK);
            holder.txtMk = (TextView) view.findViewById(R.id.txtMK);
            holder.txtMASVDN = (TextView) view.findViewById(R.id.txtMASVDN);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        final DangNhap dangNhap = this.objects.get(position);

        holder.txtTenTk.setText(dangNhap.getTaiKhoan().toString());
        holder.txtMk.setText(dangNhap.getMatKhau().toString());
        holder.txtMASVDN.setText(dangNhap.getMASV().toString());




        return view;

    }
}
