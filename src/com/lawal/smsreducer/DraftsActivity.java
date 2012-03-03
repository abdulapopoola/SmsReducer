package com.lawal.smsreducer;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

import com.lawal.data.SQLDbAdapter;

public class DraftsActivity extends ListActivity {

	private static String tag = "draftscreation";
	private static Cursor c;
	private static SQLDbAdapter db;
	private static final int DELETE_ID = 1;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		db = new SQLDbAdapter(this);

		populateListView();
		// generateView();
		// implement listeners();
		// // return result();
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				boolean shorten_draft_body = false;
				c = db.getDrafts(shorten_draft_body);
				c.moveToPosition(position);
				Intent resultIntent = new Intent();

				resultIntent.putExtra(SQLDbAdapter.RECIPIENT_COL, c.getString(
						c.getColumnIndexOrThrow(SQLDbAdapter.RECIPIENT_COL)));
				resultIntent.putExtra(SQLDbAdapter.DRAFT_BODY_COL,c.getString(
								c.getColumnIndexOrThrow(SQLDbAdapter.DRAFT_BODY_COL)));
				setResult(Activity.RESULT_OK, resultIntent);
				finish();
			}
		});
		registerForContextMenu( getListView());

		// Log.d(tag, "create");
	}

	private void populateListView() {
		boolean shorten_draft_body = true;
		c = db.getDrafts(shorten_draft_body);

		String[] from = new String[] { SQLDbAdapter.RECIPIENT_COL,
				SQLDbAdapter.DRAFT_BODY_COL };
		int[] to = new int[] { R.id.recipient, R.id.body };
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
				R.layout.draft_item, c, from, to);
		setListAdapter(notes);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		super.onCreateOptionsMenu(menu);
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.drafts_menu, menu);
//		return true;
//	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
	{
		menu.add(Menu.NONE, DELETE_ID, Menu.NONE, "Delete Draft?");		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			db.deleteDraft(info.id);		
			populateListView();
			return true;
		}
		return super.onContextItemSelected(item);
	}
}