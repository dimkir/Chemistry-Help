package com.pk.chemhelp.gaslaws;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pk.chemhelp.R;

public class GasLawsAdapter extends BaseAdapter implements OnClickListener
{
	private Context context;

	private List<GasLawsItem> listItem;

	public GasLawsAdapter(Context context, List<GasLawsItem> listItem)
	{
		this.context = context;
		this.listItem = listItem;
	}

	public int getCount()
	{
		return listItem.size();
	}

	public Object getItem(int position)
	{
		return listItem.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup viewGroup)
	{
		GasLawsItem entry = listItem.get(position);
		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.gaslaws_row, null);
		}

		ImageView iconImage = (ImageView) convertView.findViewById(R.id.imageView1);
		String icon = entry.getIcon();
		if (icon.equals(GasLaws.SID_BOYLES))
			iconImage.setImageResource(R.drawable.boyles_icon);
		else if (icon.equals(GasLaws.SID_CHARLES))
			iconImage.setImageResource(R.drawable.charles_icon);
		else if (icon.equals(GasLaws.SID_IDEAL))
			iconImage.setImageResource(R.drawable.ideal_icon);
		else if (icon.equals(GasLaws.SID_GAY))
			iconImage.setImageResource(R.drawable.lussac_icon);
		else if (icon.equals(GasLaws.SID_COMBINED))
			iconImage.setImageResource(R.drawable.combined_icon);

		TextView Item = (TextView) convertView.findViewById(R.id.Item);
		Item.setText(entry.getItemName());

		TextView Description = (TextView) convertView.findViewById(R.id.Description);
		if(entry.getDescription().equals(GasLaws.FORMULAS_BOYL_S_P1V1_P2V2))
			Description.setText(Html.fromHtml("P<sub><small>1</small></sub>V<sub><small>1</small></sub> = P<sub><small>2</small></sub>V<sub><small>2</small></sub>"));
		else if(entry.getDescription().equals(GasLaws.FORMULAS_CHARLES_V1_T1_V2_T2))
			Description.setText(Html.fromHtml("V<sub><small>1</small></sub>/T<sub><small>1</small></sub> = V<sub><small>2</small></sub>/T<sub><small>2</small></sub>"));
		else if(entry.getDescription().equals(GasLaws.FORMULAS_GAY_LUSSAC_P1T2_P2T1))
			Description.setText(Html.fromHtml("P<sub><small>1</small></sub>T<sub><small>2</small></sub> = P<sub><small>2</small></sub>T<sub><small>1</small></sub>"));
		else if(entry.getDescription().equals(GasLaws.FORMULAS_IDEAL_GAS_PV_N_RT))
			Description.setText(Html.fromHtml(GasLaws.FORMULAS_IDEAL_GAS_PV_N_RT));
		else if(entry.getDescription().equals(GasLaws.FORMUILAS_COMBINED_P1V1_T1_P2V2_T2))
			Description.setText(Html.fromHtml("P<sub><small>1</small></sub>V<sub><small>1</small></sub>/T<sub><small>1</small></sub> = P<sub><small>2</small></sub>V<sub><small>2</small></sub>/T<sub><small>2</small></sub>"));
		else
			Description.setText(entry.getDescription());

		return convertView;
	}

	@Override
	public void onClick(View view)
	{
		GasLawsItem entry = (GasLawsItem) view.getTag();
		listItem.remove(entry);
		notifyDataSetChanged();

	}
}