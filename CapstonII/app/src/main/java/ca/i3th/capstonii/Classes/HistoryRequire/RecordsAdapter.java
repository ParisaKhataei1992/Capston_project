package ca.i3th.capstonii.Classes.HistoryRequire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.i3th.capstonii.Classes.Records;
import ca.i3th.capstonii.R;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordsViewHolder> {

    Context context;
    List<Records> recordsList;
    OnRecordsClickListener listener;

    public RecordsAdapter(Context context, List<Records> recordsList, OnRecordsClickListener listener) {
        this.context = context;
        this.recordsList = recordsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.row_list_item, parent, false);
        return new RecordsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordsViewHolder holder, int position) {
        Records records = recordsList.get(position);
        holder.typeR.setText(records.getType());
        holder.startR.setText(records.getStartTime());
        holder.endR.setText(records.getFinishTime());
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    public class RecordsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView typeR, startR, endR;
        public RecordsViewHolder(View itemView) {
            super(itemView);
            typeR = itemView.findViewById(R.id.act_typeR);
            startR = itemView.findViewById(R.id.act_startR);
            endR = itemView.findViewById(R.id.act_endR);

            typeR.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            listener.onRecordClicked(position);
        }
    }

    public interface OnRecordsClickListener {
        void onRecordClicked(int position);
    }

}
