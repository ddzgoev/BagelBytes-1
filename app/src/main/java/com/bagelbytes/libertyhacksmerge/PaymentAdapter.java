package com.bagelbytes.libertyhacksmerge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by dulong_jere on 8/17/17.
 */

public class PaymentAdapter extends BaseAdapter {

    private Context mContext;
    private List<Payment> listPayment;

    public PaymentAdapter(Context context, List<Payment> list) {
        mContext = context;
        listPayment = list;
    }

    @Override
    public int getCount() {
        return listPayment.size();
    }

    @Override
    public Object getItem(int pos) {
        return listPayment.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        // get selected entry
        Payment entry = listPayment.get(pos);

        // inflating list view layout if null
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.listitem, null);
        }

        // set name
        TextView paymentName = (TextView)convertView.findViewById(R.id.name);
        paymentName.setText(entry.getName());

        // set service
        TextView paymentService = (TextView)convertView.findViewById(R.id.service);
        paymentService.setText(entry.getService());

        // set date
        TextView paymentDate = (TextView) convertView.findViewById(R.id.date);
        paymentDate.setText(entry.getDate());

        // set pay
        TextView paymentPay = (TextView) convertView.findViewById(R.id.pay);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String payFormat = formatter.format(entry.getPay());

        paymentPay.setText(payFormat);

        return convertView;
    }


}
