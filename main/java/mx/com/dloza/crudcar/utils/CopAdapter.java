package mx.com.dloza.crudcar.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import mx.com.dloza.crudcar.R;
import mx.com.dloza.crudcar.UpdateRecordActivity;
import mx.com.dloza.crudcar.model.Cop;

public class CopAdapter extends RecyclerView.Adapter<CopAdapter.ViewHolder> {
    private List<Cop> mCopList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView copNameTxtV;
        public TextView copMarkTxtV;
        public TextView copModelTxtV;
        public TextView copProcesadorTxtV;
        public TextView copMemoriaTxtV;
        public TextView copYearTxtV;
        public ImageView copImageImgV;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            copNameTxtV = (TextView) v.findViewById(R.id.name);
            copMarkTxtV = (TextView) v.findViewById(R.id.model);
            copModelTxtV = (TextView) v.findViewById(R.id.mark);
            copProcesadorTxtV = (TextView) v.findViewById(R.id.transmission);
            copMemoriaTxtV = (TextView) v.findViewById(R.id.combustible);
            copYearTxtV = (TextView) v.findViewById(R.id.year);
            copImageImgV = (ImageView) v.findViewById(R.id.image);




        }
    }

    public void add(int position, Cop cop) {
        mCopList.add(position, cop);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mCopList.remove(position);
        notifyItemRemoved(position);
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public CopAdapter(List<Cop> myDataset, Context context, RecyclerView recyclerView) {
        mCopList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.single_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Cop cop = mCopList.get(position);
        holder.copNameTxtV.setText("Name: " + cop.getName());
        holder.copMarkTxtV.setText("Mark: " + cop.getMark());
        holder.copModelTxtV.setText("Model: " + cop.getModel());
        holder.copProcesadorTxtV.setText("Transmission: " + cop.getProcesador());
        holder.copMemoriaTxtV.setText("Combustible: " + cop.getMemoria());
        holder.copYearTxtV.setText("Year: " + cop.getYear());
        Picasso.with(mContext).load(cop.getImage()).placeholder(R.mipmap.ic_launcher).into(holder.copImageImgV);

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete car?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    //go to update activity
                        goToUpdateActivity(cop.getId());

                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CopDBHelper dbHelper = new CopDBHelper(mContext);
                        dbHelper.deleteCarRecord(cop.getId(), mContext);

                        mCopList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mCopList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });


    }

    private void goToUpdateActivity(long personId){
        Intent goToUpdate = new Intent(mContext, UpdateRecordActivity.class);
        goToUpdate.putExtra("USER_ID", personId);
        mContext.startActivity(goToUpdate);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCopList.size();
    }



}