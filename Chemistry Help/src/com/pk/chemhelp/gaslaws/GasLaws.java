package com.pk.chemhelp.gaslaws;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.pk.chemhelp.Bookmark;
import com.pk.chemhelp.ChemistryHelp;
import com.pk.chemhelp.CombinedGasLaw;
import com.pk.chemhelp.Debug;
import com.pk.chemhelp.Dialogs;
import com.pk.chemhelp.MySingleton;
import com.pk.chemhelp.R;
import com.pk.chemhelp.Settings;
import com.pk.chemhelp.gaslaws.laws.BoylesLaw;
import com.pk.chemhelp.gaslaws.laws.CharlesLaw;
import com.pk.chemhelp.gaslaws.laws.GayLussacsLaw;
import com.pk.chemhelp.gaslaws.laws.IdealGasLaw;

public class GasLaws extends SherlockActivity
implements GasConstants
{

	
	
	private static final String C_BUNDLE_KEY__PAGE_VALUES = "Page Values";
	private boolean Exit;
	final String PageID = "Gas Laws";
	//Bookmark BookmarkMethod;
	Bookmark[] Bookmarks;
	int numBookmarks;
	
	/* Bookmarks */
	// XXX: what's that?
	String[] pageValues;
	
	/* Backport Overflow Menu Workaround */
	Menu mainMenu;
	SubMenu subMenu;
	
	/* Debugging */
	MenuItem warningIcon;
	MenuItem debugMenu;
	boolean DEBUG_MODE = MySingleton.getInstance().getDebugMode();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_out);
		setContentView(R.layout.gaslaws);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Bundle extraBundle;
		Intent callerIntent = getIntent();
		
		//TODO: this looks dangerous: declaring array with numeric literal.
		pageValues = new String[50];

		numBookmarks = MySingleton.getInstance().getNumBookmarks();
		Bookmarks = MySingleton.getInstance().getBookmarks();
		
		ListView list = (ListView) findViewById(R.id.ListView);
		list.setClickable(true);
		
		// Menu Items
		final List<GasLawsItem> listOfItems = new ArrayList<GasLawsItem>();
//		listOfItems.add(new GasLawsItem(LAWS_BOYLE_S_LAW, FORMULAS_BOYL_S_P1V1_P2V2, SID_BOYLES));
		listOfItems.add(new GasLawsItem(BoylesLaw.class));
		listOfItems.add(new GasLawsItem(LAWS_CHARLE_S_LAW, FORMULAS_CHARLES_V1_T1_V2_T2, SID_CHARLES));
		listOfItems.add(new GasLawsItem(LAWS_GAY_LUSSAC_S_LAW, FORMULAS_GAY_LUSSAC_P1T2_P2T1, SID_GAY));
		listOfItems.add(new GasLawsItem(LAWS_IDEAL_GAS_LAW, FORMULAS_IDEAL_GAS_PV_N_RT, SID_IDEAL));
		listOfItems.add(new GasLawsItem(LAWS_COMBINED_GAS_LAW, FORMUILAS_COMBINED_P1V1_T1_P2V2_T2, SID_COMBINED));
		
		GasLawsAdapter adapter = new GasLawsAdapter(this, listOfItems);
		
		// Red Divider
		int[] colors = { 0, 0xFF00FFFF, 0 };
		list.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
		list.setDividerHeight(1);
		
		list.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long index)
			{
				GasLawsItem item = listOfItems.get(position);
//				String ID = listOfItems.get(position).getIcon();
				Intent selectedLaw = new Intent(GasLaws.this, item.getActivityClass() );
				
//				if (ID.equals(SID_BOYLES))
//					selectedLaw = new Intent(GasLaws.this, BoylesLaw.class);
//				else if (ID.equals(SID_CHARLES))
//					selectedLaw = new Intent(GasLaws.this, CharlesLaw.class);
//				else if (ID.equals(SID_IDEAL))
//					selectedLaw = new Intent(GasLaws.this, IdealGasLaw.class);
//				else if (ID.equals(SID_GAY))
//					selectedLaw = new Intent(GasLaws.this, GayLussacsLaw.class);
//				else if (ID.equals(SID_COMBINED))
//					selectedLaw = new Intent(GasLaws.this, CombinedGasLaw.class);
//				else
//					selectedLaw = new Intent(GasLaws.this, ChemistryHelp.class);
				
				startActivity(selectedLaw);
				//showToast(listOfItems.get(position).getItemName());
			}
		});
		
		list.setAdapter(adapter);
		
		if(callerIntent.hasExtra(C_BUNDLE_KEY__PAGE_VALUES))
		{
			extraBundle = getIntent().getExtras();
			pageValues = extraBundle.getStringArray(C_BUNDLE_KEY__PAGE_VALUES);

			setPageValues();
		}
	}
	
	public void onRestart()
	{
		super.onRestart();
		
		Exit = MySingleton.getInstance().getExit();
		if (Exit)
			Exit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.action_menu, menu);
		
		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB)
		{
			mainMenu = menu;
			subMenu = menu.addSubMenu("");
			subMenu.add(Menu.NONE, R.id.AddBookmark_Label, Menu.NONE, "Add Bookmark");
			subMenu.add(Menu.NONE, R.id.Settings_Label, Menu.NONE, "Settings");
			if(DEBUG_MODE)
				subMenu.add(Menu.NONE, R.id.Debug_Label, Menu.NONE, "Debug");
			subMenu.add(Menu.NONE, R.id.Exit_Label, Menu.NONE, "Exit");
			
			MenuItem subMenuItem = subMenu.getItem();
			subMenuItem.setIcon(R.drawable.abs__ic_menu_moreoverflow_normal_holo_dark);
			subMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		}
		
		this.warningIcon = menu.findItem(R.id.Warning_Label);
		this.debugMenu = menu.findItem(R.id.Debug_Label);
		if (!MySingleton.getInstance().getErrors()[0].equals("pcx_value"))
			warningIcon.setVisible(true);
		else
			warningIcon.setVisible(false);
		if (DEBUG_MODE)
			debugMenu.setVisible(true);
		else
			debugMenu.setVisible(false);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				Intent intent = new Intent(GasLaws.this, ChemistryHelp.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			case R.id.Warning_Label:
				showError();
				return true;
			case R.id.AddBookmark_Label:
				MySingleton.getInstance().setPageValues(getPageValues());
				MySingleton.getInstance().setPreviousPageID(PageID);
				Dialogs.getDialog("Add Bookmark", GasLaws.this).show();
				return true;
			case R.id.Bookmarks_Label:
				Dialogs.getDialog("Bookmarks", GasLaws.this).show();
				return true;
			case R.id.Settings_Label:
				MySingleton.getInstance().setPreviousPageID(PageID);
				Intent settingsIntent = new Intent(this, Settings.class);
				settingsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(settingsIntent);
				return true;
				/*
				 * case R.id.Help_Label: helpDialog.show(); return true; case
				 * R.id.Credits_Label: creditsDialog.show(); return true;
				 */
			case R.id.Exit_Label:
				Exit();
				return true;
			case R.id.Debug_Label:
				Intent i = new Intent(GasLaws.this, Debug.class);
				startActivity(i);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB && keyCode == KeyEvent.KEYCODE_MENU)
		{
			mainMenu.performIdentifierAction(subMenu.getItem().getItemId(), 0);
            return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	public String[] getPageValues()
	{
		String[] values = new String[50];
		for(int l = 0; l < 50; l++)
			values[l] = "pcx_value";

		values[0] = PageID;

		return values;
	}

	/**
	 * This method doesn't do anything.
	 * @deprecated this method doesn't do anything, it's empty.
	 */
	public void setPageValues()
	{
		if(pageValues[0].equals(PageID))
		{
			// Nothing to do here...
		}
	}
	
	public void showError()
	{
		Toast.makeText(GasLaws.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_SHORT).show();
	}
	
	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
}
