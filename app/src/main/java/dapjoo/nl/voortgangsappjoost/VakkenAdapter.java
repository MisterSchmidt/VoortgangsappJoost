package dapjoo.nl.voortgangsappjoost;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VakkenAdapter extends RecyclerView.Adapter<VakkenAdapter.VakkenViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public VakkenAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class VakkenViewHolder extends RecyclerView.ViewHolder {

        public TextView naamText;
        public TextView cijferText;
        public ImageView image;

        public VakkenViewHolder(@NonNull View itemView) {
            super(itemView);

            naamText = itemView.findViewById(R.id.textview_name_item);
            cijferText = itemView.findViewById(R.id.textview_cijfer_item);
            image = itemView.findViewById(R.id.imageview_item);
        }
    }

    @NonNull
    @Override
    public VakkenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.vak_item, parent, false);
        return new VakkenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VakkenViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }

        String naam = mCursor.getString(mCursor.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_NAAM));
        double cijfer = mCursor.getDouble(mCursor.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_CIJFER));
        long id = mCursor.getLong(mCursor.getColumnIndex(VakkenContract.VakkenEntry._ID));

        holder.naamText.setText(naam);
        holder.cijferText.setText(String.valueOf(cijfer));
        holder.itemView.setTag(id);

        if(cijfer < 5.5){
            holder.image.setImageResource(R.drawable.completed_false);
        }else{
            holder.image.setImageResource(R.drawable.completed_true);
        }

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if (mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}

