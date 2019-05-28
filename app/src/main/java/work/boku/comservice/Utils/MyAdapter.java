package work.boku.comservice.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<String> rbList;
    private final LayoutInflater inflater;


    public MyAdapter(Context context, ArrayList<String> strList) {
        this.context = context;
        this.rbList = strList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(inflater.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(rbList.get(position));
    }

    @Override
    public int getItemCount() {
        return rbList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView text1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
        }

        public void bindData(String s) {
            text1.setText(s);
        }
    }
}
