package eu.lapecera.jolastoki.common;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.domain.Score;

public class ScoreAdapter extends BaseAdapter {
	
	private Context context;
	private List<Score> scores;

	
	public ScoreAdapter(Context context, List<Score> scores) {
		this.context = context;
		this.scores = scores;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		
		if (v == null) {
			v = LayoutInflater.from(context).inflate(R.layout.view_score, parent, false);
		}
		ListView.LayoutParams params = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,ListView.LayoutParams.WRAP_CONTENT);
		v.setLayoutParams(params);
		
		Score score = getItem(position);
		
		if (score != null) {
			TextView pos = (TextView) v.findViewById(R.id.score_position);
			TextView name = (TextView) v.findViewById(R.id.score_name);
			TextView amount = (TextView) v.findViewById(R.id.score_amount);
			
			pos.setText(Integer.toString(score.getPosition()));
			name.setText(score.getName());
			amount.setText(Integer.toString(score.getScore()));
		}
		
		return v;
	}

	@Override
	public int getCount() {
		return scores.size();
	}

	@Override
	public Score getItem(int position) {
		return scores.get(position);
	}

	@Override
	public long getItemId(int position) {
		return -1;
	}

}
