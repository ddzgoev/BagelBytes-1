package com.bagelbytes.libertyhacksmerge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.List;

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

        // get class variables
        Integer id = entry.getId();
        String name = entry.getName();
        String service = entry.getService();
        String date = entry.getDate();
        Double pay = entry.getPay();
        Integer auto = entry.getAuto();
        String accountNumber = entry.getAccountNumber();
        String accountHolder = entry.getAccountHolder();
        String zip = entry.getZip();
        Integer paymentMethod = entry.getPaymentMethod();

        // inflating list view layout if null
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.listitem, null);
        }

        // set name
        TextView paymentName = (TextView)convertView.findViewById(R.id.name);
        paymentName.setText(name);

        // set service
        TextView paymentService = (TextView)convertView.findViewById(R.id.service);
        paymentService.setText(service);

        // set date
        TextView paymentDate = (TextView) convertView.findViewById(R.id.date);
        paymentDate.setText(date);

        // set pay
        TextView paymentPay = (TextView) convertView.findViewById(R.id.pay);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String payFormat = formatter.format(pay);

        paymentPay.setText(payFormat);

        return convertView;
    }




}
