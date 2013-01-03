package jp.gr.java_conf.kou512.rakuwari;

import java.util.ArrayList;

import jp.gr.java_conf.kou512.rakuwari.bean.PersonBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomPersonListAdapter extends ArrayAdapter<PersonBean> {
    Context context;
    LayoutInflater inflater;

    public CustomPersonListAdapter(Context context, ArrayList<PersonBean> objects) {
        super(context, 0, objects);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonBean person = (PersonBean)getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.person_list, null);
        }
        CheckBox chkAttend = (CheckBox)convertView.findViewById(R.id.row_chk_attend);
        CheckBox chkPaid = (CheckBox)convertView.findViewById(R.id.row_chk_Paid);
        TextView txtview = (TextView)convertView.findViewById(R.id.row_PersonText);
        TextView cost = (TextView)convertView.findViewById(R.id.row_CostText);
        
        //出席者フラグ
        boolean isAttend = person.getIsAttend() == 1 ? true : false;
        //支払済フラグ
        boolean isPaid = person.getIsPaid() == 1 ? true : false;
        //支払金額　文字変換
        String costStr = Float.toString(person.getCost());
        
        chkAttend.setChecked(isAttend);
        chkPaid.setChecked(isPaid);
        txtview.setText(person.getPersonName());
        cost.setText(costStr);
        
        return convertView;
    }
}

