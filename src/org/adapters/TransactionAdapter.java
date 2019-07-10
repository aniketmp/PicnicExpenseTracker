package org.adapters;

import java.util.List;

import org.aniket.R;
import org.model.Person;
import org.model.Transaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TransactionAdapter extends BaseAdapter{
	
	List<Transaction> transactions=null;
	public TransactionAdapter(List<Transaction> transactions) {
		this.transactions=transactions;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return transactions.size();
	}

	@Override
	public Object getItem(int position) {
		
		return transactions.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return transactions.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.transaction_row, parent, false);
		}

		Transaction trans= transactions.get(position);

		TextView personTextView = (TextView) convertView.findViewById(R.id.transText);
		String transText=trans.getPerson().getName()+" spend "+trans.getAmtSpend()+"Rs ";
		if(trans.getComment()==null||trans.getComment().trim().equals(""))
		{
			transText+=":No purpose mensioned";
		}
		else
		{
			transText+=":"+trans.getComment();
		}
		personTextView.setText(transText);	
		return convertView;
	}

}
