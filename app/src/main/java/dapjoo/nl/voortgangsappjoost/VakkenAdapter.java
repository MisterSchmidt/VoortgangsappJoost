package dapjoo.nl.voortgangsappjoost;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        public TextView nameText;
        public TextView countText;

        public VakkenViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);
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

        String name = mCursor.getString(mCursor.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_NAAM));
        int amount = mCursor.getInt(mCursor.getColumnIndex(VakkenContract.VakkenEntry.COLUMN_CIJFER));
        long id = mCursor.getLong(mCursor.getColumnIndex(VakkenContract.VakkenEntry._ID));

        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));
        holder.itemView.setTag(id);
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

