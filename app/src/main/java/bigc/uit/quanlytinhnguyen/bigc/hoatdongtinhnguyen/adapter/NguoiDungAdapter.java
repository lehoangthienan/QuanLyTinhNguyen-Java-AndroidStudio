package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter;

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

import bigc.uit.quanlytinhnguyen.R;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.NguoiDung;

/**
 * Created by Thien An on 2017-12-14.
 */

public class NguoiDungAdapter extends BaseAdapter {

    //Kế thừa BaseAdapter để xử lý dữ liệu
    //Khởi Tạo Tham truyền vào Adapter

    private Activity context;
    private  int resource;
    //private List<NguoiDung> objects;
    private NguoiDung objects;

    public NguoiDungAdapter( Activity context,  int resource,  NguoiDung objects) {
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    // Khai báo những item có trong activity
    private  class ViewHolder{
        TextView   txtHoTenCTND , txtNamSinhCTND ,txtMaSoSinhVienCTND , txtTenTruongCTND , txtMaTruongCTND  ;
        ImageButton btnThayDoiThongTinCaNhan, btnThayDoiMatKhau;

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

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        ViewHolder holder;
        if (view == null) {

            holder = new ViewHolder();

            //khởi tạo LayoutInflater
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);

            //ánh xạ
            holder.txtHoTenCTND = (TextView) view.findViewById(R.id.txtHoTenCTND);
            holder.txtMaSoSinhVienCTND = (TextView) view.findViewById(R.id.txtMaSoSinhVienCTND);
            holder.txtNamSinhCTND = (TextView) view.findViewById(R.id.txtNamSinhCTND);
            holder.txtTenTruongCTND = (TextView) view.findViewById(R.id.txtTenTruongDaiHocCTND);
            holder.txtMaTruongCTND =(TextView) view.findViewById(R.id.txtMaTruongCTND);
            holder.btnThayDoiThongTinCaNhan = (ImageButton)  view.findViewById(R.id.btnThayDoiThongTinCaNhan);
            holder.btnThayDoiMatKhau = (ImageButton) view.findViewById(R.id.btnThayDoiMatKhau);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        final  NguoiDung nguoiDung = this.objects;

        //gán
        holder.txtHoTenCTND.setText("Tên : "+nguoiDung.getTenSV().toString());
        holder.txtMaSoSinhVienCTND.setText("Mã Số Sinh Viên : " +nguoiDung.getMASV().toString());
        holder.txtMaTruongCTND.setText("Tên Trường : " +nguoiDung.getMAT().toString());
        holder.txtNamSinhCTND.setText("Ngày Sinh : " + nguoiDung.getNgaySinh().toString());



        return view;

    }
}
