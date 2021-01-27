package ddwucom.moblie.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Music> musicList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, int layout, ArrayList<Music> myMusiclist) {
        this.context = context;
        this.layout = layout;
        this.musicList = myMusiclist;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return musicList.size(); }

    @Override
    public Object getItem(int pos) { return musicList.get(pos); }

    @Override
    public long getItemId(int pos) { return musicList.get(pos).get_id(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.ivAlbum = convertView.findViewById(R.id.ivAlbum);
            viewHolder.tvSongTitle = convertView.findViewById(R.id.tvSongTitle);
            viewHolder.tvAlbum = convertView.findViewById(R.id.tvAlbum);
            viewHolder.tvArtist = convertView.findViewById(R.id.tvArtist);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivAlbum.setImageResource(musicList.get(pos).getImg());
        viewHolder.tvSongTitle.setText(musicList.get(pos).getSongTitle());
        viewHolder.tvAlbum.setText(musicList.get(pos).getAlbum());
        viewHolder.tvArtist.setText(musicList.get(pos).getArtist());

        return convertView;
    }

    static class ViewHolder {
        ImageView ivAlbum;
        TextView tvSongTitle;
        TextView tvAlbum;
        TextView tvArtist;
    }
}
